package com.zb.fincore.common.topic.message;

import net.sf.json.JSONObject;

import java.util.Date;

/**
 * 功能:
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/9 0009 15:58
 * 版本: V1.0
 */
public class TopicMessage<T> {

    /**
     * 报文头
     */
    private MessageHeader messageHeader;

    /**
     * 报文体
     */
    private JSONObject messageBody;

    /**
     * 报文尾
     */
    private MessageTailer messageTailer;

    public TopicMessage() {
    }

    public TopicMessage(MessageHeader messageHeader, T messageBody) {
        this.messageHeader = messageHeader;
        this.messageBody = JSONObject.fromObject(messageBody);
    }

    public TopicMessage(String systemId, String topicType, Date createTime, T messageBody) {
        this.messageHeader = new MessageHeader(systemId, topicType, createTime);
        this.messageBody = JSONObject.fromObject(messageBody);
    }

    public TopicMessage(String systemId, String topicType, T messageBody) {
        this.messageHeader = new MessageHeader(systemId, topicType);
        this.messageBody = JSONObject.fromObject(messageBody);
    }

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public JSONObject getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(JSONObject messageBody) {
        this.messageBody = messageBody;
    }

    public MessageTailer getMessageTailer() {
        return messageTailer;
    }

    public void setMessageTailer(MessageTailer messageTailer) {
        this.messageTailer = messageTailer;
    }

    public <T> T getMessageBodyBean(Class<T> beanClass) {
        if (this.messageBody != null) {
            return (T) JSONObject.toBean(this.messageBody, beanClass);
        } else {
            return null;
        }
    }

    public boolean isPublished() {
        if (this.messageTailer == null)
            return false;
        else {
            return this.messageTailer.getMessageId() != null;
        }
    }
}
