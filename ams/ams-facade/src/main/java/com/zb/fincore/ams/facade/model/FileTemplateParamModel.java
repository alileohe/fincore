package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

/**
 * 文件模板参数
 * Created by MABIAO on 2017/6/19 0019.
 */
public class FileTemplateParamModel extends BaseModel {

    private static final long serialVersionUID = -4033977413715740770L;

    private String templateCode;

    private String assetCode;

    private String contractBatchNo;

    private String templateContent;

    private String templateParam;

    private Integer stepCode;

    private Integer status;

    private Long version;

    private String statusDesc;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
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

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
