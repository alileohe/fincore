package com.zb.fincore.common.topic;

import com.zb.fincore.common.topic.message.TopicMessage;

/**
 * 功能: 主题发布者接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/9 0009 10:05
 * 版本: V1.0
 */
public interface ITopicPublisher {

    /**
     * 发布主题
     *
     * @param topicName    主题名称
     * @param topicMessage 主题消息
     * @return
     */
    TopicMessage publishTopicMessage(String topicName, TopicMessage topicMessage);
}
