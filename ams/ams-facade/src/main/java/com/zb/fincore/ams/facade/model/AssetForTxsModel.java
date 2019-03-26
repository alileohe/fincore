package com.zb.fincore.ams.facade.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 唐小僧资产model
 * Created by MABIAO on 2017/7/7 0007.
 */
public class AssetForTxsModel implements Serializable {

    private static final long serialVersionUID = 5239602616906090570L;

    private String assetName;//资产名称
    private String assetCode;//资产编码
    private String registerName;//备案名称
    private Integer productDayCount;//产品期限
    private Date saleStartTime;//募集开始日
    private Date saleEndTime;//募集结束
    private BigDecimal assetAmount;//募集金额
    private BigDecimal yieldRate;//产品收益率
    private Date productValueStartTime;//产品起息日
    private Date productValueEndTime;//产品结息日
    private String tradeInstName;//交易所
    private String listedInstName;//挂牌方
    private String delistedInstName;//摘牌方
    private String assetType;//融资类型
    private String assetDisclosure;//披露信息

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

    public Integer getProductDayCount() {
        return productDayCount;
    }

    public void setProductDayCount(Integer productDayCount) {
        this.productDayCount = productDayCount;
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

    public String getTradeInstName() {
        return tradeInstName;
    }

    public void setTradeInstName(String tradeInstName) {
        this.tradeInstName = tradeInstName;
    }

    public String getListedInstName() {
        return listedInstName;
    }

    public void setListedInstName(String listedInstName) {
        this.listedInstName = listedInstName;
    }

    public String getDelistedInstName() {
        return delistedInstName;
    }

    public void setDelistedInstName(String delistedInstName) {
        this.delistedInstName = delistedInstName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetDisclosure() {
        return assetDisclosure;
    }

    public void setAssetDisclosure(String assetDisclosure) {
        this.assetDisclosure = assetDisclosure;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }
}
