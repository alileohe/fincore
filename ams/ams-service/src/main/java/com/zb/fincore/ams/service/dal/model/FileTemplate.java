package com.zb.fincore.ams.service.dal.model;

import com.zb.fincore.ams.common.model.BaseDo;

/**
 * 文件模板
 * Created by MABIAO on 2017/6/19 0019.
 */
public class FileTemplate extends BaseDo {
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
        this.templateCode = templateCode;
    }

    public String getFileSerialNo() {
        return fileSerialNo;
    }

    public void setFileSerialNo(String fileSerialNo) {
        this.fileSerialNo = fileSerialNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
