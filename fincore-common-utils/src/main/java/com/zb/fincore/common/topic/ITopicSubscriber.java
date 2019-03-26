package com.zb.fincore.common.topic;

import com.zb.fincore.common.topic.message.TopicMessage;

/**
 * 功能: 主题订阅者接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/9 0009 10:08
 * 版本: V1.0
 */
public interface ITopicSubscriber {

    /**
     * 启动订阅者
     */
    void startup();

    /**
     * 停止订阅者
     */
    void shutdown();

    /**
     * 删除主题消息
     *
     * @param message 要删除的主题消息
     */
    void deleteTopicMessage(TopicMessage message);
}
