package com.zb.fincore.ams.facade.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PoolLeftAssetModel implements Serializable {

	private static final long serialVersionUID = -4923954050618177216L;
	
	private Integer leftDays;//资产剩余期限

    private Integer assetCount;//资产笔数

    private BigDecimal totalAmount;//资产总金额

    private BigDecimal leftAmount;//资产剩余金额

    private String poolCode;//资产池编码

    private String poolName;//资产池名称

    private String assetName;//资产名称

    private Date establishTime;//成立时间

    private Date valueStartTime;//资产起息日

    private Date valueEndTime;//资产结息日

    private Integer valueDays;//资产期限

    private Integer poolType;//交易结构

    public Date getValueEndTime() {
        return valueEndTime;
    }

    public void setValueEndTime(Date valueEndTime) {
        this.valueEndTime = valueEndTime;
    }

    public Integer getLeftDays() {
        return leftDays;
    }

    public void setLeftDays(Integer leftDays) {
        this.leftDays = leftDays;
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

    public BigDecimal getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(BigDecimal leftAmount) {
        this.leftAmount = leftAmount;
    }

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Date getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
    }

    public Date getValueStartTime() {
        return valueStartTime;
    }

    public void setValueStartTime(Date valueStartTime) {
        this.valueStartTime = valueStartTime;
    }

    public Integer getValueDays() {
        return valueDays;
    }

    public void setValueDays(Integer valueDays) {
        this.valueDays = valueDays;
    }

    public Integer getPoolType() {
        return poolType;
    }

    public void setPoolType(Integer poolType) {
        this.poolType = poolType;
    }
}
