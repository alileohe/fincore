package com.zb.fincore.pms.facade.product.dto.req;

import com.zb.fincore.pms.common.dto.BaseRequest;
import com.zb.fincore.pms.facade.product.model.ProductContractModel;
import com.zb.fincore.pms.facade.product.model.ProductLadderModel;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 产品注册请求对象
 *
 * @author
 * @create 2017-04-10 10:32
 */
public class RegisterProductRequest extends BaseRequest{


    /*********************产品注册基本信息字段 start *****************************/
    private Long productId;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空")
    private String productName;

    /**
     * 产品展示名称
     */
    @NotBlank(message = "产品展示名称不能为空")
    private String productDisplayName;

    /**
     * 产品线主键
     */
    @NotNull(message = "产品所属产品线不能为空")
    private Long productLineId;

    /**
     * 产品线编号
     */
    private String productLineCode;

    /**
     * 产品线名称
     */
    private String productLineName;

    /**
     * 资产池类型
     */
    @NotNull(message = "产品关联的资产池类型不能为空")
    private Integer assetPoolType;

    /**
     * 资产池编号
     */
    @NotBlank(message = "产品关联的资产池编号不能为空")
    private String assetPoolCode;

    /**
     * 资产池名称
     */
    @NotBlank(message = "产品关联的资产池名称不能为空")
    private String assetPoolName;

    /**
     * 产品形态代码
     */
    @NotBlank(message = "产品形态代码不能为空")
    private String patternCode;

    /**
     * 销售渠道代码
     */
    @NotBlank(message = "产品销售渠道不能为空")
    private String saleChannelCode;

    /**
     * 接入渠道
     */
    @NotBlank(message = "产品接入渠道不能为空")
    private String joinChannelCode;

    /**
     * 产品总规模
     */
    @NotNull(message = "产品规模不能为空")
    @DecimalMin(value = "0.00", message = "产品规模必须大于等于零")
    private BigDecimal totalAmount;

    /**
     * 销售状态
     */
    private Integer saleStatus;

    /**
     * 募集状态
     */
    private Integer collectStatus;

    /**
     * 产品风险等级
     */
    @NotBlank(message = "产品风险等级不能为空")
    private String riskLevel;

    /**
     * 资金结算方
     */
    @NotBlank(message = "资金结算方不能为空")
    private String fundSettleParty;

    /**
     * 是否对港澳台客户开放
     */
    private Integer isOpenHMT;

    /**
     * 日历模式
     */
    @NotNull(message = "日历模式不能为空")
    private Integer calendarMode;

    /**
     * 产品介绍
     */
    private String introduction;

    /**
     * 审核需要签名
     */
    private String approvalRequireSign;

    /**
     * 已审核签名
     */
    private String approvalSign;

    /**
     * 审核状态
     */
    private Integer approvalStatus;

    /**
     * 归档时间
     */
    private Date archiveTime;
    /*********************产品注册基本信息字段 end *****************************/


    /*********************产品注册期限信息字段 start *****************************/
    /**
     * 预期上线时间
     */
    private Date expectOnlineTime;

    /**
     * 预期下线时间
     */
    private Date expectOfflineTime;

    /**
     * 募集起始时间
     */
    @NotNull(message = "产品募集起始时间不能为空")
    private Date saleStartTime;

    /**
     * 募集截止时间
     */
    @NotNull(message = "产品募集截止时间不能为空")
    private Date saleEndTime;

    /**
     * 预期成立时间
     */
    private Date expectEstablishTime;

    /**
     * 起息时间
     */
    @NotNull(message = "产品起息时间不能为空")
    private Date valueTime;

    /**
     * 预期到期日期
     */
    @NotNull(message = "产品预期到期日期不能为空")
    private Date expectExpireTime;

    /**
     * 预期结清时间
     */
    @NotNull(message = "产品预期结清日期不能为空")
    private Date expectClearTime;

    /**
     * 投资期限
     */
    private Integer investPeriod;

    /**
     * 资产期限单位(1:天 2：周 3：月 4:年)
     */
    private Integer investPeriodUnit;

    /**
     * 投资期限描述,如3个月期
     */
    private String investPeriodDescription;

    /**
     * 投资循环周期
     */
    private Integer investPeriodLoopUnit;

    /*********************产品注册期限信息字段 end *****************************/


    /*********************产品注册投资信息字段 start *****************************/
    /**
     * 起投金额
     */
    @NotNull(message = "产品起投金额不能为空")
    @DecimalMin(value = "0.00", message = "产品起投金额必须大于等于零")
    private BigDecimal minInvestAmount;

    /**
     * 单笔投资限额
     */
    private BigDecimal singleMaxInvestAmount;

    /**
     * 个人最大投资限额
     */
    @NotNull(message = "个人最大投资限额不能为空")
    @DecimalMin(value = "0.00", message = "个人最大投资限额必须大于等于零")
    private BigDecimal maxInvestAmount;

    /**
     * 最低可持有金额
     */
    private BigDecimal minHoldAmount;

