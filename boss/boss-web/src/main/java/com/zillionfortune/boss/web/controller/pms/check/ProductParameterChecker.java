/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.boss.web.controller.pms.check;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ibm.icu.math.BigDecimal;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.zillionfortune.boss.biz.pms.dto.ProductLadderRegisterModel;
import com.zillionfortune.boss.common.exception.BusinessException;
import com.zillionfortune.boss.web.controller.pms.vo.ApproveProductVo;
import com.zillionfortune.boss.web.controller.pms.vo.ProductRegisterRequestVo;
import com.zillionfortune.boss.web.controller.pms.vo.PutOrOutProductOffLineRequestVo;
import com.zillionfortune.boss.web.controller.pms.vo.QueryProductApprovalListVo;
import com.zillionfortune.boss.web.controller.pms.vo.QueryProductDetailVo;
import com.zillionfortune.boss.web.controller.pms.vo.QueryProductListVo;
import com.zillionfortune.boss.web.controller.pms.vo.UpdateProductCollectAmountRequestVo;

/**
 * ClassName: ProductParameterChecker <br/>
 * Function: 产品管理请求参数校验. <br/>
 * Date: 2017年5月9日 下午1:58:26 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version
 * @since JDK 1.7
 */
@Component
public class ProductParameterChecker {
	
	private final static String INTERNAL_PATTERN_CODE="02";

