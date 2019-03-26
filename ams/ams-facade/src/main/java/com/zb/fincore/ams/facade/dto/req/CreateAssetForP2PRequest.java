package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 功能: P2P创建资产请求
 */
public class CreateAssetForP2PRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -2320999302741468731L;

    @NotBlank(message = "外部编码不能为空")
    private String extAssetCode;//马上贷资产编码

    @NotNull(message = "合同金额不能为空")
    private BigDecimal assetAmount;//募集金额(合同金额)

    @NotNull(message = "合同天数不能为空")
    private Integer valueDays;//期限(合同天数)

    @NotNull(message = "合同利率不能为空")
    private BigDecimal yieldRate;//预期年化利率(合同利率)

    @NotNull(message = "还款方式不能为空")
    private Integer repaymentType;//还款方式

    @NotBlank(message = "会员ID不能为空")
    private String memberId;//会员ID

    @NotBlank(message = "借款人姓名不能为空")
    private String loanName;//借款人姓名

    @NotBlank(message = "借款人证件号不能为空")
    private String loanCertNo;//借款人证件号

    @NotNull(message = "证件类型不能为空")
    private Integer loanCertType;//证件类型

    @NotNull(message = "还款利息不能为空")
    private BigDecimal repayInterest;//还款利息

    @NotNull(message = "借款手续费不能为空")
    private BigDecimal loanFee;//借款手续费

    @NotBlank(message = "起息日不能为空")
    private String valueStartTime;//起息日

    @NotBlank(message = "止息日不能为空")
    private String valueEndTime;//止息日

    @NotBlank(message = "申请借款时间不能为空")
    private String loanApplyTime;//申请贷款时间

    @NotBlank(message = "贷款用途不能为空")
    private String loanPurpose;

//    @NotBlank(message = "联系地址不能为空")
    private String contactAddress;

    @NotBlank(message = "联系方式不能为空")
    private String tel;

    public String getExtAssetCode() {
        return extAssetCode;
    }

    public void setExtAssetCode(String extAssetCode) {
        this.extAssetCode = extAssetCode;
    }

    public BigDecimal getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(BigDecimal assetAmount) {
        this.assetAmount = assetAmount;
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

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public String getLoanCertNo() {
        return loanCertNo;
    }

    public void setLoanCertNo(String loanCertNo) {
        this.loanCertNo = loanCertNo;
    }

    public Integer getLoanCertType() {
        return loanCertType;
    }

    public void setLoanCertType(Integer loanCertType) {
        this.loanCertType = loanCertType;
    }

    public BigDecimal getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(BigDecimal repayInterest) {
        this.repayInterest = repayInterest;
    }

    public BigDecimal getLoanFee() {
        return loanFee;
    }

    public void setLoanFee(BigDecimal loanFee) {
        this.loanFee = loanFee;
    }

    public String getValueStartTime() {
        return valueStartTime;
    }

    public void setValueStartTime(String valueStartTime) {
        this.valueStartTime = valueStartTime;
    }

    public String getValueEndTime() {
        return valueEndTime;
    }

    public void setValueEndTime(String valueEndTime) {
        this.valueEndTime = valueEndTime;
    }

    public String getLoanApplyTime() {
        return loanApplyTime;
    }

    public void setLoanApplyTime(String loanApplyTime) {
        this.loanApplyTime = loanApplyTime;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
