package com.zb.fincore.ams.service.dal.model;

import com.zb.fincore.ams.common.model.BaseDo;

import java.math.BigDecimal;

public class Pool extends BaseDo {

    private String poolCode;

    private String poolName;

    private Integer poolType;

    private BigDecimal limitAmount;

    private BigDecimal totalAmount;

    private BigDecimal stockAmount;

    private BigDecimal frozenAmount;

    private BigDecimal saleAmount;

    private Integer status;

    private Integer loadSwitchStatus;

    private Long financeSubjectId;

    private String financeSubjectCode;

    private Long trusteeId;

    private String trusteeCode;

    private String poolDesc;

    private Integer version;

    private Integer assetCount;

    /**
     * 融资方
     */
    private FinanceSubject financeSubject;

    /**
     * 受托方
     */
    private Trustee trustee;

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode == null ? null : poolCode.trim();
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName == null ? null : poolName.trim();
    }

    public Integer getPoolType() {
        return poolType;
    }

    public void setPoolType(Integer poolType) {
        this.poolType = poolType;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(BigDecimal stockAmount) {
        this.stockAmount = stockAmount;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLoadSwitchStatus() {
        return loadSwitchStatus;
    }

    public void setLoadSwitchStatus(Integer loadSwitchStatus) {
        this.loadSwitchStatus = loadSwitchStatus;
    }

    public Long getFinanceSubjectId() {
        return financeSubjectId;
    }

    public void setFinanceSubjectId(Long financeSubjectId) {
        this.financeSubjectId = financeSubjectId;
    }

    public String getFinanceSubjectCode() {
        return financeSubjectCode;
    }

    public void setFinanceSubjectCode(String financeSubjectCode) {
        this.financeSubjectCode = financeSubjectCode;
    }

    public Long getTrusteeId() {
        return trusteeId;
    }

    public void setTrusteeId(Long trusteeId) {
        this.trusteeId = trusteeId;
    }

    public String getTrusteeCode() {
        return trusteeCode;
    }

    public void setTrusteeCode(String trusteeCode) {
        this.trusteeCode = trusteeCode == null ? null : trusteeCode.trim();
    }

    public String getPoolDesc() {
        return poolDesc;
    }

    public void setPoolDesc(String poolDesc) {
        this.poolDesc = poolDesc;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public FinanceSubject getFinanceSubject() {
        return financeSubject;
    }

    public void setFinanceSubject(FinanceSubject financeSubject) {
        this.financeSubject = financeSubject;
    }

    public Trustee getTrustee() {
        return trustee;
    }

    public void setTrustee(Trustee trustee) {
        this.trustee = trustee;
    }

    public Integer getAssetCount() {
        return assetCount;
    }

    public void setAssetCount(Integer assetCount) {
        this.assetCount = assetCount;
    }
}