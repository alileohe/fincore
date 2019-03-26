package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MABIAO on 2017/7/6 0006.
 */
public class ContractInfoModel implements Serializable {

    private static final long serialVersionUID = -4555805217985844887L;

    private String contractStatus;

    private Date approvalTime;

    private Integer registerRecordCount;

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public Integer getRegisterRecordCount() {
        return registerRecordCount;
    }

    public void setRegisterRecordCount(Integer registerRecordCount) {
        this.registerRecordCount = registerRecordCount;
    }
}
