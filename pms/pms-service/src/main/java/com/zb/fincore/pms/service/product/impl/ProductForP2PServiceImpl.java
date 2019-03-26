package com.zb.fincore.pms.service.product.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zb.fincore.common.enums.ChannelEnum;
import com.zb.fincore.common.enums.product.ProductSaleStatusEnum;
import com.zb.fincore.common.utils.BeanUtils;
import com.zb.fincore.common.utils.DateUtils;
import com.zb.fincore.pms.common.Constants;
import com.zb.fincore.pms.common.dto.BaseResponse;
import com.zb.fincore.pms.common.enums.ProductApprovalStatusEnum;
import com.zb.fincore.pms.common.enums.ProductBasicInterestsPeriodEnum;
import com.zb.fincore.pms.common.enums.ProductCollectStatusEnum;
import com.zb.fincore.pms.common.enums.ProductInvestTypeEnum;
import com.zb.fincore.pms.common.enums.ProductLockPeriodUnitEnum;
import com.zb.fincore.pms.common.enums.ProductProfitTypeEnum;
import com.zb.fincore.pms.common.enums.ProductUnitEnum;
import com.zb.fincore.pms.common.exception.BusinessException;
import com.zb.fincore.pms.common.model.Page;
import com.zb.fincore.pms.facade.product.dto.req.ApproveProductRequest;
import com.zb.fincore.pms.facade.product.dto.req.QueryProductInfoForTradeRequest;
import com.zb.fincore.pms.facade.product.dto.req.QueryProductInfoRequest;
import com.zb.fincore.pms.facade.product.dto.req.RegisterProductBaseInfoRequest;
import com.zb.fincore.pms.facade.product.dto.req.RegisterProductContractInfoRequest;
import com.zb.fincore.pms.facade.product.dto.req.RegisterProductPeriodInfoRequest;
import com.zb.fincore.pms.facade.product.dto.req.RegisterProductProfitInfoRequest;
import com.zb.fincore.pms.facade.product.dto.req.RegisterProductRequest;
import com.zb.fincore.pms.facade.product.dto.req.UpdateProductCollectStatusRequest;
import com.zb.fincore.pms.facade.product.dto.resp.QueryProductInfoResponse;
import com.zb.fincore.pms.facade.product.dto.resp.RegisterProductResponse;
import com.zb.fincore.pms.facade.product.model.ProductContractModel;
import com.zb.fincore.pms.facade.product.model.ProductModel;
import com.zb.fincore.pms.facade.product.model.ProductPeriodModel;
import com.zb.fincore.pms.facade.product.model.ProductProfitModel;
import com.zb.fincore.pms.facade.product.model.ProductStockModel;
import com.zb.fincore.pms.service.dal.dao.ProductApprovalDao;
import com.zb.fincore.pms.service.dal.dao.ProductDao;
import com.zb.fincore.pms.service.dal.dao.ProductPeriodDao;
import com.zb.fincore.pms.service.dal.dao.ProductProfitDao;
import com.zb.fincore.pms.service.dal.model.Product;
import com.zb.fincore.pms.service.dal.model.ProductApproval;
import com.zb.fincore.pms.service.dal.model.ProductContract;
import com.zb.fincore.pms.service.dal.model.ProductPeriod;
import com.zb.fincore.pms.service.dal.model.ProductProfit;
import com.zb.fincore.pms.service.product.ProductForP2PService;
import com.zb.fincore.pms.service.product.validate.ProductForP2PServiceParameterValidator;
import com.zb.p2p.facade.api.req.StockQueryReq;
import com.zb.p2p.facade.api.resp.CommonResp;
import com.zb.p2p.facade.api.resp.ProductStockDTO;
import com.zb.p2p.facade.service.OrderFacade;

/**
 * 功能: 产品数据库接口类
 * 日期: 2017/4/6 0006 16:57
 * 版本: V1.0
 */
@Service
public class ProductForP2PServiceImpl extends AbstractProductServiceImpl implements ProductForP2PService {

    private static Logger logger = LoggerFactory.getLogger(ProductForP2PServiceImpl.class);

    /**
     * 参数校验器
     */
    @Autowired
    private ProductForP2PServiceParameterValidator periodicProductValidator;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductPeriodDao productPeriodDao;

    @Autowired
    private ProductProfitDao productProfitDao;
    
    @Autowired
    private ProductApprovalDao productApprovalDao;
    
