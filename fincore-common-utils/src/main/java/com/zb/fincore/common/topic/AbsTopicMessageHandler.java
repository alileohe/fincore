package com.zb.fincore.common.topic;

import com.zb.fincore.common.topic.message.TopicMessage;

/**
 * 功能:
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/10 0010 17:15
 * 版本: V1.0
 */
public abstract class AbsTopicMessageHandler extends Thread {

    protected ITopicSubscriber iTopicSubscriber;

    protected TopicMessage topicMessage;

    public abstract void handleMessage(TopicMessage topicMessage);

    public ITopicSubscriber getITopicSubscriber() {
        return iTopicSubscriber;
    }

    public void setITopicSubscriber(ITopicSubscriber iTopicSubscriber) {
        this.iTopicSubscriber = iTopicSubscriber;
    }

    public TopicMessage getTopicMessage() {
        return topicMessage;
    }

    public void setTopicMessage(TopicMessage topicMessage) {
        this.topicMessage = topicMessage;
    }
}
