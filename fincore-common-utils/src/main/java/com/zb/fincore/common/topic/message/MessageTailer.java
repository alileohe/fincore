package com.zb.fincore.common.topic.message;

/**
 * 功能:
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/9 0009 16:50
 * 版本: V1.0
 */
public class MessageTailer {

    private String messageId;

    private String receiptHandle;

    private String messageMD5;

    public MessageTailer() {
    }

    public MessageTailer(String messageId, String messageMD5) {
        this.messageId = messageId;
        this.messageMD5 = messageMD5;
    }

    public MessageTailer(String messageId, String receiptHandle, String messageMD5) {
        this.messageId = messageId;
        this.receiptHandle = receiptHandle;
        this.messageMD5 = messageMD5;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getReceiptHandle() {
        return receiptHandle;
    }

    public void setReceiptHandle(String receiptHandle) {
        this.receiptHandle = receiptHandle;
    }

    public String getMessageMD5() {
        return messageMD5;
    }

    public void setMessageMD5(String messageMD5) {
        this.messageMD5 = messageMD5;
    }
}
