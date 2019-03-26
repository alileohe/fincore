package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;

/**
 * 功能: 创建底层资产响应
 * 创建: lijun@zillionfortune.com
 * 日期: 2017/8/30 0019 11:18
 * 版本: V1.0
 */
public class BorrowerInfoResponse extends BaseResponse {

    /**
     * SerialVersionUID
     */
	private static final long serialVersionUID = -8263646260603747755L;

	/**
     * 主键ID
     */
    private Long id;

    /**
     * 借款人姓名
     */
    private String loanName;
    
    /**
     * 借款人证件号
     */
    private String loanCertNo;

    /**
     * 融资类型
     */
    private String assetType;
    
    /**
     * 还款状态
     */
    private String repayStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getLoanCertNo() {
		return loanCertNo;
	}

	public void setLoanCertNo(String loanCertNo) {
		this.loanCertNo = loanCertNo;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getRepayStatus() {
		return repayStatus;
	}

	public void setRepayStatus(String repayStatus) {
		this.repayStatus = repayStatus;
	}
    
}
