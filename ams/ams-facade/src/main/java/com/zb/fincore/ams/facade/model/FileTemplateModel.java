package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

/**
 * 文件模板
 */
public class FileTemplateModel extends BaseModel {

    /**
     * 模板编码
     */
    private String templateCode;

    /**
     * 文件序列号
     */
    private String fileSerialNo;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode == null ? null : templateCode.trim();
    }

    public String getFileSerialNo() {
        return fileSerialNo;
    }

    public void setFileSerialNo(String fileSerialNo) {
        this.fileSerialNo = fileSerialNo == null ? null : fileSerialNo.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

}