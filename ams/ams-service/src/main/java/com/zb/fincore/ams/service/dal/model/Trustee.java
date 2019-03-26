package com.zb.fincore.ams.service.dal.model;

import com.zb.fincore.ams.common.model.BaseDo;

import java.math.BigDecimal;

public class Trustee extends BaseDo {

    private String trusteeCode;

    private String trusteeName;

    private Integer certType;

    private String certNo;

    private BigDecimal registeredCapital;

    private String trusteeAddress;

    private String trusteeAddressShowName;

    private String legalPersonName;

    private String legalPersonCertNo;

    private String businessScope;

    private String introduction;

    private Integer status;

    private String trusteeShowName;

    private String certNoShowName;

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getTrusteeCode() {
        return trusteeCode;
    }

    public void setTrusteeCode(String trusteeCode) {
        this.trusteeCode = trusteeCode;
    }

    public String getTrusteeName() {
        return trusteeName;
    }

    public void setTrusteeName(String trusteeName) {
        this.trusteeName = trusteeName;
    }

    public String getTrusteeAddress() {
        return trusteeAddress;
    }

    public void setTrusteeAddress(String trusteeAddress) {
        this.trusteeAddress = trusteeAddress;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName == null ? null : legalPersonName.trim();
    }

    public String getLegalPersonCertNo() {
        return legalPersonCertNo;
    }

    public void setLegalPersonCertNo(String legalPersonCertNo) {
        this.legalPersonCertNo = legalPersonCertNo == null ? null : legalPersonCertNo.trim();
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTrusteeShowName() {
        return trusteeShowName;
    }

    public void setTrusteeShowName(String trusteeShowName) {
        this.trusteeShowName = trusteeShowName;
    }

    public String getCertNoShowName() {
        return certNoShowName;
    }

    public void setCertNoShowName(String certNoShowName) {
        this.certNoShowName = certNoShowName;
    }

    public String getTrusteeAddressShowName() {
        return trusteeAddressShowName;
    }

    public void setTrusteeAddressShowName(String trusteeAddressShowName) {
        this.trusteeAddressShowName = trusteeAddressShowName;
    }
}