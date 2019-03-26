package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

public class InProcessAssetModel extends BaseModel {

	private static final long serialVersionUID = 2670798529784998044L;
	
	private String dayLeft;
	
	private String expireDay;
	
	private Double stockAmount;
	
	private Integer assetCount;

	public String getDayLeft() {
		return dayLeft;
	}

	public void setDayLeft(String dayLeft) {
		this.dayLeft = dayLeft;
	}

	public String getExpireDay() {
		return expireDay;
	}

	public void setExpireDay(String expireDay) {
		this.expireDay = expireDay;
	}

	public Double getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(Double stockAmount) {
		this.stockAmount = stockAmount;
	}

	public Integer getAssetCount() {
		return assetCount;
	}

	public void setAssetCount(Integer assetCount) {
		this.assetCount = assetCount;
	}

}
