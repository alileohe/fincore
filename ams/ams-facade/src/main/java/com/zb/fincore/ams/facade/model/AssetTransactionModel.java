package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

public class AssetTransactionModel extends BaseModel {

	private static final long serialVersionUID = -7528677019653303540L;
	
	private String registerName;

	private String listedInstName;

	private String exchangeName;
	
	private String delistedInstName;
	
	private Double saleAmount;
	
	private String saleDate;
	
	private Double paybackAmount;
	
	private String paybackDate;
	
	private Integer saleDurationDays;

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public String getListedInstName() {
		return listedInstName;
	}

	public void setListedInstName(String listedInstName) {
		this.listedInstName = listedInstName;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getDelistedInstName() {
		return delistedInstName;
	}

	public void setDelistedInstName(String delistedInstName) {
		this.delistedInstName = delistedInstName;
	}

	public Double getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(Double saleAmount) {
		this.saleAmount = saleAmount;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public Double getPaybackAmount() {
		return paybackAmount;
	}

	public void setPaybackAmount(Double paybackAmount) {
		this.paybackAmount = paybackAmount;
	}

	public String getPaybackDate() {
		return paybackDate;
	}

	public void setPaybackDate(String paybackDate) {
		this.paybackDate = paybackDate;
	}

	public Integer getSaleDurationDays() {
		return saleDurationDays;
	}

	public void setSaleDurationDays(Integer saleDurationDays) {
		this.saleDurationDays = saleDurationDays;
	}
}
