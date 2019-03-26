package com.zb.fincore.common.topic.impl.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.RawTopicMessage;
import com.zb.fincore.common.topic.ITopicPublisher;
import com.zb.fincore.common.topic.message.MessageTailer;
import com.zb.fincore.common.topic.message.TopicMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能: MNS服务主题发布者
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/9 0009 10:14
 * 版本: V1.0
 */
public class MnsTopicPublisher implements ITopicPublisher {

    /**
     * 日志器
     */
    private static Logger logger = LoggerFactory.getLogger(MnsTopicPublisher.class);

    /**
     * 阿里云授权信息
     */
    private CloudAccount cloudAccount;

    public MnsTopicPublisher() {
    }

    public MnsTopicPublisher(CloudAccount cloudAccount) {
        this.cloudAccount = cloudAccount;
    }

    public TopicMessage publishTopicMessage(String topicName, TopicMessage topicMessage) {
        MNSClient client = cloudAccount.getMNSClient();
        try {
            CloudTopic cloudTopic = client.getTopicRef(topicName);
            com.aliyun.mns.model.TopicMessage tMessage = new RawTopicMessage();
            tMessage.setBaseMessageBody(JSON.toJSONString(topicMessage));
            tMessage = cloudTopic.publishMessage(tMessage);
            MessageTailer messageTailer = new MessageTailer(tMessage.getMessageId(), tMessage.getMessageBodyMD5());
            topicMessage.setMessageTailer(messageTailer);
        } catch (Exception e) {
            logger.error("发布主题异常", e);
        } finally {
            client.close();
        }
        return topicMessage;
    }

    public CloudAccount getCloudAccount() {
        return cloudAccount;
    }

    public void setCloudAccount(CloudAccount cloudAccount) {
        this.cloudAccount = cloudAccount;
    }
}