    @Value("${p2p_product_register_notify_asset_mng_url}")
    private String p2pProductRegisterNotifyAssetMngUrl;
    
    @Autowired
    private OrderFacade orderFacade;

    /**
     * 货架系统 产品注册
     * @param req
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public RegisterProductResponse registerProduct(@Valid RegisterProductRequest req) throws Exception {
        RegisterProductResponse response = null;
        // 如果同一资产池，同一起息日，同一产品期限，并且产品销售状态为上线的产品已存在，不能注册
        QueryProductInfoForTradeRequest queryProductInfoForTradeRequest = new QueryProductInfoForTradeRequest();
        queryProductInfoForTradeRequest.setAssetPoolCode(req.getAssetPoolCode());
        queryProductInfoForTradeRequest.setInvestPeriod(req.getInvestPeriod());
        queryProductInfoForTradeRequest.setValueTime(req.getValueTime());
		List<Product> productList = getProductListForP2P(queryProductInfoForTradeRequest);
		if (productList != null && productList.size() > 0) {
			throw new BusinessException(Constants.PRODUCT_SAME_ASSETPOOL_VALUETIME_INVESTPERIOD_ONLINEEXIST_CODE,
					Constants.PRODUCT_SAME_ASSETPOOL_VALUETIME_INVESTPERIOD_ONLINEEXIST_CODE_DESC);
		}

        //1:注册产品基本信息,并入库产品库存信息
        RegisterProductBaseInfoRequest registerProductBaseInfoRequest = BeanUtils.copyAs(req, RegisterProductBaseInfoRequest.class);
        response = registerProductBaseInfo(registerProductBaseInfoRequest);
        if (!Constants.SUCCESS_RESP_CODE.equals(response.getRespCode())) {
            throw new BusinessException(response.getRespCode(), response.getRespMsg());
        }
        req.setProductCode(response.getProductCode());
        req.setProductId(response.getId());

        //2:注册产品期限相关信息及阶梯信息
        RegisterProductPeriodInfoRequest registerProductPeriodInfoRequest = BeanUtils.copyAs(req, RegisterProductPeriodInfoRequest.class);
        response = registerProductPeriodInfo(registerProductPeriodInfoRequest);
        if (!Constants.SUCCESS_RESP_CODE.equals(response.getRespCode())) {
            throw new BusinessException(response.getRespCode(), response.getRespMsg());
        }

        //3:注册产品投资收益相关信息及阶梯信息
        RegisterProductProfitInfoRequest registerProductProfitInfoRequest = BeanUtils.copyAs(req, RegisterProductProfitInfoRequest.class);
        response = registerProductProfitInfo(registerProductProfitInfoRequest);
        if (!Constants.SUCCESS_RESP_CODE.equals(response.getRespCode())) {
            throw new BusinessException(response.getRespCode(), response.getRespMsg());
        }

        //4:注册产品合同相关信息
        RegisterProductContractInfoRequest registerProductContractInfoRequest = BeanUtils.copyAs(req, RegisterProductContractInfoRequest.class);
        response = registerProductContractInfo(registerProductContractInfoRequest);
        if (!Constants.SUCCESS_RESP_CODE.equals(response.getRespCode())) {
            throw new BusinessException(response.getRespCode(), response.getRespMsg());
        }

        //http方式 调用资管系统
        Map<String, Object> returnMap = notifyAmsAssociateAssetPoolUseHttp(req.getProductCode(), req.getAssetPoolCode(), req.getTotalAmount(), req.getValueTime(), req.getExpectExpireTime());
        
        //处理产品介绍信息；
        response = registerProductInstruction(req, returnMap);
        if (!Constants.SUCCESS_RESP_CODE.equals(response.getRespCode())) {
            throw new BusinessException(response.getRespCode(), response.getRespMsg());
        }

        return response;
    }

    /**
     * 货架系统 产品注册  投资期限信息注册
     *
     * @param req
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public RegisterProductResponse registerProductPeriodInfo(@Valid RegisterProductPeriodInfoRequest req) throws Exception {
        //1: 执行请求参数校验
        Product product = productDao.selectProductByCode(req.getProductCode());
        if (null == product) {
            return BaseResponse.build(RegisterProductResponse.class, Constants.PRODUCT_NOT_EXIST_RETURN_CODE, Constants.PRODUCT_NOT_EXIST_RETURN_DESC);
        }
        RegisterProductResponse resp = periodicProductValidator.checkRegisterProductPeriodInfoParameter(req, product.getPatternCode());
        if (null != resp) {
            return resp;
        }

        //2: 入库产品期限信息
        ProductPeriod productPeriod = new ProductPeriod();
        BeanUtils.copy(req, productPeriod);
        productPeriod.setProductCode(product.getProductCode());
        productPeriod.setProductId(product.getId());
        productPeriod.setInvestPeriodUnit(ProductLockPeriodUnitEnum.DAY.getCode());
        productPeriod.setInvestPeriod(DateUtils.getBetweenDays(req.getValueTime(), req.getExpectExpireTime()));

        if (null == req.getExpectClearTime()) {
            //预期结清时间 = 到期日+1
            Calendar cl = Calendar.getInstance();
            cl.setTime(req.getExpectExpireTime());
            cl.add(Calendar.DATE, 1);
            productPeriod.setExpectClearTime(cl.getTime());
        } else {
            productPeriod.setExpectClearTime(req.getExpectClearTime());
        }

        productPeriodDao.insertSelective(productPeriod);

        //3: 返回结果
        resp = BaseResponse.build(RegisterProductResponse.class);
        resp.setId(productPeriod.getProductId());
        resp.setProductCode(productPeriod.getProductCode());
        return resp;
    }

    /**
     * 货架系统 产品注册 投资收益信息注册
     *
     * @param req
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public RegisterProductResponse registerProductProfitInfo(@Valid RegisterProductProfitInfoRequest req) throws Exception {
        //1: 执行请求参数校验
        Product product = productDao.selectProductByCode(req.getProductCode());
        if (null == product) {
            return BaseResponse.build(RegisterProductResponse.class, Constants.PRODUCT_NOT_EXIST_RETURN_CODE, Constants.PRODUCT_NOT_EXIST_RETURN_DESC);
        }
        RegisterProductResponse resp = periodicProductValidator.checkRegisterProductProfitInfoParameter(req);
        if (null != resp) {
            return resp;
        }

        //2: 入库产品期限信息
        ProductProfit productProfit = new ProductProfit();
        BeanUtils.copy(req, productProfit);
        productProfit.setProductCode(product.getProductCode());
        productProfit.setProductId(product.getId());
        productProfit.setBasicInterestsPeriod(ProductBasicInterestsPeriodEnum.ProductBasicInterestsPeriod_365.getCode());
        productProfit.setUnit(ProductUnitEnum.RMB.getCode());
        productProfit.setProfitType(req.getProfitType()==null ? ProductProfitTypeEnum.PERIODIC_VALUE.getCode() : req.getProfitType());//收益方式=固定起息日
        productProfit.setCalculateInvestType(req.getCalculateInvestType()==null ? ProductInvestTypeEnum.ONCE_PAY_ALL.getCode() : req.getCalculateInvestType());//计息方式=一次性还本付息

        productProfitDao.insertSelective(productProfit);

        //3: 返回结果
        resp = BaseResponse.build(RegisterProductResponse.class);
        resp.setId(productProfit.getId());
        resp.setProductCode(productProfit.getProductCode());
        return resp;
    }

    /**
     * 产品注册 产品部署，通知资管产品编码
     *
     * @param productCode
     * @param assetPoolCode
     * @param totalAmount
     * @throws BusinessException
     */
    @Override
    public Map notifyAmsAssociateAssetPoolUseHttp(String productCode, String assetPoolCode, BigDecimal totalAmount, Date productValueStartTime, Date expectExpireTime) throws BusinessException {
        try {
            String respContent = null;
            net.sf.json.JSONObject obj = null;
            Map<String, Object> assetNotifyParamMap = new HashMap<String, Object>();
            // 产品编号
            assetNotifyParamMap.put("productCode", productCode);
            // 资产池编号
            assetNotifyParamMap.put("poolCode", assetPoolCode);

            // 调用远程服务
            logger.debug("P2P产品注册 调用资管请求参数：" + JSONObject.toJSONString(assetNotifyParamMap));
            respContent = aesHttpClientUtils.sendPostRequest(p2pProductRegisterNotifyAssetMngUrl, JSONObject.toJSONString(assetNotifyParamMap));
            logger.debug("P2P产品注册 调用资管响应参数：" + respContent);
            // 将json字符创转换成json对象
            obj = net.sf.json.JSONObject.fromObject(respContent);
            // 判断远程URl调用是否成功
            String notifyRespCode = obj.getString("respCode");
            if (!Constants.SUCCESS_RESP_CODE.equals(notifyRespCode)) {
                throw new BusinessException(Constants.FAIL_RESP_CODE, "资管系统调用失败:" + obj.getString("respMsg"));
            }

            /**
             * 返回资产池的 受托方和 合作机构信息
             */
            String infoModel = obj.getString("infoModel");
            net.sf.json.JSONObject jsonInfoModel = net.sf.json.JSONObject.fromObject(infoModel);
            String trusteeName = jsonInfoModel.getString("trusteeName");
            String cooperationOrgName = jsonInfoModel.getString("cooperationOrgName");
            String investTargetIntroduction = jsonInfoModel.getString("investTargetIntroduction");
            Map<String, Object> assetReturnParamMap = new HashMap<String, Object>();
            assetReturnParamMap.put("trusteeName", null == trusteeName ? "" : trusteeName);
            assetReturnParamMap.put("cooperationOrgName", null == cooperationOrgName ? "" : cooperationOrgName);
            assetReturnParamMap.put("investTargetIntroduction", null == investTargetIntroduction ? "" : investTargetIntroduction);
            return assetReturnParamMap;

        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw new BusinessException(Constants.FAIL_RESP_CODE, ((BusinessException) e).getResultMsg());
            } else {
                throw new BusinessException(Constants.FAIL_RESP_CODE, "调用资管系统失败", e);
            }
        }
    }

    /**
     * 产品详情
     *
     * @param req
     * @return
     */
    @Override
    public QueryProductInfoResponse queryProductInfo(QueryProductInfoRequest req) throws Exception {
        Product product = productDao.queryProductDetailByProductCode(req.getProductCode());
        if (null == product) {
            return new QueryProductInfoResponse(Constants.PRODUCT_NOT_EXIST_RETURN_CODE, Constants.PRODUCT_NOT_EXIST_RETURN_DESC);
        }

        QueryProductInfoResponse response = new QueryProductInfoResponse();
        ProductModel productModel = new ProductModel();
        BeanUtils.copy(product, productModel);

        //产品期限信息
        ProductPeriodModel productPeriodModel = new ProductPeriodModel();
        BeanUtils.copy(product.getProductPeriod(), productPeriodModel);
        productModel.setProductPeriodModel(productPeriodModel);

        //产品投资限制及收益信息
        ProductProfitModel productProfitModel = new ProductProfitModel();
        BeanUtils.copy(product.getProductProfit(), productProfitModel);
        if (null != productProfitModel.getCalculateInvestType()) {
            productProfitModel.setCalculateInvestTypeDesc(ProductInvestTypeEnum.getEnumCodeDesc(productProfitModel.getCalculateInvestType()));
        }
        productModel.setProductProfitModel(productProfitModel);

        //产品库存信息
        ProductStockModel productStockModel = new ProductStockModel();
        BeanUtils.copy(product.getProductStock(), productStockModel);
        productModel.setProductStockModel(productStockModel);

        // 产品合同信息列表
        List<ProductContractModel> contractModelList = null;
        List<ProductContract> contractList = product.getProductContractList();
        if (null != contractList && contractList.size() > 0) {
            contractModelList = BeanUtils.copyAs(contractList, ProductContractModel.class);
            productModel.setProductContractModelList(contractModelList);
        }
        
        // 调用交易系统查询交易库存信息并设置交易库存信息
        try {
        	productModel = setTradeStock(productModel);
		} catch (Exception e) {
			BusinessException be = (BusinessException)e;
			logger.info(be.getMessage());
		}

        response.setProductModel(productModel);
        return response;
    }
    
    /**
     * setTradeStock:设置产品交易库存信息. <br/>
     *
     * @param productModel
     * @return
     * @throws BusinessException
     */
    private ProductModel setTradeStock(ProductModel productModel) throws BusinessException {
		try {
			StockQueryReq stockQueryReq = new StockQueryReq();
			stockQueryReq.setProductCode(productModel.getProductCode());
			stockQueryReq.setSource("BOSS");
			logger.debug("调用交易系统  查询交易库存请求参数:{}" + stockQueryReq);
			CommonResp<ProductStockDTO> response = orderFacade.queryStock(stockQueryReq);
			logger.debug("调用交易系统  查询交易库存响应参数:{}" + response);
			if (response == null || !Constants.SUCCESS_RESP_CODE.equals(response.getCode())) {
				throw new BusinessException(Constants.FAIL_RESP_CODE, "调用交易系统查询交易库存失败:" + response.getMessage());
			}
			productModel.setReservationTotalAmount(response.getData().getReservationTotalAmount()); // 预约募集金额
			productModel.setActualTotalAmount(response.getData().getActualTotalAmount()); // 已募集金额

			return productModel;
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				throw new BusinessException(Constants.FAIL_RESP_CODE, ((BusinessException) e).getResultMsg());
			} else {
				throw new BusinessException(Constants.FAIL_RESP_CODE, "调用交易系统失败", e);
			}
		}
    }
    
    /**
     * 产品信息审核
     *
     * @param req
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public BaseResponse approveProduct(@Valid ApproveProductRequest req) throws Exception {
        Product product = productDao.queryProductDetailByProductCode(req.getProductCode());
        if (null == product) {
            return BaseResponse.build(Constants.PRODUCT_NOT_EXIST_RETURN_CODE, Constants.PRODUCT_NOT_EXIST_RETURN_DESC);
        }

        BaseResponse resp = periodicProductValidator.checkApproveProductRequestParameter(req, product);
        if (null != resp) {
            return resp;
        }

        String curRequireApprovalSign = product.getApprovalRequireSign();
        String restNeedApprovalSign = "";
        int index = curRequireApprovalSign.indexOf(",");
        if (index != -1) {
            curRequireApprovalSign = curRequireApprovalSign.substring(0, index);
            restNeedApprovalSign = product.getApprovalRequireSign().substring(index + 1);
        }
        if (!curRequireApprovalSign.equals(req.getSign())) {
            return BaseResponse.build(Constants.PRODUCT_APPROVAL_NO_PRIVILEGE_CODE, Constants.PRODUCT_APPROVAL_NO_PRIVILEGE_CODE_DESC);
        }

        ProductApproval productApproval = new ProductApproval();
        BeanUtils.copy(req, productApproval);
        productApproval.setProductId(product.getId());
        productApproval.setProductCode(product.getProductCode());
        productApprovalDao.insertSelective(productApproval);

        int productApprovalStatus = req.getApprovalStatus();
        Map<String, Object> params = new HashMap<String, Object>();
        //产品审核不通过，通知资管释放注册时关联的资产池
        if (ProductApprovalStatusEnum.APPROVAL_FAILURE.getCode() == req.getApprovalStatus()) {
            //审核不通过调用资管系统释放资产
//            notifyAmsReleaseAssetPoolUseHttp(product.getProductCode(), product.getAssetPoolCode());
        } else {
            //当前审核通过，如果不存在下一个审核人，设置为审核通过，否则，状态为待审核
            if (!StringUtils.isBlank(restNeedApprovalSign)) {
                productApprovalStatus = ProductApprovalStatusEnum.WAIT_APPROVAL.getCode();
            } else {
                params.put("saleStatus", ProductSaleStatusEnum.PRODUCT_SALE_STATUS_DEPLOYED.getCode());
            }
        }

        params.put("id", product.getId());
        params.put("approvalStatus", productApprovalStatus);
        params.put("originalStatus", product.getApprovalStatus());
        params.put("approvalSign", StringUtils.isBlank(product.getApprovalSign()) ? curRequireApprovalSign : (product.getApprovalSign() + "," + curRequireApprovalSign));
        params.put("approvalRequireSign", restNeedApprovalSign);
        params.put("lastApprovalTime", new Date());
        productDao.updateProductApprovalStatusById(params);

        if (ProductApprovalStatusEnum.APPROVAL_SUCCESS.getCode() == productApprovalStatus) {
            //审核通过 调用TA系统 做产品登记
//            notifyTaRegisterProduct(product);
        }

        return BaseResponse.build();
    }
    
    /**
     * 供交易系统调用  产品详情查询
     *
     * @param req
     * @return
     */
	@Override
	public QueryProductInfoResponse queryProductInfoForTrade(QueryProductInfoForTradeRequest req) throws Exception {
		List<Product> productList = getProductListForP2P(req);
		
		Product product = new Product();
        if (productList == null || productList.size() == 0) {
            return new QueryProductInfoResponse(Constants.PRODUCT_NOT_EXIST_RETURN_CODE, Constants.PRODUCT_NOT_EXIST_RETURN_DESC);
        }
        product = productList.get(0);

        QueryProductInfoResponse response = new QueryProductInfoResponse();
        ProductModel productModel = new ProductModel();
        BeanUtils.copy(product, productModel);

        //产品期限信息
        ProductPeriodModel productPeriodModel = new ProductPeriodModel();
        BeanUtils.copy(product.getProductPeriod(), productPeriodModel);
        productModel.setProductPeriodModel(productPeriodModel);

        //产品投资限制及收益信息
        ProductProfitModel productProfitModel = new ProductProfitModel();
        BeanUtils.copy(product.getProductProfit(), productProfitModel);
        if (null != productProfitModel.getCalculateInvestType()) {
            productProfitModel.setCalculateInvestTypeDesc(ProductInvestTypeEnum.getEnumCodeDesc(productProfitModel.getCalculateInvestType()));
        }
        productModel.setProductProfitModel(productProfitModel);

        //产品库存信息
        ProductStockModel productStockModel = new ProductStockModel();
        BeanUtils.copy(product.getProductStock(), productStockModel);
        productModel.setProductStockModel(productStockModel);

        response.setProductModel(productModel);
        return response;
	}
	
	/**
	 * getProductListForP2P:获取同一资产池、同一起息时间、同一投资期限，已上线，销售渠道为唐小僧的产品列表. <br/>
	 *
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public List<Product> getProductListForP2P(QueryProductInfoForTradeRequest req) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assetPoolCode", req.getAssetPoolCode());// 资产池编号
		params.put("valueTime", req.getValueTime());// 起息时间
		params.put("investPeriod", req.getInvestPeriod());// 投资期限
		params.put("saleChannelCode", ChannelEnum.ZD.getCode());// 销售渠道
		params.put("saleStatus", ProductSaleStatusEnum.PRODUCT_SALE_STATUS_ON_LINE.getCode());// 销售状态
		List<Product> productList = productDao.queryProductListByCondition(params, new Page());
		return productList;
	}
	
	/**
     * 更新产品募集状态
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public BaseResponse updateProductCollectStatus(@Valid UpdateProductCollectStatusRequest req) throws Exception {
        Product product = productDao.selectProductByCode(req.getProductCode());
        if (null == product) {
            return BaseResponse.build(Constants.PRODUCT_NOT_EXIST_RETURN_CODE, Constants.PRODUCT_NOT_EXIST_RETURN_DESC);
        }

        Map<String, Object> params = new HashMap<String, Object>();
        if (req.getCollectStatus() == ProductCollectStatusEnum.PRODUCT_COLLECT_STATUS_REDEEMED.getCode()) {//请求状态为 兑付完成
            //产品募集状态需为【到期】且已下线（定期P2P产品没有【待兑付】状态）
            if (!(product.getCollectStatus() == ProductCollectStatusEnum.PRODUCT_COLLECT_STATUS_VALUE_EXPIRE.getCode()
                    && product.getSaleStatus() == ProductSaleStatusEnum.PRODUCT_SALE_STATUS_OFF_LINE.getCode())) {
                return BaseResponse.build(Constants.PRODUCT_UNKNOWN_COLLECT_STATUS_CHANGED_RESULT_CODE,
                        Constants.PRODUCT_UNKNOWN_COLLECT_STATUS_CHANGED_RESULT_CODE_DESC);
            }

            params.put("id", product.getId());
            params.put("originalStatus", product.getCollectStatus());
            params.put("collectStatus", ProductCollectStatusEnum.PRODUCT_COLLECT_STATUS_REDEEMED.getCode());//兑付完成
            params.put("saleOriginalStatus", product.getSaleStatus());
            params.put("saleStatus", ProductSaleStatusEnum.PRODUCT_SALE_STATUS_FILED.getCode());//归档

            //兑付完成更新  实际结清时间
            ProductPeriod productPeriod = new ProductPeriod();
            productPeriod.setProductCode(req.getProductCode());
            productPeriod.setClearTime(new Date());
            productPeriodDao.updateActualTimeByProductCode(productPeriod);
            //更新  归档时间
            params.put("archiveTime", new Date());
            productDao.updateProductArchiveTimeById(params);
        } else {
            return BaseResponse.build(Constants.PRODUCT_UNKNOWN_COLLECT_STATUS_CHANGED_RESULT_CODE,
                    Constants.PRODUCT_UNKNOWN_COLLECT_STATUS_CHANGED_RESULT_CODE_DESC);
        }
        productDao.updateProductCollectStatusById(params);

        return BaseResponse.build();
    }
}
