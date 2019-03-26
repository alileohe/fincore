package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能: 创建底层资产请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 11:17
 * 版本: V1.0
 */
public class CreateAssetRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -2320999302741468731L;

    @NotBlank(message = "资产名称不能为空")
    private String assetName;

    @NotNull(message = "资产类型不能为空")
    private Integer assetType;

    /**
     * 融资项目
     */
    private String financeProject;

    /**
     * 项目描述
     */
    @NotBlank(message = "项目描述不能为空")
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

    @NotNull(message = "资产总规模不能为空")
    @DecimalMin(value = "0.00", message = "资产总规模必须大于零")
    private BigDecimal assetAmount;

    private BigDecimal stockAmount;

    private BigDecimal frozenAmount;

    private BigDecimal saleAmount;

    private Long financeSubjectId;

//    @NotBlank(message = "出资方编码不能为空")
    private String provideLoanCode;

    @NotBlank(message = "融资方编码不能为空")
    private String financeSubjectCode;

    private Date collectStartTime;

    private Date collectEndTime;

    @NotNull(message = "成立日不能为空")
    private Date establishTime;

    @NotNull(message = "起息日不能为空")
    private Date valueStartTime;

    @NotNull(message = "结息日不能为空")
    private Date valueEndTime;

    @NotNull(message = "到期日不能为空")
    private Date expireTime;

    private Integer transferLockDays;

    @NotNull(message = "期限不能为空")
    private Integer valueDays;

    private BigDecimal yieldRate;

    private BigDecimal minInvestAmount;

    private BigDecimal increaseAmount;

    @NotNull(message = "还款方式不能为空")
    private Integer repaymentType;

    private String assetDesc;

    @NotBlank(message = "录入人不能为空")
    private String createBy;

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
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
        this.approvalRequireSign = approvalRequireSign;
    }

    public String getApprovalSign() {
        return approvalSign;
    }

    public void setApprovalSign(String approvalSign) {
        this.approvalSign = approvalSign;
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

    public Date getCollectStartTime() {
        return collectStartTime;
    }

    public void setCollectStartTime(Date collectStartTime) {
        this.collectStartTime = collectStartTime;
    }

    public Date getCollectEndTime() {
        return collectEndTime;
    }

    public void setCollectEndTime(Date collectEndTime) {
        this.collectEndTime = collectEndTime;
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
        this.assetDesc = assetDesc;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getProvideLoanCode() {
        return provideLoanCode;
    }

    public void setProvideLoanCode(String provideLoanCode) {
        this.provideLoanCode = provideLoanCode;
    }
}
