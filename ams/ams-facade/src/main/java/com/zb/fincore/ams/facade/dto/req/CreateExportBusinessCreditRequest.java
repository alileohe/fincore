package com.zb.fincore.ams.facade.dto.req;

import java.math.BigDecimal;
import java.util.Date;

public class CreateExportBusinessCreditRequest {

    private String creditBusinessCode;//授信业务编号

    private BigDecimal creditLimitAmount;//授信金额

    private String batchNo;//录入批次号

    private String assetCode;//资产编号

    private Integer contractDays;//合同天数

    private Date valueStartTime;//合同起息(备案开始)时间

    private Date valueEndTime;//备案完成时间

    private Date freeStartTime;//空档期开始

    private Date freeEndTime;//空档期结束

    private Date saleStartTime;//募集开始时间

    private Date saleEndTime;//募集结束时间

    private Date productValueStartTime;//产品计息开始时间

    private Date productValueEndTime;//产品结息时间(合同结息)

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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public Integer getContractDays() {
        return contractDays;
    }

    public void setContractDays(Integer contractDays) {
        this.contractDays = contractDays;
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

    public Date getFreeStartTime() {
        return freeStartTime;
    }

    public void setFreeStartTime(Date freeStartTime) {
        this.freeStartTime = freeStartTime;
    }

    public Date getFreeEndTime() {
        return freeEndTime;
    }

    public void setFreeEndTime(Date freeEndTime) {
        this.freeEndTime = freeEndTime;
    }

    public Date getSaleStartTime() {
        return saleStartTime;
    }

    public void setSaleStartTime(Date saleStartTime) {
        this.saleStartTime = saleStartTime;
    }

    public Date getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(Date saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    public Date getProductValueStartTime() {
        return productValueStartTime;
    }

    public void setProductValueStartTime(Date productValueStartTime) {
        this.productValueStartTime = productValueStartTime;
    }

    public Date getProductValueEndTime() {
        return productValueEndTime;
    }

    public void setProductValueEndTime(Date productValueEndTime) {
        this.productValueEndTime = productValueEndTime;
    }
}