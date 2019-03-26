package com.zb.fincore.ams.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.zb.fincore.ams.common.enums.*;
import com.zb.fincore.ams.service.ProductForP2PService;
import com.zb.fincore.ams.service.dal.dao.*;
import com.zb.fincore.ams.service.dal.model.*;
import com.zb.fincore.ams.service.validate.AssetForP2PServiceParameterValidator;
import com.zb.fincore.common.enums.ChannelEnum;
import com.zb.p2p.facade.api.req.LoanReq;
import com.zb.p2p.facade.api.resp.CommonResp;
import com.zb.p2p.facade.api.resp.product.ProductDTO;
import com.zb.p2p.facade.service.OrderFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.exception.BusinessException;
import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.dto.req.CreateAssetForP2PRequest;
import com.zb.fincore.ams.facade.dto.req.CreateAssetProductRelationRequest;
import com.zb.fincore.ams.facade.dto.req.RelateAssetProductForP2PRequest;
import com.zb.fincore.ams.facade.dto.req.UpdateAssetStockRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import com.zb.fincore.ams.facade.model.ProductRelatedAssetInfoModel;
import com.zb.fincore.ams.service.AssetForP2PService;
import com.zb.fincore.ams.service.AssetRepayPlanService;
import com.zb.fincore.ams.service.SequenceService;
import com.zb.fincore.ams.service.validate.AssetProductServiceParameterValidator;
import com.zb.fincore.common.utils.DateUtils;
import javax.validation.Valid;

/**
 * 功能: P2P资产服务接口
 * 创建: mabiao - mabiao@zillionfortune.com
 * 日期: 2017-9-1
 * 版本: V1.0
 */
@Service
public class AssetForP2PServiceImpl implements AssetForP2PService {

    private static Logger logger = LoggerFactory.getLogger(AssetForP2PServiceImpl.class);
    @Autowired
    private PoolDao poolDao;

    @Autowired
    private AssetDao assetDao;

    @Autowired
    private AssetPoolRelationDao relationDao;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private AssetRepayPlanService assetRepayPlanService;

    @Autowired
    private AssetProductRelationDao assetProductRelationDao;

    @Autowired
    private AssetPoolRelationDao assetPoolRelationDao;

    @Autowired
    private AssetRepayPlanDao assetRepayPlanDao;

    @Autowired
    private OrderFacade orderFacadeService;

    @Autowired
    private ProductForP2PService productForP2PService;

    @Autowired
    private AssetProductServiceParameterValidator validator;

    @Autowired
    private AssetForP2PServiceParameterValidator assetForP2PServiceParameterValidator;

    /**
     * 创建产品资产匹配关系
     * @param req 创建产品资产匹配关系请求对象
     * @return 创建产品资产匹配关系响应对象
     */
    @Override
    public CreateAssetProductRelationResponse createAssetProductRelation(CreateAssetProductRelationRequest req) throws Exception {
        CreateAssetProductRelationResponse resp = validator.checkAssetProductRelationP2PRequest(req);
        if (resp != null) {
            return resp;
        }

        Pool pool = poolDao.selectDetailByCode(req.getPoolCode());
        if (pool == null) {
            return BaseResponse.build(CreateAssetProductRelationResponse.class,
                    Constants.POOL_NOT_EXIST_CODE, Constants.POOL_NOT_EXIST_DESC);
        }

        ProductRelatedAssetInfoModel infoModel = new ProductRelatedAssetInfoModel();
        FinanceSubject financeSubject = pool.getFinanceSubject();
        if(null != financeSubject){
            String subjectShowName = null == financeSubject.getSubjectShowName()?"":financeSubject.getSubjectShowName()+"；";
            String subjectAddress = null == financeSubject.getSubjectAddressShowName()?"":"公司地址："+financeSubject.getSubjectAddressShowName()+"；";
            String businessScope = null == financeSubject.getBusinessScope()?"":"经营范围："+financeSubject.getBusinessScope();
            String cooperationOrgName = subjectShowName + subjectAddress + businessScope;
            infoModel.setCooperationOrgName(cooperationOrgName);//合作结构

            String introduction = null == financeSubject.getIntroduction()?"":financeSubject.getIntroduction();
            infoModel.setInvestTargetIntroduction(introduction);//投资介绍
        }
        Trustee trustee = pool.getTrustee();
        if(null != trustee){
            String trusteeName = null == trustee.getTrusteeShowName()?"":trustee.getTrusteeShowName()+"；";
            String trusteeAddress = null == trustee.getTrusteeAddressShowName()?"":"公司地址："+trustee.getTrusteeAddressShowName()+"；";
            String businessScope = null == trustee.getBusinessScope()?"":"经营范围："+trustee.getBusinessScope();
            String introduction = trusteeName + trusteeAddress + businessScope;
            infoModel.setTrusteeName(introduction);//受托方
        }

        resp = BaseResponse.build(CreateAssetProductRelationResponse.class);
        resp.setInfoModel(infoModel);
        return resp;
    }

