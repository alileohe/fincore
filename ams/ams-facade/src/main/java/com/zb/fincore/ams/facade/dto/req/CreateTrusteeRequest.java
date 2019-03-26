package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 功能: 创建受托方请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 17:02
 * 版本: V1.0
 */
public class CreateTrusteeRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 7128900658727929443L;


    @NotBlank(message = "受托方名称不能为空")
    private String trusteeName;

    @NotNull(message = "证件类型不能为空")
    private Integer certType;

    @NotBlank(message = "证件号码不能为空")
    private String certNo;

    @NotNull(message = "注册资本不能为空")
    @DecimalMin(value = "0.00",message = "注册资本必须大于0")
    private BigDecimal registeredCapital;

    @NotBlank(message = "公司注册地址不能为空")
    private String trusteeAddress;

    private String trusteeAddressShowName;

    @NotBlank(message = "法人代表姓名不能为空")
    private String legalPersonName;

    @NotBlank(message = "法人代表身份证号码不能为空")
    private String legalPersonCertNo;

    @NotBlank(message = "经营范围不能为空")
    private String businessScope;

    private String introduction;

    @NotBlank(message = "录入人不能为空")
    private String createBy;

    /**
     * 受托方展示名
     */
    private String trusteeShowName;

    /**
     * 证件号码展示名
     */
    private String certNoShowName;

    public String getTrusteeName() {
        return trusteeName;
    }

    public void setTrusteeName(String trusteeName) {
        this.trusteeName = trusteeName;
    }

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getTrusteeAddress() {
        return trusteeAddress;
    }

    public void setTrusteeAddress(String trusteeAddress) {
        this.trusteeAddress = trusteeAddress;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonCertNo() {
        return legalPersonCertNo;
    }

    public void setLegalPersonCertNo(String legalPersonCertNo) {
        this.legalPersonCertNo = legalPersonCertNo;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getTrusteeShowName() {
        return trusteeShowName;
    }

    public void setTrusteeShowName(String trusteeShowName) {
        this.trusteeShowName = trusteeShowName;
    }

    public String getCertNoShowName() {
        return certNoShowName;
    }

    public void setCertNoShowName(String certNoShowName) {
        this.certNoShowName = certNoShowName;
    }

    public String getTrusteeAddressShowName() {
        return trusteeAddressShowName;
    }

    public void setTrusteeAddressShowName(String trusteeAddressShowName) {
        this.trusteeAddressShowName = trusteeAddressShowName;
    }
}
