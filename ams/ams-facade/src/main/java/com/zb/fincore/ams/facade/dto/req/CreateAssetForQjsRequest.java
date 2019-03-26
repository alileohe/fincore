package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能: Qjs创建资产请求
 */
public class CreateAssetForQjsRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -2320999302741468731L;

    @NotBlank(message = "底层资产合同编号不能为空")
    private String contractAssetCode;//资产合同编码

    @NotBlank(message = "资产名称不能为空")
    private String assetName;//资产名称

    @NotBlank(message = "备案名称不能为空")
    private String registerName;//备案名称

    @NotNull(message = "期限不能为空")
    private Integer valueDays;//产品期限

    @NotNull(message = "募集开始日不能为空")
    private Date saleStartTime;//募集开始日

    @NotNull(message = "募集结束日不能为空")
    private Date saleEndTime;//募集结束日

    @NotNull(message = "备案金额不能为空")
    private BigDecimal assetAmount;//备案金额

    @NotNull(message = "产品收益率不能为空")
    private BigDecimal yieldRate;//预期年化利率

    @NotNull(message = "起息日不能为空")
    private Date valueStartTime;//起息日

    @NotNull(message = "止息日不能为空")
    private Date valueEndTime;//止息日

    @NotBlank(message = "交易所编码不能为空")
    private String tradeInstCode;//交易所编码

    @NotBlank(message = "挂牌方编码不能为空")
    private String listedInstCode;//挂牌方编码

    @NotBlank(message = "摘牌方编码不能为空")
    private String delistedInstCode;//摘牌方编码

    @NotNull(message = "融资类型不能为空")
    private Integer assetType;

    @NotNull(message = "还款方式不能为空")
    private Integer repaymentType;//还款方式


    public String getContractAssetCode() {
        return contractAssetCode;
    }

    public void setContractAssetCode(String contractAssetCode) {
        this.contractAssetCode = contractAssetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public Integer getValueDays() {
        return valueDays;
    }

    public void setValueDays(Integer valueDays) {
        this.valueDays = valueDays;
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

    public BigDecimal getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(BigDecimal assetAmount) {
        this.assetAmount = assetAmount;
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

    public String getTradeInstCode() {
        return tradeInstCode;
    }

    public void setTradeInstCode(String tradeInstCode) {
        this.tradeInstCode = tradeInstCode;
    }

    public String getListedInstCode() {
        return listedInstCode;
    }

    public void setListedInstCode(String listedInstCode) {
        this.listedInstCode = listedInstCode;
    }

    public String getDelistedInstCode() {
        return delistedInstCode;
    }

    public void setDelistedInstCode(String delistedInstCode) {
        this.delistedInstCode = delistedInstCode;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }
}
