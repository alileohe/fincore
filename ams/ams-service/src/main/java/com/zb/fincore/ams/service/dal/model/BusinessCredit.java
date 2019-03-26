package com.zb.fincore.ams.service.dal.model;

import com.zb.fincore.ams.common.model.BaseDo;

import java.math.BigDecimal;
import java.util.Date;

public class BusinessCredit extends BaseDo {

    private String batchNo;

    private String creditBusinessCode;

    private BigDecimal creditLimitAmount;

    private String financingName;

    private String provideLoanName;

    private String certNo;

    private String legalPersonName;

    private String contactWay;

    private String address;

    private String financingPurpose;

    private BigDecimal yieldRate;

    private Date valueStartTime;

    private Date valueEndTime;

    private Integer dayCount;

    private Integer repaymentType;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCreditBusinessCode() {
        return creditBusinessCode;
    }

    public void setCreditBusinessCode(String creditBusinessCode) {
        this.creditBusinessCode = creditBusinessCode == null ? null : creditBusinessCode.trim();
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName == null ? null : legalPersonName.trim();
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay == null ? null : contactWay.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(BigDecimal yieldRate) {
        this.yieldRate = yieldRate;
    }

    public Date getValueStartTime() {
        return valueStartTime;
    }

    public void setValueStartTime(Date valueStartTime) {
        this.valueStartTime = valueStartTime;
    }

    public Date getValueEndTime() {
        return valueEndTime;
    }

    public void setValueEndTime(Date valueEndTime) {
        this.valueEndTime = valueEndTime;
    }

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    public BigDecimal getCreditLimitAmount() {
        return creditLimitAmount;
    }

    public void setCreditLimitAmount(BigDecimal creditLimitAmount) {
        this.creditLimitAmount = creditLimitAmount;
    }

    public String getFinancingName() {
        return financingName;
    }

    public void setFinancingName(String financingName) {
        this.financingName = financingName;
    }

    public String getFinancingPurpose() {
        return financingPurpose;
    }

    public void setFinancingPurpose(String financingPurpose) {
        this.financingPurpose = financingPurpose;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public String getProvideLoanName() {
        return provideLoanName;
    }

    public void setProvideLoanName(String provideLoanName) {
        this.provideLoanName = provideLoanName;
    }
}