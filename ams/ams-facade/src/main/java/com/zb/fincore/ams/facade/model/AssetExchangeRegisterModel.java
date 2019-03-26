package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class AssetExchangeRegisterModel extends BaseModel {

    private String assetCode;

    private String parentAssetCode;

    private String exchangeRegisterBatchNo;

    private String tradeInstCode;

    private String delistedInstCode;

    private String listedInstCode;

    private String status;

    private String registerStatus;

    private String registerName;

    private BigDecimal registerRate;

    private BigDecimal registerAmount;

    private Date registerValueStartTime;

    private Date registerValueEndTime;

    private Date registerExpireTime;

    private Date registerTime;

    private Integer valueDays;

    private Integer repaymentType;

    private String memo;

    private Long version;

    private AssetModel assetModel;

    private FileTemplateParamModel paramModel;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    public String getExchangeRegisterBatchNo() {
        return exchangeRegisterBatchNo;
    }

    public void setExchangeRegisterBatchNo(String exchangeRegisterBatchNo) {
        this.exchangeRegisterBatchNo = exchangeRegisterBatchNo == null ? null : exchangeRegisterBatchNo.trim();
    }

    public String getTradeInstCode() {
        return tradeInstCode;
    }

    public void setTradeInstCode(String tradeInstCode) {
        this.tradeInstCode = tradeInstCode;
    }

    public String getDelistedInstCode() {
        return delistedInstCode;
    }

    public void setDelistedInstCode(String delistedInstCode) {
        this.delistedInstCode = delistedInstCode == null ? null : delistedInstCode.trim();
    }

    public String getListedInstCode() {
        return listedInstCode;
    }

    public void setListedInstCode(String listedInstCode) {
        this.listedInstCode = listedInstCode == null ? null : listedInstCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public BigDecimal getRegisterRate() {
        return registerRate;
    }

    public void setRegisterRate(BigDecimal registerRate) {
        this.registerRate = registerRate;
    }

    public String getParentAssetCode() {
        return parentAssetCode;
    }

    public void setParentAssetCode(String parentAssetCode) {
        this.parentAssetCode = parentAssetCode;
    }

    public BigDecimal getRegisterAmount() {
        return registerAmount;
    }

    public void setRegisterAmount(BigDecimal registerAmount) {
        this.registerAmount = registerAmount;
    }

    public Date getRegisterValueStartTime() {
        return registerValueStartTime;
    }

    public void setRegisterValueStartTime(Date registerValueStartTime) {
        this.registerValueStartTime = registerValueStartTime;
    }

    public Date getRegisterValueEndTime() {
        return registerValueEndTime;
    }

    public void setRegisterValueEndTime(Date registerValueEndTime) {
        this.registerValueEndTime = registerValueEndTime;
    }

    public Date getRegisterExpireTime() {
        return registerExpireTime;
    }

    public void setRegisterExpireTime(Date registerExpireTime) {
        this.registerExpireTime = registerExpireTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public AssetModel getAssetModel() {
        return assetModel;
    }

    public void setAssetModel(AssetModel assetModel) {
        this.assetModel = assetModel;
    }

    public FileTemplateParamModel getParamModel() {
        return paramModel;
    }

    public void setParamModel(FileTemplateParamModel paramModel) {
        this.paramModel = paramModel;
    }

    public Integer getValueDays() {
        return valueDays;
    }

    public void setValueDays(Integer valueDays) {
        this.valueDays = valueDays;
    }

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }
}