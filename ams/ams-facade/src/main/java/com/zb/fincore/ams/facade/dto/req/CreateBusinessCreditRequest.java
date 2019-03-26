package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class CreateBusinessCreditRequest extends BaseRequest {

    @NotBlank(message = "授信业务编号不能为空")
    private String creditBusinessCode;//授信业务编号

    @NotNull(message = "授信金额不能为空")
    private BigDecimal creditLimitAmount;//授信金额

    @NotBlank(message = "融资方不能为空")
    private String financingName;

    @NotBlank(message = "证件号码不能为空")
    private String certNo;

    @NotBlank(message = "法人代表不能为空")
    private String legalPersonName;

    @NotBlank(message = "联系方式不能为空")
    private String contactWay;

    @NotBlank(message = "地址不能为空")
    private String address;

    @NotBlank(message = "用途不能为空")
    private String financingPurpose;

    @NotNull(message = "收益率不能为空")
    private BigDecimal yieldRate;

    @NotNull(message = "起息日不能为空")
    private Date valueStartTime;

    @NotNull(message = "结息日不能为空")
    private Date valueEndTime;

    @NotNull(message = "天数不能为空")
    private Integer dayCount;

    @NotBlank(message = "还款方式不能为空")
    private String repaymentType;

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
}