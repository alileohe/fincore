package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 功能: 创建资产池请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 11:17
 * 版本: V1.0
 */
public class CreatePoolRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -1800430342768398488L;

    @NotBlank(message = "资产池名称不能为空")
    private String poolName;

    @NotNull(message = "资产池类型不能为空")
    private Integer poolType;

//    @NotNull(message = "资产池总额上限不能为空")
//    @DecimalMin(value = "0.00", message = "资产池总额上限必须大于等于零")
    private BigDecimal limitAmount;

    //@NotNull(message = "融资方ID不能为空")
    //private Long financeSubjectId;

    @NotBlank(message = "融资方编码不能为空")
    private String financeSubjectCode;

    //@NotNull(message = "受托方ID不能为空")
    //private Long trusteeId;

    @NotBlank(message = "受托方编码不能为空")
    private String trusteeCode;

    @NotBlank(message = "录入人不能为空")
    private String createBy;

    private String poolDesc;

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
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

    public String getFinanceSubjectCode() {
        return financeSubjectCode;
    }

    public void setFinanceSubjectCode(String financeSubjectCode) {
        this.financeSubjectCode = financeSubjectCode;
    }

    public String getTrusteeCode() {
        return trusteeCode;
    }

    public void setTrusteeCode(String trusteeCode) {
        this.trusteeCode = trusteeCode;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getPoolDesc() {
        return poolDesc;
    }

    public void setPoolDesc(String poolDesc) {
        this.poolDesc = poolDesc;
    }
}
