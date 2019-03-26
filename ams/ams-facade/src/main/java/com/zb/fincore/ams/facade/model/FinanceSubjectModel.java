package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

import java.math.BigDecimal;

public class FinanceSubjectModel extends BaseModel {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -8363835945031790716L;

    private String subjectCode;

    private String subjectName;

    /**
     * 融资方展示名
     */
    private String subjectShowName;

    private Integer certType;

    private String certNo;

    /**
     * 证件号码展示名称
     */
    private String certNoShowName;

    private BigDecimal registeredCapital;

    private String subjectAddress;

    private String subjectAddressShowName;

    private String legalPersonName;

    private String legalPersonCertNo;

    private String tel;

    private String email;

    private String businessScope;

    private String introduction;

    private Integer status;

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

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

    public String getSubjectAddress() {
        return subjectAddress;
    }

    public void setSubjectAddress(String subjectAddress) {
        this.subjectAddress = subjectAddress == null ? null : subjectAddress.trim();
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

    public String getSubjectShowName() {
        return subjectShowName;
    }

    public void setSubjectShowName(String subjectShowName) {
        this.subjectShowName = subjectShowName;
    }

    public String getCertNoShowName() {
        return certNoShowName;
    }

    public void setCertNoShowName(String certNoShowName) {
        this.certNoShowName = certNoShowName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectAddressShowName() {
        return subjectAddressShowName;
    }

    public void setSubjectAddressShowName(String subjectAddressShowName) {
        this.subjectAddressShowName = subjectAddressShowName;
    }
}