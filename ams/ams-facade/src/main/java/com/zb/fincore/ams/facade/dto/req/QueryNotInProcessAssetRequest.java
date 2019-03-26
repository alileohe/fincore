package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;

public class QueryNotInProcessAssetRequest extends BaseRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4803479751722311079L;
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
