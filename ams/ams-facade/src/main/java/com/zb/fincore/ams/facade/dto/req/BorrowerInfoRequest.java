package com.zb.fincore.ams.facade.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import com.zb.fincore.ams.common.dto.BaseRequest;

/**
 * 功能: 借款人信息请求
 * 创建: lijun@zillionfortune.com
 * 日期: 2017/4/19 0019 11:17
 * 版本: V1.0
 */
public class BorrowerInfoRequest extends BaseRequest {

	/**
     * SerialVersionUID
     */
	private static final long serialVersionUID = -7714252994667203821L;
	
    @NotBlank(message = "资产编号不能为空")
    private String assetCode;

    private String productCode;
    
	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
    
}
