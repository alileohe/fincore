/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.boss.web.controller.pms.vo;

import java.util.List;

import com.zillionfortune.boss.biz.pms.dto.ProductLadderRegisterModel;
import com.zillionfortune.boss.dal.entity.FileInfoConvert;

/**
 * ClassName: ProductRegisterRequestVo <br/>
 * Function: 产品注册用Vo. <br/>
 * Date: 2017年5月9日 上午11:06:45 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class ProductRegisterRequestVo {
	/** productName:产品名称 **/
	private String productName;
	
	/** productLineId:产品线Id **/
	private String productLineId;
	
	/** saleChannelCode:销售渠道 **/
	private String saleChannelCode;
	
	/** riskLevel:风险等级 **/
	private String riskLevel;
	
	/** productDisplayName:展示名称 **/
	private String productDisplayName;
	
	/** patternCode:产品类型 **/
	private String patternCode;
	
	/** joinChannelCode:接入渠道 **/
	private String joinChannelCode;
	
	/** calendarMode:日历模式 **/
	private String calendarMode;
	
	/** saleStatus:销售状态 **/
	private String saleStatus;
	
	/** collectStatus:募集状态 **/
	private String collectStatus;
	
	/** informationDisclosure:信息披露 **/
	private List<FileInfoConvert> informationDisclosure;
	
	/** productLadderList:阶梯收益列表 **/
	private List<ProductLadderRegisterModel> productLadderList;
	
	/** introduction:产品介绍 **/
	private String introduction;
	
	/** totalAmount:募集总规模 **/
	private String totalAmount;
	
	/** increaseAmount:步长（递增金额） **/
	private String increaseAmount;
	
	/** assetPoolType:资产池类型 **/
	private String assetPoolType;
	
	/** maxInvestAmount:个人限投 **/
	private String maxInvestAmount;
	
	/** minInvestAmount:起购金额 **/
	private String minInvestAmount;
	
	/** assetPoolCode:资产池编号 **/
	private String assetPoolCode;
	
	/** assetPoolName:资产池名称 **/
	private String assetPoolName;
	
	/** minHoldAmount:最低可持有金额 **/
	private String minHoldAmount;
	
	/** fundSettleParty:资金结算方 **/
	private String fundSettleParty;
	
	/** maxYieldRate:预期年化收益率上限 **/
	private String maxYieldRate;
	
	/** minYieldRate:预期年化收益率下限 **/
	private String minYieldRate;
	
	/** saleStartTime:募集起始日 **/
	private String saleStartTime;
	
	/** saleEndTime:募集截止日 **/
	private String saleEndTime;
	
	/** valueTime:起息日 **/
	private String valueTime;
	
	/** investPeriodLoopUnit:循环周期 **/
	private String investPeriodLoopUnit;
	
	/** expireTime:到期日（预期） **/
	private String expireTime;
	
	/** expectClearTime:到期回款日（预期结清时间） **/
	private String expectClearTime;
	
	/** investPeriod:产品期限 **/
	private String investPeriod;
	
	/** investPeriodUnit:投资期限单位 **/
	private String investPeriodUnit;
	
	/** currentYieldRate:当期利率 **/
	private String currentYieldRate;
	
	/** floatingYieldRate:浮动利率 **/
	private String floatingYieldRate;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductLineId() {
		return productLineId;
	}

	public void setProductLineId(String productLineId) {
		this.productLineId = productLineId;
	}

	public String getSaleChannelCode() {
		return saleChannelCode;
	}

	public void setSaleChannelCode(String saleChannelCode) {
		this.saleChannelCode = saleChannelCode;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getProductDisplayName() {
		return productDisplayName;
	}

	public void setProductDisplayName(String productDisplayName) {
		this.productDisplayName = productDisplayName;
	}

	public String getPatternCode() {
		return patternCode;
	}

	public void setPatternCode(String patternCode) {
		this.patternCode = patternCode;
	}

	public String getJoinChannelCode() {
		return joinChannelCode;
	}

	public void setJoinChannelCode(String joinChannelCode) {
		this.joinChannelCode = joinChannelCode;
	}

	public String getCalendarMode() {
		return calendarMode;
	}

	public void setCalendarMode(String calendarMode) {
		this.calendarMode = calendarMode;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getCollectStatus() {
		return collectStatus;
	}

	public void setCollectStatus(String collectStatus) {
		this.collectStatus = collectStatus;
	}

	public List<FileInfoConvert> getInformationDisclosure() {
		return informationDisclosure;
	}

	public void setInformationDisclosure(List<FileInfoConvert> informationDisclosure) {
		this.informationDisclosure = informationDisclosure;
	}

	public List<ProductLadderRegisterModel> getProductLadderList() {
		return productLadderList;
	}

	public void setProductLadderList(List<ProductLadderRegisterModel> productLadderList) {
		this.productLadderList = productLadderList;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getIncreaseAmount() {
		return increaseAmount;
	}

	public void setIncreaseAmount(String increaseAmount) {
		this.increaseAmount = increaseAmount;
	}

	public String getAssetPoolType() {
		return assetPoolType;
	}

	public void setAssetPoolType(String assetPoolType) {
		this.assetPoolType = assetPoolType;
	}

	public String getMaxInvestAmount() {
		return maxInvestAmount;
	}

	public void setMaxInvestAmount(String maxInvestAmount) {
		this.maxInvestAmount = maxInvestAmount;
	}

	public String getMinInvestAmount() {
		return minInvestAmount;
	}

	public void setMinInvestAmount(String minInvestAmount) {
		this.minInvestAmount = minInvestAmount;
	}

	public String getAssetPoolCode() {
		return assetPoolCode;
	}

	public void setAssetPoolCode(String assetPoolCode) {
		this.assetPoolCode = assetPoolCode;
	}

	public String getAssetPoolName() {
		return assetPoolName;
	}

	public void setAssetPoolName(String assetPoolName) {
		this.assetPoolName = assetPoolName;
	}

	public String getMinHoldAmount() {
		return minHoldAmount;
	}

	public void setMinHoldAmount(String minHoldAmount) {
		this.minHoldAmount = minHoldAmount;
	}

	public String getFundSettleParty() {
		return fundSettleParty;
	}

	public void setFundSettleParty(String fundSettleParty) {
		this.fundSettleParty = fundSettleParty;
	}

	public String getMaxYieldRate() {
		return maxYieldRate;
	}

	public void setMaxYieldRate(String maxYieldRate) {
		this.maxYieldRate = maxYieldRate;
	}

	public String getMinYieldRate() {
		return minYieldRate;
	}

	public void setMinYieldRate(String minYieldRate) {
		this.minYieldRate = minYieldRate;
	}

	public String getSaleStartTime() {
		return saleStartTime;
	}

	public void setSaleStartTime(String saleStartTime) {
		this.saleStartTime = saleStartTime;
	}

	public String getSaleEndTime() {
		return saleEndTime;
	}

	public void setSaleEndTime(String saleEndTime) {
		this.saleEndTime = saleEndTime;
	}

	public String getValueTime() {
		return valueTime;
	}

	public void setValueTime(String valueTime) {
		this.valueTime = valueTime;
	}

	public String getInvestPeriodLoopUnit() {
		return investPeriodLoopUnit;
	}

	public void setInvestPeriodLoopUnit(String investPeriodLoopUnit) {
		this.investPeriodLoopUnit = investPeriodLoopUnit;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	
	public String getExpectClearTime() {
		return expectClearTime;
	}

	public void setExpectClearTime(String expectClearTime) {
		this.expectClearTime = expectClearTime;
	}

	public String getInvestPeriod() {
		return investPeriod;
	}

	public void setInvestPeriod(String investPeriod) {
		this.investPeriod = investPeriod;
	}

	public String getInvestPeriodUnit() {
		return investPeriodUnit;
	}

	public void setInvestPeriodUnit(String investPeriodUnit) {
		this.investPeriodUnit = investPeriodUnit;
	}

	public String getCurrentYieldRate() {
		return currentYieldRate;
	}

	public void setCurrentYieldRate(String currentYieldRate) {
		this.currentYieldRate = currentYieldRate;
	}

	public String getFloatingYieldRate() {
		return floatingYieldRate;
	}

	public void setFloatingYieldRate(String floatingYieldRate) {
		this.floatingYieldRate = floatingYieldRate;
	}
	
}
