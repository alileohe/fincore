package com.zb.fincore.ams.facade.model;

import java.io.Serializable;

/**
 * Created by MABIAO on 2017/6/12 0012.
 */
public class ProductRelatedAssetInfoModel implements Serializable {


    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 3778864176688666771L;
    /**
     * 受托方
     */
    private String trusteeName;

    /**
     * 合作机构
     */
    private String cooperationOrgName;

    /**
     * 证件号码
     */
    private String certNo;

    private String investTargetIntroduction;

    public String getTrusteeName() {
        return trusteeName;
    }

    public void setTrusteeName(String trusteeName) {
        this.trusteeName = trusteeName;
    }

    public String getCooperationOrgName() {
        return cooperationOrgName;
    }

    public void setCooperationOrgName(String cooperationOrgName) {
        this.cooperationOrgName = cooperationOrgName;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getInvestTargetIntroduction() {
        return investTargetIntroduction;
    }

    public void setInvestTargetIntroduction(String investTargetIntroduction) {
        this.investTargetIntroduction = investTargetIntroduction;
    }
}
