package com.zb.fincore.common.topic.impl.aliyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.zb.fincore.common.topic.AbsTopicMessageHandler;
import com.zb.fincore.common.topic.ITopicSubscriber;
import com.zb.fincore.common.topic.message.MessageTailer;
import com.zb.fincore.common.topic.message.TopicMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;

/**
 * 功能:
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/9 0009 17:23
 * 版本: V1.0
 */
public class MnsTopicSubscriber extends Thread implements ITopicSubscriber {

    /**
     * 日志器
     */
    private static Logger logger = LoggerFactory.getLogger(MnsTopicSubscriber.class);

    /**
     * 阿里云授权信息
     */
    private CloudAccount cloudAccount;

    /**
     * 主题订阅队列名称
     */
    private String queueName;

    /**
     * 无消息时等待时间,默认5秒
     */
    private int waitSeconds = 5;

    /**
     * 消息处理线程池
     */
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 是否运行
     */
    private boolean runnable = true;

    /**
     * 消息处理器映射关系
     */
    private Map<String, String> topicTypeHandlerMap;

    /**
     * 接收订阅消息的消息队列
     */
    private CloudQueue queue;

    @Override
    public void run() {
        MNSClient client = null;
        try {
            client = cloudAccount.getMNSClient();
            queue = client.getQueueRef(this.queueName);
            while (this.runnable) {
                try {
                    Message popMsg = queue.popMessage(this.waitSeconds);
                    if (popMsg != null) {
                        logger.debug(popMsg.getMessageBodyAsRawString());
                        JSONObject jsonObject = JSON.parseObject(popMsg.getMessageBodyAsRawString());
                        String jsonStr = jsonObject.getString("Message");
                        logger.info(jsonStr);
                        TopicMessage topicMessage = JSON.parseObject(jsonStr, TopicMessage.class);
                        topicMessage.setMessageTailer(new MessageTailer(popMsg.getMessageId(), popMsg.getReceiptHandle(), popMsg.getMessageBodyMD5()));
                        //路由消息
                        String handlerClass = routeMessage(topicMessage);
                        if (handlerClass == null)
                            queue.deleteMessage(popMsg.getReceiptHandle());
                        //反射获取消息处理器
                        AbsTopicMessageHandler handler = (AbsTopicMessageHandler) Class.forName(handlerClass).
                                getConstructors()[0].newInstance();
                        handler.setITopicSubscriber(this);
                        handler.setTopicMessage(topicMessage);
                        taskExecutor.execute(handler);
                    } else {
                        logger.debug("正在监听:" + this.queueName);
                    }
                } catch (ClientException ce) {
                    logger.error("客户端异常,请检查网络设置和DNS有效性", ce);
                    Thread.sleep(this.waitSeconds * 1000);
                } catch (ServiceException se) {
                    logger.error("服务端异常,requestId:" + se.getRequestId(), se);
                    if (se.getErrorCode() != null) {
                        if (se.getErrorCode().equals("QueueNotExist")) {
                            logger.error(this.queueName + "队列不存在");
                        } else if (se.getErrorCode().equals("TimeExpired")) {
                            logger.error("请求过期,请检测本机服务器时间设置");
                        }
                    }
                    Thread.sleep(this.waitSeconds * 1000);
                } catch (Exception ne) {
                    logger.error("阿里云队列消费者出现未知异常", ne);
                    Thread.sleep(this.waitSeconds * 1000);
                }
            }
            logger.info("已停止:" + this.queueName);
        } catch (Exception e) {
            logger.error("连接阿里云MNS服务异常", e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    /**
     * 消息路由
     *
     * @param topicMessage 自定义消息体
     */
    public String routeMessage(TopicMessage topicMessage) {
        if (this.topicTypeHandlerMap == null) {
            logger.info("MnsTopicSubscriber消息处理映射未定义");
            return null;
        }
        if (topicMessage == null) {
            logger.info("TopicMessage为null");
            return null;
        }
        if (topicMessage.getMessageHeader() == null) {
            logger.info("MessageHeader为null");
            return null;
        }
        String topicType = topicMessage.getMessageHeader().getTopicType();
        if (topicType == null) {
            logger.info("TopicType为null");
            return null;
        }
        if (!this.topicTypeHandlerMap.containsKey(topicType)) {
            logger.info("未知TopicType");
            return null;
        }
        String handlerClass = this.topicTypeHandlerMap.get(topicType);
        logger.info("主题消息" + topicType + "分发给" + handlerClass + "处理");
        return handlerClass;
    }

    public void startup() {
        this.start();
    }

    public void shutdown() {
        logger.info("尝试停止" + this.queueName + "队列消费者");
        if (this.runnable) {
            this.runnable = false;
            //防止出现memory leak
            try {
                Thread.sleep(this.waitSeconds * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            logger.info("已停止:" + this.queueName);
        }
    }

    public synchronized void deleteTopicMessage(TopicMessage message) {
        try {
            queue.deleteMessage(message.getMessageTailer().getReceiptHandle());
        } catch (Exception e) {
            logger.error("删除主题消息异常", e);
        }
    }

    public CloudAccount getCloudAccount() {
        return cloudAccount;
    }

    public void setCloudAccount(CloudAccount cloudAccount) {
        this.cloudAccount = cloudAccount;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public int getWaitSeconds() {
        return waitSeconds;
    }

    public void setWaitSeconds(int waitSeconds) {
        this.waitSeconds = waitSeconds;
    }

    public ThreadPoolTaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public boolean isRunnable() {
        return runnable;
    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }

    public Map<String, String> getTopicTypeHandlerMap() {
        return topicTypeHandlerMap;
    }

    public void setTopicTypeHandlerMap(Map<String, String> topicTypeHandlerMap) {
        this.topicTypeHandlerMap = topicTypeHandlerMap;
    }
}
