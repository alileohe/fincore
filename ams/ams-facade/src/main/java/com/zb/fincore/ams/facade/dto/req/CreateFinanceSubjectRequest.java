package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 功能: 融资方请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 14:09
 * 版本: V1.0
 */
public class CreateFinanceSubjectRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -1310975109724405715L;

    @NotBlank(message = "融资方名称不能为空")
    private String subjectName;

    /**
     * 融资方展示名
     */
    private String subjectShowName;

    @NotNull(message = "证件类型不能为空")
    private Integer certType;

    @NotBlank(message = "证件号码不能为空")
    private String certNo;

    /**
     * 证件号码展示名称
     */
    private String certNoShowName;

    @NotNull(message = "注册资本不能为空")
    @DecimalMin(value = "0.00",message = "注册资本必须大于0")
    private BigDecimal registeredCapital;

    @NotBlank(message = "公司注册地址不能为空")
    private String subjectAddress;

    private String subjectAddressShowName;//地址展示名

    @NotBlank(message = "法人代表姓名不能为空")
    private String legalPersonName;

    @NotBlank(message = "法人代表身份证号码不能为空")
    private String legalPersonCertNo;


    private String businessScope;

    private String introduction;

    @NotBlank(message = "录入人不能为空")
    private String createBy;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public String getSubjectAddress() {
        return subjectAddress;
    }

    public void setSubjectAddress(String subjectAddress) {
        this.subjectAddress = subjectAddress;
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

    public String getSubjectShowName() {
        return subjectShowName;
    }

    public void setSubjectShowName(String subjectShowName) {
        this.subjectShowName = subjectShowName;
    }

    public String getCertNoShowName() {
        return certNoShowName;
    }

    public void setCertNoShowName(String certNoShowName) {
        this.certNoShowName = certNoShowName;
    }

    public String getSubjectAddressShowName() {
        return subjectAddressShowName;
    }

    public void setSubjectAddressShowName(String subjectAddressShowName) {
        this.subjectAddressShowName = subjectAddressShowName;
    }
}
