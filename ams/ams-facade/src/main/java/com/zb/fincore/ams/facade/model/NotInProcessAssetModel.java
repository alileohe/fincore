package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

public class NotInProcessAssetModel extends BaseModel {

	private static final long serialVersionUID = -4923954050618177216L;
	
	private String valueStartTime;
	
	private Integer valueDays;
	
	private String expireDate;
	
	private Double assetAmount;
	
	private Integer assetCount;

	public String getValueStartTime() {
		return valueStartTime;
	}

	public void setValueStartTime(String valueStartTime) {
		this.valueStartTime = valueStartTime;
	}

	public Integer getValueDays() {
		return valueDays;
	}

	public void setValueDays(Integer valueDays) {
		this.valueDays = valueDays;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public Double getAssetAmount() {
		return assetAmount;
	}

	public void setAssetAmount(Double assetAmount) {
		this.assetAmount = assetAmount;
	}

	public Integer getAssetCount() {
		return assetCount;
	}

	public void setAssetCount(Integer assetCount) {
		this.assetCount = assetCount;
	}

}
