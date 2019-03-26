package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 功能: 变更产品资产占用情况请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 10:34
 * 版本: V1.0
 */
public class ChangeAssetProductModel extends BaseModel {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -3096488846839200644L;

    /**
     * 资产池主键
     */
//    @NotNull(message = "资产池ID不能为空")
    private Long poolId;

    /**
     * 资产池编码
     */
    @NotBlank(message = "资产池编码不能为空")
    private String poolCode;

    /**
     * 资产编码
     */
//    @NotNull(message = "资产ID不能为空")
    private Long assetId;

    /**
     * 资产编码
     */
    @NotBlank(message = "资产编码不能为空")
    private String assetCode;

    /**
     * 变更类型
     */
    @NotNull(message = "变更类型不能为空")
    private Integer changeType;

    /**
     * 变更金额
     */
//    @NotNull(message = "变更金额不能为空")
    private BigDecimal changeAmount;

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
        this.poolCode = poolCode;
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
        this.assetCode = assetCode;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }
}
