package com.zb.fincore.ams.service.dal.model;

import com.zb.fincore.ams.common.model.BaseDo;

import java.math.BigDecimal;

public class AssetPoolRelation extends BaseDo {

    private Long poolId;

    private String poolCode;

    private Long assetId;

    private String assetCode;

    private BigDecimal relationAmount;

    private BigDecimal stockAmount;

    private BigDecimal frozenAmount;

    private BigDecimal saleAmount;

    private Long version;

    private Asset asset;

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

    public BigDecimal getRelationAmount() {
        return relationAmount;
    }

    public void setRelationAmount(BigDecimal relationAmount) {
        this.relationAmount = relationAmount;
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

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}