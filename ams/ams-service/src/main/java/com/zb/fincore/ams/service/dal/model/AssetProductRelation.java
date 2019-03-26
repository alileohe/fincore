package com.zb.fincore.ams.service.dal.model;

import com.zb.fincore.ams.common.model.BaseDo;

import java.math.BigDecimal;
import java.util.Date;

public class AssetProductRelation extends BaseDo {

    private String productCode;

    private Long poolId;

    private String poolCode;

    private Long assetId;

    private String assetCode;

    private BigDecimal assetAmount;

    private BigDecimal saleAmount;

    private BigDecimal releaseAmount;

    private Date productExpireTime;

    private Long version;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 资产到期时间
     */
    private Date assetExpireTime;

    private Integer assetType;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public Long getPoolId() {
        return poolId;
    }

    public void setPoolId(Long poolId) {
        this.poolId = poolId;
    }

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode == null ? null : poolCode.trim();
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    public BigDecimal getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(BigDecimal assetAmount) {
        this.assetAmount = assetAmount;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public BigDecimal getReleaseAmount() {
        return releaseAmount;
    }

    public void setReleaseAmount(BigDecimal releaseAmount) {
        this.releaseAmount = releaseAmount;
    }

    public Date getProductExpireTime() {
        return productExpireTime;
    }

    public void setProductExpireTime(Date productExpireTime) {
        this.productExpireTime = productExpireTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Date getAssetExpireTime() {
        return assetExpireTime;
    }

    public void setAssetExpireTime(Date assetExpireTime) {
        this.assetExpireTime = assetExpireTime;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }
}