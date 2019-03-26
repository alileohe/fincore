package com.zb.fincore.ams.facade.dto.req;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

import com.zb.fincore.ams.common.dto.BaseRequest;

/**
 * 功能: 更新资产库存请求
 * 创建: lijun@zillionfortune.com
 * 日期: 2017/9/04 0019 11:17
 * 版本: V1.0
 */
public class UpdateAssetStockRequest extends BaseRequest {
	
	/**
     * SerialVersionUID
     */
	private static final long serialVersionUID = -936601904904502780L;

	
	@NotBlank(message = "外部资产编码不能为空")
    private String extAssetCode;
	
//    @NotBlank(message = "资产金额不能为空")
    private BigDecimal assetAmount;
    
    
	public String getExtAssetCode() {
		return extAssetCode;
	}

	public void setExtAssetCode(String extAssetCode) {
		this.extAssetCode = extAssetCode;
	}

	public BigDecimal getAssetAmount() {
		return assetAmount;
	}

	public void setAssetAmount(BigDecimal assetAmount) {
		this.assetAmount = assetAmount;
	}
	
}