    /**
     * 递增金额(步长)
     */
    @NotNull(message = "产品步长不能为空")
    @DecimalMin(value = "0.00", message = "产品步长必须大于等于零")
    private BigDecimal increaseAmount;

    /**
     * 年基础计息周期(360, 365, 366三种枚举值)
     */
    private Integer basicInterestsPeriod;

    /**
     * 产品成立条件
     */
    private String establishCondition;

    /**
     * 计量单位(1:份额, 2:人民币元)
     */
    private Integer unit;

    /**
     * 收益方式
     */
    private Integer profitType;

    /**
     * 计息方式
     */
    private Integer calculateInvestType;

    /**
     * 最低预期收益率
     */
    @NotNull(message = "最低预期收益率不能为空")
    @DecimalMin(value = "0.00", message = "最低预期收益率必须大于等于零")
    private BigDecimal minYieldRate;

    /**
     * 最高预期收益率
     */
    @NotNull(message = "最高预期收益率不能为空")
    @DecimalMin(value = "0.00", message = "最高预期收益率必须大于等于零")
    private BigDecimal maxYieldRate;

    /**
     * 当期利率
     */
    private BigDecimal currentYieldRate;

    /**
     * 加息利率
     */
    private BigDecimal addYieldRate;

    /**
     * 浮动利率
     */
//    @NotNull(message = "浮动利率不能为空")
//    @DecimalMin(value = "0.00", message = "浮动利率必须大于等于零")
    private BigDecimal floatingYieldRate;
    /*********************产品注册投资信息字段 end *****************************/


    /*********************产品注册 合同信息字段 start *****************************/
    /**
     * 产品合同信息列表
     */
    private List<ProductContractModel> productContractList;
    /*********************产品注册 合同信息字段 end *****************************/


    /*********************产品注册 阶梯信息字段 start *****************************/
    /**
     * 产品阶梯信息列表
     */
    private List<ProductLadderModel> productLadderList;
    /*********************产品注册 合同信息字段 end *****************************/


    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDisplayName() {
        return productDisplayName;
    }

    public void setProductDisplayName(String productDisplayName) {
        this.productDisplayName = productDisplayName;
    }

    public Long getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(Long productLineId) {
        this.productLineId = productLineId;
    }

    public String getProductLineCode() {
        return productLineCode;
    }

    public void setProductLineCode(String productLineCode) {
        this.productLineCode = productLineCode;
    }

    public String getAssetPoolCode() {
        return assetPoolCode;
    }

    public void setAssetPoolCode(String assetPoolCode) {
        this.assetPoolCode = assetPoolCode;
    }

    public String getPatternCode() {
        return patternCode;
    }

    public void setPatternCode(String patternCode) {
        this.patternCode = patternCode;
    }

    public String getSaleChannelCode() {
        return saleChannelCode;
    }

    public void setSaleChannelCode(String saleChannelCode) {
        this.saleChannelCode = saleChannelCode;
    }

    public String getJoinChannelCode() {
        return joinChannelCode;
    }

