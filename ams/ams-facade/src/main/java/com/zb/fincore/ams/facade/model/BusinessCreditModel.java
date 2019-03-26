package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  业务授信
 * Created by MABIAO on 2017/6/16 0016.
 */
public class BusinessCreditModel extends BaseModel {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -5163642079129138926L;

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

    private String repaymentType;

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
        this.creditBusinessCode = creditBusinessCode;
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

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFinancingPurpose() {
        return financingPurpose;
    }

    public void setFinancingPurpose(String financingPurpose) {
        this.financingPurpose = financingPurpose;
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

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getProvideLoanName() {
        return provideLoanName;
    }

    public void setProvideLoanName(String provideLoanName) {
        this.provideLoanName = provideLoanName;
    }
}