	/**
	 * checkProductRegisterRequest:产品注册相关参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkProductRegisterRequest(ProductRegisterRequestVo req) throws Exception {

		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}

		// 产品线Id
		if (StringUtils.isBlank(req.getProductLineId())) {
			throw new BusinessException("产品线Id不能为空");
		} else {
			try {
				Long.valueOf(req.getProductLineId());
			} catch (Exception e) {
				throw new BusinessException("产品线Id必须为整数");
			}
		}

		// 日历模式
		if (StringUtils.isBlank(req.getCalendarMode())) {
			throw new BusinessException("日历模式不能为空");
		} else {
			try {
				Integer.valueOf(req.getCalendarMode());
			} catch (Exception e) {
				throw new BusinessException("日历模式必须为整数");
			}
		}

		// 销售状态
		if (StringUtils.isNotBlank(req.getSaleStatus())) {
			try {
				Integer.valueOf(req.getSaleStatus());
			} catch (Exception e) {
				throw new BusinessException("销售状态必须为整数");
			}
		}

		// 募集状态
		if (StringUtils.isNotBlank(req.getCollectStatus())) {
			try {
				Integer.valueOf(req.getCollectStatus());
			} catch (Exception e) {
				throw new BusinessException("募集状态必须为整数");
			}
		}

		// 募集总规模
		if (StringUtils.isBlank(req.getTotalAmount())) {
			throw new BusinessException("募集总规模不能为空");
		} else {
			try {
				new BigDecimal(req.getTotalAmount());
			} catch (Exception e) {
				throw new BusinessException("募集总规模必须为数字");
			}
		}

		// 步长
		if (StringUtils.isBlank(req.getIncreaseAmount())) {
			throw new BusinessException("步长不能为空");
		} else {
			try {
				new BigDecimal(req.getIncreaseAmount());
			} catch (Exception e) {
				throw new BusinessException("步长必须为数字");
			}
		}

		// 资产池类型
		if (StringUtils.isBlank(req.getAssetPoolType())) {
			throw new BusinessException("资产池类型不能为空");
		} else {
			try {
				Integer.valueOf(req.getAssetPoolType());
			} catch (Exception e) {
				throw new BusinessException("资产池类型必须为整数");
			}
		}

		// 个人限投
		if (StringUtils.isNotBlank(req.getMaxInvestAmount())) {
			try {
				new BigDecimal(req.getMaxInvestAmount());
			} catch (Exception e) {
				throw new BusinessException("个人限投必须为数字");
			}
		}

		// 起购金额
		if (StringUtils.isBlank(req.getMinInvestAmount())) {
			throw new BusinessException("起购金额不能为空");
		} else {
			try {
				new BigDecimal(req.getMinInvestAmount());
			} catch (Exception e) {
				throw new BusinessException("起购金额必须为数字");
			}
		}

		// 最低可持有金额
		if (StringUtils.isBlank(req.getMinHoldAmount())) {
			throw new BusinessException("最低可持有金额不能为空");
		} else {
			try {
				new BigDecimal(req.getMinHoldAmount());
			} catch (Exception e) {
				throw new BusinessException("最低可持有金额必须为数字");
			}
		}

		// 预期年化收益率上限
		if (StringUtils.isBlank(req.getMaxYieldRate())) {
			throw new BusinessException("预期年化收益率上限不能为空");
		} else {
			try {
				new BigDecimal(req.getMaxYieldRate());
			} catch (Exception e) {
				throw new BusinessException("预期年化收益率上限必须为数字");
			}
		}

		// 预期年化收益率下限
		if (StringUtils.isBlank(req.getMinYieldRate())) {
			throw new BusinessException("预期年化收益率下限不能为空");
		} else {
			try {
				new BigDecimal(req.getMinYieldRate());
			} catch (Exception e) {
				throw new BusinessException("预期年化收益率下限必须为数字");
			}
		}

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 募集起始日
		if (StringUtils.isBlank(req.getSaleStartTime())) {
			throw new BusinessException("募集起始日不能为空");
		} else {
			try {
				format.parse(req.getSaleStartTime());
			} catch (Exception e) {
				throw new BusinessException("募集起始日必须为yyyy-MM-dd时间格式");
			}
		}

		// 募集截止日
		if (StringUtils.isBlank(req.getSaleEndTime())) {
			throw new BusinessException("募集截止日不能为空");
		} else {
			try {
				format.parse(req.getSaleEndTime());
			} catch (Exception e) {
				throw new BusinessException("募集截止日必须为yyyy-MM-dd时间格式");
			}
		}

		// 起息日
		if (StringUtils.isBlank(req.getValueTime())) {
			throw new BusinessException("起息日不能为空");
		} else {
			try {
				format.parse(req.getValueTime());
			} catch (Exception e) {
				throw new BusinessException("起息日必须为yyyy-MM-dd时间格式");
			}
		}

		// 循环周期
		if (StringUtils.isBlank(req.getInvestPeriodLoopUnit())) {
			throw new BusinessException("循环周期不能为空");
		} else {
			try {
				Integer.valueOf(req.getInvestPeriodLoopUnit());
			} catch (Exception e) {
				throw new BusinessException("循环周期必须为整数");
			}
		}

		// 预期到期日
		if (StringUtils.isBlank(req.getExpireTime())) {
			throw new BusinessException("预期到期日不能为空");
		} else {
			try {
				format.parse(req.getExpireTime());
			} catch (Exception e) {
				throw new BusinessException("预期到期日必须为yyyy-MM-dd时间格式");
			}
		}
		
		// 到期回款日
		if (StringUtils.isBlank(req.getExpectClearTime())) {
			throw new BusinessException("到期回款日不能为空");
		} else {
			try {
				format.parse(req.getExpectClearTime());
			} catch (Exception e) {
				throw new BusinessException("到期回款日必须为yyyy-MM-dd时间格式");
			}
		}

		// 产品期限
		if (StringUtils.isNotBlank(req.getInvestPeriod())) {
			try {
				Integer.valueOf(req.getInvestPeriod());
			} catch (Exception e) {
				throw new BusinessException("产品期限必须为整数");
			}
		}

		// 投资期限单位
		if (StringUtils.isNotBlank(req.getInvestPeriodUnit())) {
			try {
				Integer.valueOf(req.getInvestPeriodUnit());
			} catch (Exception e) {
				throw new BusinessException("投资期限单位必须为整数");
			}
		}

		// 当期利率
		if (StringUtils.isNotBlank(req.getCurrentYieldRate())) {
			try {
				new BigDecimal(req.getCurrentYieldRate());
			} catch (Exception e) {
				throw new BusinessException("当期利率必须为数字");
			}
		}
		
		// 浮动利率
		if (StringUtils.isNotBlank(req.getFloatingYieldRate())) {
			try {
				new BigDecimal(req.getFloatingYieldRate());
			} catch (Exception e) {
				throw new BusinessException("浮动利率必须为数字");
			}
		}
		
		if(INTERNAL_PATTERN_CODE.equals(req.getPatternCode())){
			return;
		}
		
		// 阶梯收益列表
		List<ProductLadderRegisterModel> productLadderRegisterModelList = req.getProductLadderList();
		if (productLadderRegisterModelList == null || productLadderRegisterModelList.size() == 0) {
			throw new BusinessException("阶梯收益列表不能为空");
		} else {
			for (ProductLadderRegisterModel model : productLadderRegisterModelList) {
				// 当期循环轮次
				if (StringUtils.isNotBlank(model.getInvestPeriodLoopIndex())) {
					try {
						Integer.valueOf(model.getInvestPeriodLoopIndex());
					} catch (Exception e) {
						throw new BusinessException("当前循环轮次必须为整数");
					}
				}
				
				// 实际收益率
				if (StringUtils.isNotBlank(model.getYieldRate())) {
					try {
						new BigDecimal(model.getYieldRate());
					} catch (Exception e) {
						throw new BusinessException("实际收益率必须为数字");
					}
				}
				
				// 手续费
				if (StringUtils.isNotBlank(model.getPoundage())) {
					try {
						new BigDecimal(model.getPoundage());
					} catch (Exception e) {
						throw new BusinessException("手续费必须为数字");
					}
				}
			}
		}
	}

	/**
	 * checkQueryProductListRequest:产品列表查询相关参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkQueryProductListRequest(QueryProductListVo req) throws Exception {

		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}

		// 产品线Id
		if (StringUtils.isNotBlank(req.getProductLineId())) {
			try {
				Long.valueOf(req.getProductLineId());
			} catch (Exception e) {
				throw new BusinessException("产品线Id必须为整数");
			}
		}

		// 销售状态
		if (StringUtils.isNotBlank(req.getSaleStatus())) {
			try {
				Integer.valueOf(req.getSaleStatus());
			} catch (Exception e) {
				throw new BusinessException("销售状态必须为整数");
			}
		}

		// 募集状态
		if (StringUtils.isNotBlank(req.getCollectStatus())) {
			try {
				Integer.valueOf(req.getCollectStatus());
			} catch (Exception e) {
				throw new BusinessException("募集状态必须为整数");
			}
		}

		// 募集总规模
		if (StringUtils.isNotBlank(req.getTotalAmount())) {
			try {
				new BigDecimal(req.getTotalAmount());
			} catch (Exception e) {
				throw new BusinessException("募集总规模必须为数字");
			}
		}

		// 显示状态
		if (StringUtils.isNotBlank(req.getDisplayStatus())) {
			try {
				Integer.valueOf(req.getDisplayStatus());
			} catch (Exception e) {
				throw new BusinessException("显示状态必须为整数");
			}
		}

		// 产品审核状态
		if (StringUtils.isNotBlank(req.getApprovalStatus())) {
			try {
				Integer.valueOf(req.getApprovalStatus());
			} catch (Exception e) {
				throw new BusinessException("产品审核状态必须为整数");
			}
		}

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 查询创建开始时间
		if (StringUtils.isNotBlank(req.getBeginCreateTime())) {
			try {
				format.parse(req.getBeginCreateTime());
			} catch (Exception e) {
				throw new BusinessException("查询创建开始时间必须为yyyy-MM-dd时间格式");
			}
		}
		
		// 查询创建结束时间
		if (StringUtils.isNotBlank(req.getEndCreateTime())) {
			try {
				format.parse(req.getEndCreateTime());
			} catch (Exception e) {
				throw new BusinessException("查询创建结束时间必须为yyyy-MM-dd时间格式");
			}
		}
		
		// 审核开始时间
		if (StringUtils.isNotBlank(req.getApprovalStartTime())) {
			try {
				format.parse(req.getApprovalStartTime());
			} catch (Exception e) {
				throw new BusinessException("审核开始时间必须为yyyy-MM-dd时间格式");
			}
		}
		
		// 审核结束时间
		if (StringUtils.isNotBlank(req.getApprovalEndTime())) {
			try {
				format.parse(req.getApprovalEndTime());
			} catch (Exception e) {
				throw new BusinessException("审核结束时间必须为yyyy-MM-dd时间格式");
			}
		}
	}
	
	/**
	 * checkQueryProductDetailRequest:产品详情查询相关参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkQueryProductDetailRequest(QueryProductDetailVo req) throws Exception {

		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}
		
		if (StringUtils.isBlank(req.getProductCode())) {
			throw new BusinessException("产品编号不能为空");
		}
	}
	
	/**
	 * checkPutOrOutProductOffLineRequest:产品上线/下线相关参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkPutOrOutProductOffLineRequest(PutOrOutProductOffLineRequestVo req) throws Exception {

		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}
		
		if (StringUtils.isBlank(req.getProductCode())) {
			throw new BusinessException("产品编号不能为空");
		}
	}
	
	/**
	 * checkPutOrOutProductOffLineRequest:产品上线/下线相关参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkUpdateProductCollectAmountRequest(UpdateProductCollectAmountRequestVo req) throws Exception {

		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}
		
		if (StringUtils.isBlank(req.getProductCode())) {
			throw new BusinessException("产品编号不能为空");
		}
		
		if (req.getCollectAmount()==null) {
			throw new BusinessException("募集金额不能为空");
		}
	}
	
	/**
	 * checkApproveProductRequest:产品审核相关参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkApproveProductRequest(ApproveProductVo req) throws Exception {

		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}
		
		// 产品审核状态
		if (StringUtils.isNotBlank(req.getApprovalStatus())) {
			try {
				Integer.valueOf(req.getApprovalStatus());
			} catch (Exception e) {
				throw new BusinessException("产品审核状态必须为整数");
			}
		}
	}
	
	/**
	 * checkProductApprovalListRequest:产品审核信息列表查询相关参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkProductApprovalListRequest(QueryProductApprovalListVo req) throws Exception {

		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}
		
		if (StringUtils.isBlank(req.getProductCode())) {
			throw new BusinessException("产品编号不能为空");
		}
	}
}