    public void setJoinChannelCode(String joinChannelCode) {
        this.joinChannelCode = joinChannelCode;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Integer getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(Integer collectStatus) {
        this.collectStatus = collectStatus;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getFundSettleParty() {
        return fundSettleParty;
    }

    public void setFundSettleParty(String fundSettleParty) {
        this.fundSettleParty = fundSettleParty;
    }

    public Integer getIsOpenHMT() {
        return isOpenHMT;
    }

    public void setIsOpenHMT(Integer isOpenHMT) {
        this.isOpenHMT = isOpenHMT;
    }

    public Integer getCalendarMode() {
        return calendarMode;
    }

    public void setCalendarMode(Integer calendarMode) {
        this.calendarMode = calendarMode;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getApprovalRequireSign() {
        return approvalRequireSign;
    }

    public void setApprovalRequireSign(String approvalRequireSign) {
        this.approvalRequireSign = approvalRequireSign;
    }

    public String getApprovalSign() {
        return approvalSign;
    }

    public void setApprovalSign(String approvalSign) {
        this.approvalSign = approvalSign;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Date archiveTime) {
        this.archiveTime = archiveTime;
    }


    public Date getExpectOnlineTime() {
        return expectOnlineTime;
    }

    public void setExpectOnlineTime(Date expectOnlineTime) {
        this.expectOnlineTime = expectOnlineTime;
    }

    public Date getSaleStartTime() {
        return saleStartTime;
    }

    public void setSaleStartTime(Date saleStartTime) {
        this.saleStartTime = saleStartTime;
    }

    public Date getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(Date saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    public Date getExpectEstablishTime() {
        return expectEstablishTime;
    }

    public void setExpectEstablishTime(Date expectEstablishTime) {
        this.expectEstablishTime = expectEstablishTime;
    }

    public Date getValueTime() {
        return valueTime;
    }

    public void setValueTime(Date valueTime) {
        this.valueTime = valueTime;
    }

    public Date getExpectExpireTime() {
        return expectExpireTime;
    }

    public void setExpectExpireTime(Date expectExpireTime) {
        this.expectExpireTime = expectExpireTime;
    }

    public Date getExpectClearTime() {
        return expectClearTime;
    }

    public void setExpectClearTime(Date expectClearTime) {
        this.expectClearTime = expectClearTime;
    }

    public Date getExpectOfflineTime() {
        return expectOfflineTime;
    }

    public void setExpectOfflineTime(Date expectOfflineTime) {
        this.expectOfflineTime = expectOfflineTime;
    }

    public Integer getInvestPeriod() {
        return investPeriod;
    }

    public void setInvestPeriod(Integer investPeriod) {
        this.investPeriod = investPeriod;
    }

    public Integer getInvestPeriodUnit() {
        return investPeriodUnit;
    }

    public void setInvestPeriodUnit(Integer investPeriodUnit) {
        this.investPeriodUnit = investPeriodUnit;
    }

    public String getInvestPeriodDescription() {
        return investPeriodDescription;
    }

    public void setInvestPeriodDescription(String investPeriodDescription) {
        this.investPeriodDescription = investPeriodDescription;
    }

    public Integer getInvestPeriodLoopUnit() {
        return investPeriodLoopUnit;
    }

    public void setInvestPeriodLoopUnit(Integer investPeriodLoopUnit) {
        this.investPeriodLoopUnit = investPeriodLoopUnit;
    }

    public BigDecimal getMinInvestAmount() {
        return minInvestAmount;
    }

    public void setMinInvestAmount(BigDecimal minInvestAmount) {
        this.minInvestAmount = minInvestAmount;
    }

    public BigDecimal getSingleMaxInvestAmount() {
        return singleMaxInvestAmount;
    }

    public void setSingleMaxInvestAmount(BigDecimal singleMaxInvestAmount) {
        this.singleMaxInvestAmount = singleMaxInvestAmount;
    }

    public BigDecimal getMaxInvestAmount() {
        return maxInvestAmount;
    }

    public void setMaxInvestAmount(BigDecimal maxInvestAmount) {
        this.maxInvestAmount = maxInvestAmount;
    }

    public BigDecimal getIncreaseAmount() {
        return increaseAmount;
    }

    public void setIncreaseAmount(BigDecimal increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public Integer getBasicInterestsPeriod() {
        return basicInterestsPeriod;
    }

    public void setBasicInterestsPeriod(Integer basicInterestsPeriod) {
        this.basicInterestsPeriod = basicInterestsPeriod;
    }

    public String getEstablishCondition() {
        return establishCondition;
    }

    public void setEstablishCondition(String establishCondition) {
        this.establishCondition = establishCondition;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getProfitType() {
        return profitType;
    }

    public void setProfitType(Integer profitType) {
        this.profitType = profitType;
    }

    public Integer getCalculateInvestType() {
        return calculateInvestType;
    }

    public void setCalculateInvestType(Integer calculateInvestType) {
        this.calculateInvestType = calculateInvestType;
    }

    public BigDecimal getMinYieldRate() {
        return minYieldRate;
    }

    public void setMinYieldRate(BigDecimal minYieldRate) {
        this.minYieldRate = minYieldRate;
    }

    public BigDecimal getMaxYieldRate() {
        return maxYieldRate;
    }

    public void setMaxYieldRate(BigDecimal maxYieldRate) {
        this.maxYieldRate = maxYieldRate;
    }

    public BigDecimal getCurrentYieldRate() {
        return currentYieldRate;
    }

    public void setCurrentYieldRate(BigDecimal currentYieldRate) {
        this.currentYieldRate = currentYieldRate;
    }

    public BigDecimal getAddYieldRate() {
        return addYieldRate;
    }

    public void setAddYieldRate(BigDecimal addYieldRate) {
        this.addYieldRate = addYieldRate;
    }

    public List<ProductContractModel> getProductContractList() {
        return productContractList;
    }

    public void setProductContractList(List<ProductContractModel> productContractList) {
        this.productContractList = productContractList;
    }

    public BigDecimal getMinHoldAmount() {
        return minHoldAmount;
    }

    public void setMinHoldAmount(BigDecimal minHoldAmount) {
        this.minHoldAmount = minHoldAmount;
    }

    public Integer getAssetPoolType() {
        return assetPoolType;
    }

    public void setAssetPoolType(Integer assetPoolType) {
        this.assetPoolType = assetPoolType;
    }

    public String getAssetPoolName() {
        return assetPoolName;
    }

    public void setAssetPoolName(String assetPoolName) {
        this.assetPoolName = assetPoolName;
    }

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public List<ProductLadderModel> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<ProductLadderModel> productLadderList) {
        this.productLadderList = productLadderList;
    }

    public BigDecimal getFloatingYieldRate() {
        return floatingYieldRate;
    }

    public void setFloatingYieldRate(BigDecimal floatingYieldRate) {
        this.floatingYieldRate = floatingYieldRate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
