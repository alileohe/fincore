package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 发行计划请求
 * Created by MABIAO on 2017/6/30 0030.
 */
public class CreateIssuePlanRequest extends BaseRequest{

    @NotNull(message = "产品天数不能为空")
    private Integer productDayCount;

    @NotNull(message = "合同天数不能为空")
    private Integer contractDayCount;

    @NotNull(message = "备案结束日期不能为空")
    private Date valueStartTime;

    @NotNull(message = "备案完成日期不能为空")
    private Date valueEndTime;

//    @NotNull(message = "空档开始日期不能为空")
    private Date freeStartTime;

//    @NotNull(message = "空档结束日期不能为空")
    private Date freeEndTime;

    @NotNull(message = "募集开始日期不能为空")
    private Date saleStartTime;

    @NotNull(message = "募集结束日期不能为空")
    private Date saleEndTime;

    @NotNull(message = "产品计息不能为空")
    private Date productValueStartTime;

    @NotNull(message = "产品结息日不能为空")
    private Date productValueEndTime;

    @NotNull(message = "单包金额不能为空")
    private BigDecimal singleAmount;

    @NotNull(message = "个数不能为空")
    private Integer assetCount;

    @NotNull(message = "总金额不能为空")
    private BigDecimal totalAmount;

    @NotBlank(message = "融资方名称不能为空")
    private String subjectName;

    public Integer getProductDayCount() {
        return productDayCount;
    }

    public void setProductDayCount(Integer productDayCount) {
        this.productDayCount = productDayCount;
    }

    public Integer getContractDayCount() {
        return contractDayCount;
    }

    public void setContractDayCount(Integer contractDayCount) {
        this.contractDayCount = contractDayCount;
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

    public Date getFreeStartTime() {
        return freeStartTime;
    }

    public void setFreeStartTime(Date freeStartTime) {
        this.freeStartTime = freeStartTime;
    }

    public Date getFreeEndTime() {
        return freeEndTime;
    }

    public void setFreeEndTime(Date freeEndTime) {
        this.freeEndTime = freeEndTime;
    }

    public Date getSaleStartTime() {
        return saleStartTime;
    }

    public void setSaleStartTime(Date saleStartTime) {
        this.saleStartTime = saleStartTime;
    }

    public Date getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(Date saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    public Date getProductValueStartTime() {
        return productValueStartTime;
    }

    public void setProductValueStartTime(Date productValueStartTime) {
        this.productValueStartTime = productValueStartTime;
    }

    public Date getProductValueEndTime() {
        return productValueEndTime;
    }

    public void setProductValueEndTime(Date productValueEndTime) {
        this.productValueEndTime = productValueEndTime;
    }

    public BigDecimal getSingleAmount() {
        return singleAmount;
    }

    public void setSingleAmount(BigDecimal singleAmount) {
        this.singleAmount = singleAmount;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
