package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 发行计划
 * Created by MABIAO on 2017/6/16 0016.
 */
public class IssuePlanModel extends BaseModel{

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 2763940782599636873L;

    private String creditBusinessCode;

    private String batchNo;

    private Integer productDayCount;

    private Integer contractDayCount;

    private Date valueStartTime;

    private Date valueEndTime;

    private Date freeStartTime;

    private Date freeEndTime;

    private Date saleStartTime;

    private Date saleEndTime;

    private Date productValueStartTime;

    private Date productValueEndTime;

    private BigDecimal singleAmount;

    private Integer assetCount;

    private BigDecimal totalAmount;

    private String subjectName;

    private String subjectCode;

    private String memo;

    public String getCreditBusinessCode() {
        return creditBusinessCode;
    }

    public void setCreditBusinessCode(String creditBusinessCode) {
        this.creditBusinessCode = creditBusinessCode;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getProductDayCount() {
        return productDayCount;
    }

    public void setProductDayCount(Integer productDayCount) {
        this.productDayCount = productDayCount;
    }

    public Integer getContractDayCount() {
        return contractDayCount;
    }

    public void setContractDayCount(Integer contractDayCount) {
        this.contractDayCount = contractDayCount;
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

    public BigDecimal getSingleAmount() {
        return singleAmount;
    }

    public void setSingleAmount(BigDecimal singleAmount) {
        this.singleAmount = singleAmount;
    }

    public Integer getAssetCount() {
        return assetCount;
    }

    public void setAssetCount(Integer assetCount) {
        this.assetCount = assetCount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