    /**
     * 创建资产
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public synchronized CreateAssetResponse createAssetForP2P(@Valid CreateAssetForP2PRequest req) throws Exception {
        CreateAssetResponse resp = BaseResponse.build(CreateAssetResponse.class);

        BaseResponse response = assetForP2PServiceParameterValidator.checkCreateAssetRequest(req);
        if (null != response){
            resp.setRespCode(response.getRespCode());
            resp.setRespMsg(response.getRespMsg());
            return resp;
        }

        Asset isRepeat = new Asset();
        isRepeat.setExtAssetCode(req.getExtAssetCode());
        int repeatCount = assetDao.selectDetailCount(isRepeat);
        if(repeatCount > 0){
            resp.setRespCode(Constants.EXT_ASSET_CODE_REPEAT_CODE);
            resp.setRespMsg(Constants.EXT_ASSET_CODE_REPEAT_DESC);
            return resp;
        }

        //按照规则生成资产编号
        String assetCode = sequenceService.generateBusinessCode(Constants.ASSET_CODE_PREFIX, Constants.ASSEET_CODE_SEQUENCE_LENGTH);

        //入库资产信息
        Asset asset = new Asset();
//        PropertyUtils.copyProperties(asset, req);
        asset.setAssetCode(assetCode);

        String assetName = "";
        String dateSequence = sequenceService.generateRegisterNo(Constants.ASSET_CODE_PREFIX, Constants.LENGTH_3);
        dateSequence = dateSequence.replace(Constants.ASSET_CODE_PREFIX,"");
        //生成资产名称
        assetName = AssetTypeEnum.PERSONAL_LOAN.getDesc() + req.getLoanName() + dateSequence;

        asset.setAssetName(assetName);
        asset.setEstablishTime(DateUtils.parse(req.getValueStartTime(),DateUtils.DATE_FORMAT_YYMMDD));//成立时间
        asset.setValueStartTime(DateUtils.parse(req.getValueStartTime(),DateUtils.DATE_FORMAT_YYMMDD));//资产起息时间
        asset.setValueEndTime(DateUtils.parse(req.getValueEndTime(),DateUtils.DATE_FORMAT_YYMMDD));//资产止息时间
        asset.setExpireTime(DateUtils.parse(req.getValueEndTime(),DateUtils.DATE_FORMAT_YYMMDD));//资产到期时间

        int valueDays = req.getValueDays();
        if(valueDays != Constants.P2P_LIMIT_VALUE_DAYS && valueDays != Constants.P2P_LIMIT_VALUE_2DAYS){
            resp.setRespCode(Constants.P2P_LIMIT_VALUE_DAYS_CODE);
            resp.setRespMsg(Constants.P2P_LIMIT_VALUE_DAYS_DESC);
            return resp;
        }
        asset.setExtAssetCode(req.getExtAssetCode());
        asset.setValueDays(valueDays);//期限
        asset.setApprovalStatus(AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());//审核通过
        BigDecimal assetAmount = req.getAssetAmount().setScale(0,BigDecimal.ROUND_DOWN);
        asset.setAssetAmount(assetAmount);//募集金额
        asset.setYieldRate(req.getYieldRate());//利率
        asset.setStockAmount(assetAmount);//库存和总规模一致
        asset.setDistributeAmount(assetAmount);//已分配
        asset.setAssetType(AssetTypeEnum.PERSONAL_LOAN.getCode());//个人贷款
        asset.setRepaymentType(AssetRepaymentTypeEnum.ONE_TIME_DEBT.getCode());//本息一次性偿还
        asset.setCollectStatus(AssetCollectStatusEnum.ESTABLISHED.getCode());//募集状态为已成立
        asset.setRegisterType(RegisterTypeEnum.MSD.getCode());//资产来源：马上贷
        asset.setFinanceSubjectCode(Constants.MSD_CODE);//合作机构：马上贷
        //利率
        BigDecimal yieldRate = req.getYieldRate().divide(new BigDecimal(100));
        asset.setYieldRate(yieldRate);
        assetDao.insertSelective(asset);

        Pool newPool = new Pool();
        newPool.setPoolType(PoolTypeEnum.P2P_LOAN.getCode());
        List<Pool> pools = poolDao.selectPoolDetailList(newPool, Page.getNullPage());//查询资产池
        Pool pool = null;
        List<AssetPoolRelation> relations = new ArrayList<>();
        if(null != pools && pools.size() > 0){
            pool = pools.get(0);
            AssetPoolRelation relation = new AssetPoolRelation();//资产资产池关联关系
            relation.setPoolId(pool.getId());
            relation.setPoolCode(pool.getPoolCode());
            relation.setAssetId(asset.getId());
            relation.setAssetCode(asset.getAssetCode());
            relation.setRelationAmount(asset.getAssetAmount());
            relation.setStockAmount(asset.getStockAmount());
            relation.setFrozenAmount(BigDecimal.ZERO);
            relation.setSaleAmount(BigDecimal.ZERO);
            relations.add(relation);

            pool.setTotalAmount(pool.getTotalAmount().add(asset.getAssetAmount()));
            pool.setStockAmount(pool.getStockAmount().add(asset.getStockAmount()));
            if (poolDao.updateSelectiveWithLock(pool) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
        }else {
            throw new BusinessException(Constants.POOL_NOT_EXIST_CODE, Constants.POOL_NOT_EXIST_DESC);
        }

        //生成资产汇款计划
        assetRepayPlanService.createAssetReplayPlanForP2P(asset,req);

        //创建资产资产池关联关系
        relationDao.insertList(relations);

        //根据资产池编号，起息时间，期限查询产品服务
        CommonResp<ProductDTO> prodCommonResp = productForP2PService.queryProductInfoForAssetMatch(pool.getPoolCode(), asset.getValueStartTime(), asset.getValueDays());
        if (null == prodCommonResp || null == prodCommonResp.getData() || StringUtils.isBlank(prodCommonResp.getData().getProductCode())) {
            throw new BusinessException(Constants.P2P_ASSET_RELATED_PRODUCT_NOT_EXISTS_CODE, Constants.P2P_ASSET_RELATED_PRODUCT_NOT_EXISTS_DESC);
        }
        ProductDTO productDTO = prodCommonResp.getData();

        //传递资产信息给交易
        LoanReq loanReq = new LoanReq();
        loanReq.setMemberId(req.getMemberId());//会员id
        loanReq.setLoanAmount(asset.getAssetAmount());//借款金额
        loanReq.setLoanNo(asset.getExtAssetCode());//外部编号（马上贷）
        loanReq.setLoanFee(req.getLoanFee());//借款手续费
        loanReq.setLoanInterests(req.getRepayInterest());//借款利息
        loanReq.setAssetCode(asset.getAssetCode());//资产编号
        loanReq.setAssetPoolCode(pool.getPoolCode());//资产池编号
        loanReq.setAssetAmount(asset.getAssetAmount());//资产金额
        loanReq.setAssetName(asset.getAssetName());//资产名称
        loanReq.setValueStartTime(asset.getValueStartTime());//资产起息日期
        loanReq.setValueEndTime(asset.getValueEndTime());//资产止息日期
        loanReq.setExpireDate(asset.getExpireTime());//资产到期日
        loanReq.setAssetYearYield(asset.getYieldRate());//资产年化利率
        loanReq.setLockDate(asset.getValueDays());//借款期限
        loanReq.setLoanTime(DateUtils.parse(req.getLoanApplyTime(), DateUtils.COMMON_TIMESTAMP_FORMAT));//借款时间
        loanReq.setSaleChannel(ChannelEnum.ZD.getCode());//交易渠道 资鼎
        loanReq.setProductCode(productDTO.getProductCode());//产品编号
        logger.info("call trade process loan request params: loanNo:{}, productCode:{}", loanReq.getLoanNo(), loanReq.getProductCode());
        CommonResp commonResp = orderFacadeService.loan(loanReq);
        if(!commonResp.getCode().equals(Constants.SUCCESS_RESP_CODE)){
            throw new BusinessException(Constants.TRADE_ASSET_SYNC_CODE, Constants.TRADE_ASSET_SYNC_FAILED);
        }

        return resp;
    }

    /**
     * 资产产品匹配
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public synchronized BaseResponse saveAssetProductRelation(@Valid RelateAssetProductForP2PRequest req) throws Exception {

        Pool pool = poolDao.selectDetailByCode(req.getPoolCode());
        if (pool == null) {
            return BaseResponse.build(Constants.ASSET_POOL_NOT_EXIST_CODE, Constants.ASSET_POOL_NOT_EXIST_DESC);
        }
        for (String assetCode : req.getAssetCodes()) {
            if (StringUtils.isBlank(assetCode)){
                throw new BusinessException(Constants.ASSET_CODE_NOT_NULL_CODE, Constants.ASSET_CODE_NOT_NULL_DESC);
            }

            Asset asset = assetDao.selectByCode(assetCode);
            if(null == asset){
                throw new BusinessException(Constants.ASSET_NOT_EXIST_CODE, Constants.ASSET_NOT_EXIST_DESC);
            }
            //资产金额变动
            asset.setStockAmount(BigDecimal.ZERO);
            asset.setFrozenAmount(BigDecimal.ZERO);
            asset.setSaleAmount(asset.getAssetAmount());
            assetDao.updateSelectiveWithLock(asset);

            //资产池金额变动
            pool.setSaleAmount(pool.getSaleAmount().add(asset.getAssetAmount()));
            pool.setStockAmount(pool.getStockAmount().subtract(asset.getAssetAmount()));

            //资产和资产池关联金额变动
            AssetPoolRelation poolRelation = new AssetPoolRelation();
            poolRelation.setPoolCode(req.getPoolCode());
            poolRelation.setAssetCode(assetCode);
            poolRelation = assetPoolRelationDao.selectOne(poolRelation);
            poolRelation.setFrozenAmount(BigDecimal.ZERO);
            poolRelation.setSaleAmount(asset.getAssetAmount());
            poolRelation.setStockAmount(BigDecimal.ZERO);
            if (assetPoolRelationDao.updateSelectiveWithLock(poolRelation) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }

            //资产和产品匹配重复校验
            AssetProductRelation queryProductRelation = new AssetProductRelation();
            queryProductRelation.setProductCode(req.getProductCode());
            queryProductRelation.setAssetCode(assetCode);
            int totalCount = assetProductRelationDao.selectCount(queryProductRelation);
            if(totalCount > 0){
                throw new BusinessException(Constants.ASSET_PRODUCT_IS_EXIST_CODE,Constants.ASSET_PRODUCT_IS_EXIST_DESC);
            }

            AssetProductRelation productRelation = new AssetProductRelation();
            productRelation.setProductCode(req.getProductCode());
            productRelation.setProductExpireTime(asset.getExpireTime());
            productRelation.setPoolId(pool.getId());
            productRelation.setPoolCode(pool.getPoolCode());
            productRelation.setAssetId(asset.getId());
            productRelation.setAssetCode(asset.getAssetCode());
            productRelation.setAssetAmount(asset.getAssetAmount());
            productRelation.setSaleAmount(asset.getAssetAmount());
            assetProductRelationDao.insertSelective(productRelation);//创建产品资产关联关系
        }

        if (poolDao.updateSelectiveWithLock(pool) <= 0) {
            throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
        }
        return BaseResponse.build();
    }

    /**
     * 更新资产库存
     * @param req
     * @return
     * @throws Exception
     */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized BaseResponse updateAssetStock(@Valid UpdateAssetStockRequest req) throws Exception {
		//1.1、查询资产记录
		Asset asset = assetDao.selectByExtAssetCode(req.getExtAssetCode());
		if(asset == null){
			throw new BusinessException(Constants.ASSET_NOT_EXIST_CODE, Constants.ASSET_NOT_EXIST_DESC);
		}

        AssetRepayPlan repayPlan = new AssetRepayPlan();
        repayPlan.setAssetCode(asset.getAssetCode());
        List<AssetRepayPlan> assetRepayPlans = assetRepayPlanDao.select(repayPlan, Page.getNullPage());
        if(null != assetRepayPlans && assetRepayPlans.size() > 0){
            AssetRepayPlan assetRepayPlan = assetRepayPlans.get(0);
            if(assetRepayPlan.getRepayStatus() == RepayStatusEnum.REPAID.getCode()){
                return BaseResponse.build(Constants.REPAID_CODE_REPEAT_CODE,Constants.REPAID_CODE_REPEAT_DESC);
            }
            assetRepayPlan.setRepayStatus(RepayStatusEnum.REPAID.getCode());
            assetRepayPlan.setRepaidAmount(asset.getAssetAmount());
            assetRepayPlan.setWaitingRepayAmount(BigDecimal.ZERO);
            assetRepayPlanDao.updateByPrimaryKeySelective(assetRepayPlan);
        }
		
		//1.2、查询资产/资产池关联
		AssetPoolRelation record = new AssetPoolRelation();
		record.setAssetCode(asset.getAssetCode());
		AssetPoolRelation assetPoolRelation = assetPoolRelationDao.selectOne(record);
		if(assetPoolRelation == null){
			throw new BusinessException(Constants.ASSET_POOL_NOT_EXIST_CODE, Constants.ASSET_POOL_NOT_EXIST_DESC);
		}
		
		//1.3、查询资产池记录
		Pool pool = poolDao.selectByCode(assetPoolRelation.getPoolCode());
		if(pool == null){
			throw new BusinessException(Constants.POOL_NOT_EXIST_CODE, Constants.POOL_NOT_EXIST_DESC);
		}
	
		//1.4、查询产品/资产关联关系
		AssetProductRelation productRelation = new AssetProductRelation();
		productRelation.setAssetCode(asset.getAssetCode());
		productRelation.setPoolCode(assetPoolRelation.getPoolCode());
		AssetProductRelation assetProductRelation = assetProductRelationDao.selectOne(productRelation);
		if(assetProductRelation == null){
			throw new BusinessException(Constants.ASSET_PRODUCT_NOT_EXIST_CODE, Constants.ASSET_PRODUCT_NOT_EXIST_DESC);
		}
		
		//2.1、更新资产
		asset.setStockAmount(asset.getAssetAmount());//加
		asset.setSaleAmount(BigDecimal.ZERO);//减
        asset.setCollectStatus(AssetCollectStatusEnum.CAN_NOT_USE.getCode());//不可用
		if (assetDao.updateSelectiveWithLock(asset) <= 0) {
			throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
		}
		
		//2.2、更新资产/资产池关联关系
		assetPoolRelation.setStockAmount(assetPoolRelation.getStockAmount().add(asset.getAssetAmount()));//加
		assetPoolRelation.setSaleAmount(assetPoolRelation.getSaleAmount().subtract(asset.getAssetAmount()));//减
		if (assetPoolRelationDao.updateSelectiveWithLock(assetPoolRelation) <= 0) {
			throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
		}
		
		//2.3、更新资产池
		pool.setStockAmount(pool.getStockAmount().add(asset.getAssetAmount()));//加
		pool.setSaleAmount(pool.getSaleAmount().subtract(asset.getAssetAmount()));//减
		if (poolDao.updateSelectiveWithLock(pool) <= 0) {
			throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
		}
		
		//2.4、更新产品资产关联关系
		assetProductRelation.setReleaseAmount(asset.getAssetAmount());//加
		assetProductRelation.setSaleAmount(BigDecimal.ZERO);//减
		if (assetProductRelationDao.updateSelectiveWithLock(assetProductRelation) <= 0) {
			throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
		}
		return BaseResponse.build();
	}
}
