package com.zb.fincore.ams.service.dal.model;

import com.zb.fincore.ams.common.model.BaseDo;

public class FileTemplateParam extends BaseDo {

    private String templateCode;

    private String assetCode;

    private String contractBatchNo;

    private String templateContent;

    private String templateParam;

    private Integer stepCode;

    private Integer status;

    private Long version;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getContractBatchNo() {
        return contractBatchNo;
    }

    public void setContractBatchNo(String contractBatchNo) {
        this.contractBatchNo = contractBatchNo;
    }

    public Integer getStepCode() {
        return stepCode;
    }

    public void setStepCode(Integer stepCode) {
        this.stepCode = stepCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }
}