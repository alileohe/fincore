package com.zb.fincore.common.topic.message;

import java.util.Date;

/**
 * 功能:
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/9 0009 15:59
 * 版本: V1.0
 */
public class MessageHeader {

    private String systemId;

    private String topicType;

    private Date createTime;

    public MessageHeader() {
    }

    public MessageHeader(String systemId, String topicName) {
        this(systemId, topicName, new Date());
    }

    public MessageHeader(String systemId, String topicType, Date createTime) {
        this.systemId = systemId;
        this.topicType = topicType;
        this.createTime = createTime;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
