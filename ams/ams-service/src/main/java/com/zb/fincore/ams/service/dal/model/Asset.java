package com.zb.fincore.ams.service.dal.model;

import com.zb.fincore.ams.common.model.BaseDo;
import com.zb.fincore.ams.facade.model.BusinessCreditModel;

import java.math.BigDecimal;
import java.util.Date;

public class Asset extends BaseDo {

    private String assetCode;

    private String parentAssetCode;

    private String extAssetCode;

    private String assetName;

    private Integer assetType;

    /**
     * 融资项目
     */
    private String financeProject;

    /**
     * 项目描述
     */
    private String projectDesc;

    /**
     * 还款保障措施
     */
    private String repayGuarenteeMode;

    /**
     * 增信措施
     */
    private String creditMode;

    private Integer collectStatus;

    private String approvalRequireSign;

    private String approvalSign;

    private Integer approvalStatus;

    private BigDecimal assetAmount;

    private BigDecimal distributeAmount;

    private BigDecimal stockAmount;

    private BigDecimal frozenAmount;

    private BigDecimal saleAmount;

    private Long financeSubjectId;

    private String financeSubjectCode;

    private String provideLoanCode;//出资方

    private Date establishTime;

    private Date valueStartTime;

    private Date valueEndTime;

    private Date expireTime;

    private Integer transferLockDays;

    private Integer valueDays;

    private BigDecimal yieldRate;

    private BigDecimal minInvestAmount;

    private BigDecimal increaseAmount;

    private Integer repaymentType;

    private String assetDesc;

    private Integer registerType;

    private Integer storageStatus;

    /**
     * 披露信息(JSON格式)
     */
    private String publishInfo;

    private Integer version;

    /**
     * 提交审核开始时间
     */
    private String beginCreateTime;

    /**
     * 提交审核结束时间
     */
    private String endCreateTime;

    /**
     * 录入批次号
     */
    private String bathNo;

    private FinanceSubject financeSubject;//融资方

    private AssetExchangeRegister register;//交易所备案

    private BusinessCredit businessCredit;//业务授信

    private AssetApproval approval;//审核

    private IssuePlan issuePlan;//发行计划

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public String getFinanceProject() {
        return financeProject;
    }

    public void setFinanceProject(String financeProject) {
        this.financeProject = financeProject;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getRepayGuarenteeMode() {
        return repayGuarenteeMode;
    }

    public void setRepayGuarenteeMode(String repayGuarenteeMode) {
        this.repayGuarenteeMode = repayGuarenteeMode;
    }

    public String getCreditMode() {
        return creditMode;
    }

    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode;
    }

    public Integer getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(Integer collectStatus) {
        this.collectStatus = collectStatus;
    }

    public String getApprovalRequireSign() {
        return approvalRequireSign;
    }

    public void setApprovalRequireSign(String approvalRequireSign) {
        this.approvalRequireSign = approvalRequireSign == null ? null : approvalRequireSign.trim();
    }

    public String getApprovalSign() {
        return approvalSign;
    }

    public void setApprovalSign(String approvalSign) {
        this.approvalSign = approvalSign == null ? null : approvalSign.trim();
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public BigDecimal getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(BigDecimal assetAmount) {
        this.assetAmount = assetAmount;
    }

    public BigDecimal getDistributeAmount() {
        return distributeAmount;
    }

    public void setDistributeAmount(BigDecimal distributeAmount) {
        this.distributeAmount = distributeAmount;
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

    public Date getValueEndTime() {
        return valueEndTime;
    }

    public void setValueEndTime(Date valueEndTime) {
        this.valueEndTime = valueEndTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getTransferLockDays() {
        return transferLockDays;
    }

    public void setTransferLockDays(Integer transferLockDays) {
        this.transferLockDays = transferLockDays;
    }

    public Integer getValueDays() {
        return valueDays;
    }

    public void setValueDays(Integer valueDays) {
        this.valueDays = valueDays;
    }

    public BigDecimal getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(BigDecimal yieldRate) {
        this.yieldRate = yieldRate;
    }

    public BigDecimal getMinInvestAmount() {
        return minInvestAmount;
    }

    public void setMinInvestAmount(BigDecimal minInvestAmount) {
        this.minInvestAmount = minInvestAmount;
    }

    public BigDecimal getIncreaseAmount() {
        return increaseAmount;
    }

    public void setIncreaseAmount(BigDecimal increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getAssetDesc() {
        return assetDesc;
    }

    public void setAssetDesc(String assetDesc) {
        this.assetDesc = assetDesc == null ? null : assetDesc.trim();
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

    public String getBeginCreateTime() {
        return beginCreateTime;
    }

    public void setBeginCreateTime(String beginCreateTime) {
        this.beginCreateTime = beginCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public String getBathNo() {
        return bathNo;
    }

    public void setBathNo(String bathNo) {
        this.bathNo = bathNo;
    }

    public Integer getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(Integer storageStatus) {
        this.storageStatus = storageStatus;
    }

    public String getPublishInfo() {
        return publishInfo;
    }

    public void setPublishInfo(String publishInfo) {
        this.publishInfo = publishInfo;
    }

    public AssetExchangeRegister getRegister() {
        return register;
    }

    public void setRegister(AssetExchangeRegister register) {
        this.register = register;
    }

    public BusinessCredit getBusinessCredit() {
        return businessCredit;
    }

    public void setBusinessCredit(BusinessCredit businessCredit) {
        this.businessCredit = businessCredit;
    }

    public AssetApproval getApproval() {
        return approval;
    }

    public void setApproval(AssetApproval approval) {
        this.approval = approval;
    }

    public String getParentAssetCode() {
        return parentAssetCode;
    }

    public void setParentAssetCode(String parentAssetCode) {
        this.parentAssetCode = parentAssetCode;
    }

    public String getProvideLoanCode() {
        return provideLoanCode;
    }

    public void setProvideLoanCode(String provideLoanCode) {
        this.provideLoanCode = provideLoanCode;
    }

    public IssuePlan getIssuePlan() {
        return issuePlan;
    }

    public void setIssuePlan(IssuePlan issuePlan) {
        this.issuePlan = issuePlan;
    }

    public String getExtAssetCode() {
        return extAssetCode;
    }

    public void setExtAssetCode(String extAssetCode) {
        this.extAssetCode = extAssetCode;
    }
}