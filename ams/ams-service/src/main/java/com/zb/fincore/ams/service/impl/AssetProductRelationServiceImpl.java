package com.zb.fincore.ams.service.impl;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.enums.ChangeAssetProductTypeEnum;
import com.zb.fincore.ams.common.exception.BusinessException;
import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.dto.resp.QueryProductRelatedAssetInfoResponse;
import com.zb.fincore.ams.facade.model.AssetProductRelationModel;
import com.zb.fincore.ams.facade.model.ChangeAssetProductModel;
import com.zb.fincore.ams.facade.model.ProductRelatedAssetInfoModel;
import com.zb.fincore.ams.service.AssetProductRelationService;
import com.zb.fincore.ams.service.dal.dao.AssetDao;
import com.zb.fincore.ams.service.dal.dao.AssetPoolRelationDao;
import com.zb.fincore.ams.service.dal.dao.AssetProductRelationDao;
import com.zb.fincore.ams.service.dal.dao.PoolDao;
import com.zb.fincore.ams.service.dal.model.*;
import com.zb.fincore.ams.service.validate.AssetProductServiceParameterValidator;
import com.zb.fincore.common.utils.BeanUtils;
import com.zb.fincore.common.utils.DecimalUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能: 产品资产匹配关系服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 17:33
 * 版本: V1.0
 */
@Service
public class AssetProductRelationServiceImpl implements AssetProductRelationService {

    @Autowired
    private AssetProductRelationDao assetProductRelationDao;

    @Autowired
    private AssetPoolRelationDao assetPoolRelationDao;

    @Autowired
    private PoolDao poolDao;

    @Autowired
    private AssetDao assetDao;

    @Autowired
    private AssetProductServiceParameterValidator productServiceParameterValidator;

    /**
     * 查询产品资产匹配关系
     *
     * @param req 查询产品资产匹配关系请求对象
     * @return 分页查询基础响应对象
     */
    @Override
    @Transactional(readOnly = true)
    public PageQueryResponse<AssetProductRelationModel> queryAssetProductRelation(@Valid QueryAssetProductRelationRequest req) throws Exception {
        PageQueryResponse<AssetProductRelationModel> resp = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        AssetProductRelation productRelation = new AssetProductRelation();
        PropertyUtils.copyProperties(page, req);
        PropertyUtils.copyProperties(productRelation, req);

        int totalCount = assetProductRelationDao.selectCount(productRelation);
        List<AssetProductRelationModel> productRelationModels = null;
        if (totalCount > 0) {
            List<AssetProductRelation> productRelations = assetProductRelationDao.select(productRelation, Page.getNullPage());
            productRelationModels = BeanUtils.copyAs(productRelations, AssetProductRelationModel.class);
        } else {
            resp = BaseResponse.build(PageQueryResponse.class, Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC);
        }
        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(productRelationModels);
        return resp;
    }

    /**
     * 创建产品资产匹配关系(占用资产)
     *
     * @param req 创建产品资产匹配关系请求对象
     * @return 创建产品资产匹配关系响应对象
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public synchronized CreateAssetProductRelationResponse createAssetProductRelation(@Valid CreateAssetProductRelationRequest req) throws Exception {
        Pool pool = poolDao.selectDetailByCode(req.getPoolCode());
        if (pool == null) {
            return BaseResponse.build(CreateAssetProductRelationResponse.class,
                    Constants.ASSET_POOL_NOT_EXIST_CODE, Constants.ASSET_POOL_NOT_EXIST_DESC);
        }

        if (DecimalUtils.lt(pool.getStockAmount(), req.getProductAmount())) {
            return BaseResponse.build(CreateAssetProductRelationResponse.class,
                    Constants.POOL_NOT_ENOUGH_AMOUNT_CODE, Constants.POOL_NOT_ENOUGH_AMOUNT_DESC);
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("poolId", pool.getId());
        paramMap.put("productValueStartTime", req.getProductValueStartTime());
        paramMap.put("productExpireTime", req.getProductExpireTime());
        List<AssetPoolRelation> relations = assetPoolRelationDao.selectAvailableAsset(paramMap);
        if (CollectionUtils.isEmpty(relations)) {
            return BaseResponse.build(CreateAssetProductRelationResponse.class,
                    Constants.POOL_NOT_FIT_ASSET_CODE, Constants.POOL_NOT_FIT_ASSET_DESC);
        }
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (AssetPoolRelation relation : relations) {
            totalAmount = totalAmount.add(relation.getStockAmount());
        }
        if (DecimalUtils.lt(totalAmount, req.getProductAmount())) {
            return BaseResponse.build(CreateAssetProductRelationResponse.class,
                    Constants.POOL_NOT_ENOUGH_AMOUNT_CODE, Constants.POOL_NOT_ENOUGH_AMOUNT_DESC);
        }
        List<AssetProductRelation> productRelations = new ArrayList<>();
        BigDecimal productAmount = req.getProductAmount();
        int index = 0;
        while (DecimalUtils.gt(productAmount, BigDecimal.ZERO)) {
            AssetProductRelation productRelation = new AssetProductRelation();
            productRelation.setProductCode(req.getProductCode());
            productRelation.setProductExpireTime(req.getProductExpireTime());
            productRelation.setPoolId(pool.getId());
            productRelation.setPoolCode(pool.getPoolCode());
            AssetPoolRelation relation = relations.get(index);
            Asset asset = relation.getAsset();
            productRelation.setAssetId(asset.getId());
            productRelation.setAssetCode(asset.getAssetCode());
            if (DecimalUtils.ge(relation.getStockAmount(), productAmount)) {
                productRelation.setAssetAmount(productAmount);
                relation.setStockAmount(relation.getStockAmount().subtract(productAmount));
                relation.setFrozenAmount(relation.getFrozenAmount().add(productAmount));
                asset.setStockAmount(asset.getStockAmount().subtract(productAmount));
                asset.setFrozenAmount(asset.getFrozenAmount().add(productAmount));
                productAmount = BigDecimal.ZERO;
            } else {
                productAmount = productAmount.subtract(relation.getStockAmount());
                productRelation.setAssetAmount(relation.getStockAmount());
                relation.setFrozenAmount(relation.getFrozenAmount().add(relation.getStockAmount()));
                relation.setStockAmount(BigDecimal.ZERO);
                asset.setFrozenAmount(asset.getFrozenAmount().add(asset.getStockAmount()));
                asset.setStockAmount(BigDecimal.ZERO);
            }
            index++;
            //资产和产品匹配重复
            AssetProductRelation queryProductRelation = new AssetProductRelation();
            queryProductRelation.setProductCode(req.getProductCode());
            queryProductRelation.setAssetCode(asset.getAssetCode());
            int totalCount = assetProductRelationDao.selectCount(queryProductRelation);
            if(totalCount > 0){
                throw new BusinessException(Constants.ASSET_PRODUCT_IS_EXIST_CODE,Constants.ASSET_PRODUCT_IS_EXIST_DESC);
            }

            productRelations.add(productRelation);
            assetProductRelationDao.insertSelective(productRelation);
            if (assetPoolRelationDao.updateSelectiveWithLock(relation) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
            if (assetDao.updateSelectiveWithLock(asset) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
        }
        pool.setStockAmount(pool.getStockAmount().subtract(req.getProductAmount()));
        pool.setFrozenAmount(pool.getFrozenAmount().add(req.getProductAmount()));
        if (poolDao.updateSelectiveWithLock(pool) <= 0) {
            throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
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

        CreateAssetProductRelationResponse resp = BaseResponse.build(CreateAssetProductRelationResponse.class);
        List<AssetProductRelationModel> relationModels = BeanUtils.copyAs(productRelations, AssetProductRelationModel.class);
        resp.setAssetProductRelationModels(relationModels);
        resp.setInfoModel(infoModel);
        return resp;
    }

    /**
     * 更改产品资产匹配关系(资产匹配/赎回释放)
     *
     * @param req 更改产品资产匹配关系请求对象
     * @return 更改产品资产匹配关系响应对象
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public synchronized BaseResponse changeAssetProductRelation(@Valid ChangeAssetProductRelationRequest req) throws Exception {
        for (ChangeAssetProductModel changeModel : req.getChangeAssetProductModels()) {
            BaseResponse response = productServiceParameterValidator.checkAssetProductRelationRequest(changeModel);
            if(response != null){
                return response;
            }
            AssetProductRelation productRelation = new AssetProductRelation();
            productRelation.setProductCode(req.getProductCode());
            productRelation.setPoolCode(changeModel.getPoolCode());
            productRelation.setAssetCode(changeModel.getAssetCode());
            productRelation = assetProductRelationDao.selectOne(productRelation);
            if (productRelation == null) {
                throw new BusinessException(Constants.ASSET_PRODUCT_NOT_EXIST_CODE, Constants.ASSET_PRODUCT_NOT_EXIST_DESC);
            }

            AssetPoolRelation poolRelation = new AssetPoolRelation();
            poolRelation.setPoolCode(changeModel.getPoolCode());
            poolRelation.setAssetCode(changeModel.getAssetCode());
            poolRelation = assetPoolRelationDao.selectOne(poolRelation);
            if (poolRelation == null) {
                throw new BusinessException(Constants.ASSET_POOL_NOT_EXIST_CODE, Constants.ASSET_POOL_NOT_EXIST_DESC);
            }
            Pool pool = poolDao.selectByPrimaryKey(productRelation.getPoolId());
            if (pool == null) {
                throw new BusinessException(Constants.POOL_NOT_EXIST_CODE, Constants.POOL_NOT_EXIST_DESC);
            }
            Asset asset = assetDao.selectByPrimaryKey(productRelation.getAssetId());
            if (asset == null) {
                throw new BusinessException(Constants.ASSET_NOT_EXIST_CODE, Constants.ASSET_NOT_EXIST_DESC);
            }
            if (changeModel.getChangeType() == ChangeAssetProductTypeEnum.OCCUPY.getCode()) {

                //资产已销售金额+变更额度或者已释放金额不能大于资产总额
                BigDecimal totalAmount = productRelation.getSaleAmount().add(changeModel.getChangeAmount());
                if(totalAmount.compareTo(productRelation.getAssetAmount()) > 0
                        || productRelation.getReleaseAmount().compareTo(productRelation.getAssetAmount()) >= 0){
                    throw new BusinessException(Constants.NOT_ENOUGH_AMOUNT_CODE, Constants.NOT_ENOUGH_AMOUNT_DESC);
                }
                //产品资产关联关系
                productRelation.setSaleAmount(productRelation.getSaleAmount().add(changeModel.getChangeAmount()));
                productRelation.setReleaseAmount(productRelation.getAssetAmount().subtract(changeModel.getChangeAmount()));
                //资产资产池关联关系
                poolRelation.setFrozenAmount(poolRelation.getFrozenAmount().subtract(productRelation.getAssetAmount()));
                poolRelation.setSaleAmount(poolRelation.getSaleAmount().add(changeModel.getChangeAmount()));
                BigDecimal addStock = poolRelation.getStockAmount().add(productRelation.getAssetAmount().subtract(changeModel.getChangeAmount()));//未售出释放回库存
                poolRelation.setStockAmount(addStock);
                //资产
                asset.setFrozenAmount(asset.getFrozenAmount().subtract(productRelation.getAssetAmount()));
                asset.setSaleAmount(asset.getSaleAmount().add(changeModel.getChangeAmount()));
                BigDecimal addStock2 = asset.getStockAmount().add(productRelation.getAssetAmount().subtract(changeModel.getChangeAmount()));//未售出释放回库存
                asset.setStockAmount(addStock2);
                //资产池
                pool.setFrozenAmount(pool.getFrozenAmount().subtract(productRelation.getAssetAmount()));
                pool.setSaleAmount(pool.getSaleAmount().add(changeModel.getChangeAmount()));
                BigDecimal addStock3 = pool.getStockAmount().add(productRelation.getAssetAmount().subtract(changeModel.getChangeAmount()));//未售出释放回库存
                pool.setStockAmount(addStock3);
            }
            if (changeModel.getChangeType() == ChangeAssetProductTypeEnum.RELEASE.getCode()) {
                //资产释放金额不能大于资产已售总额
                if(changeModel.getChangeAmount().compareTo(productRelation.getSaleAmount()) > 0){
                    throw new BusinessException(Constants.CHANGE_AMOUNT_LE_SALE_AMOUNT_CODE, Constants.CHANGE_AMOUNT_LE_SALE_AMOUNT_DESC);
                }
                //产品资产关联关系
                productRelation.setSaleAmount(productRelation.getSaleAmount().subtract(changeModel.getChangeAmount()));
                productRelation.setReleaseAmount(productRelation.getReleaseAmount().add(changeModel.getChangeAmount()));
                //资产资产池关联关系
                poolRelation.setStockAmount(poolRelation.getStockAmount().add(changeModel.getChangeAmount()));
                poolRelation.setSaleAmount(poolRelation.getSaleAmount().subtract(changeModel.getChangeAmount()));
                //资产
                asset.setStockAmount(asset.getStockAmount().add(changeModel.getChangeAmount()));
                asset.setSaleAmount(asset.getSaleAmount().subtract(changeModel.getChangeAmount()));
                //资产池
                pool.setStockAmount(pool.getStockAmount().add(changeModel.getChangeAmount()));
                pool.setSaleAmount(pool.getSaleAmount().subtract(changeModel.getChangeAmount()));
            }
            //更新产品资产关联关系
            if (assetProductRelationDao.updateSelectiveWithLock(productRelation) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
            //更新资产资产池关系
            if (assetPoolRelationDao.updateSelectiveWithLock(poolRelation) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
            //更新资产
            if (assetDao.updateSelectiveWithLock(asset) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
            //更新资产池
            if (poolDao.updateSelectiveWithLock(pool) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
        }
        return BaseResponse.build();
    }

    /**
     * 产品未售出释放资产
     * @param req 产品未售出释放资产请求对象
     * @return 产品未售出释放资产响应对象
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public synchronized BaseResponse releaseAssetForProductUnSale(@Valid ChangeAssetProductRelationRequest req) throws Exception {
        for (ChangeAssetProductModel changeModel : req.getChangeAssetProductModels()) {
            BaseResponse response = productServiceParameterValidator.checkReleaseAssetForProductUnSaleRequest(changeModel);
            if(response != null){
                return response;
            }
            AssetProductRelation productRelation = new AssetProductRelation();
            productRelation.setProductCode(req.getProductCode());
            productRelation.setPoolCode(changeModel.getPoolCode());
            productRelation.setAssetCode(changeModel.getAssetCode());
            productRelation = assetProductRelationDao.selectOne(productRelation);
            if (productRelation == null) {
                throw new BusinessException(Constants.ASSET_PRODUCT_NOT_EXIST_CODE, Constants.ASSET_PRODUCT_NOT_EXIST_DESC);
            }

            AssetPoolRelation poolRelation = new AssetPoolRelation();
            poolRelation.setPoolCode(changeModel.getPoolCode());
            poolRelation.setAssetCode(changeModel.getAssetCode());
            poolRelation = assetPoolRelationDao.selectOne(poolRelation);
            if (poolRelation == null) {
                throw new BusinessException(Constants.ASSET_POOL_NOT_EXIST_CODE, Constants.ASSET_POOL_NOT_EXIST_DESC);
            }
            Pool pool = poolDao.selectByPrimaryKey(productRelation.getPoolId());
            if (pool == null) {
                throw new BusinessException(Constants.POOL_NOT_EXIST_CODE, Constants.POOL_NOT_EXIST_DESC);
            }
            Asset asset = assetDao.selectByPrimaryKey(productRelation.getAssetId());
            if (asset == null) {
                throw new BusinessException(Constants.ASSET_NOT_EXIST_CODE, Constants.ASSET_NOT_EXIST_DESC);
            }

//            if (changeModel.getChangeType() == ChangeAssetProductTypeEnum.RELEASE.getCode()) {
            if(productRelation.getReleaseAmount().compareTo(productRelation.getAssetAmount()) >= 0){
                throw new BusinessException(Constants.PRODUCT_ASSET_ACCOUNT_IS_RELEASE_CODE, Constants.PRODUCT_ASSET_ACCOUNT_IS_RELEASE_DESC);
            }

            //产品资产关联关系
            productRelation.setSaleAmount(BigDecimal.ZERO);
            productRelation.setReleaseAmount(productRelation.getAssetAmount());
            //资产资产池关联关系
            poolRelation.setStockAmount(poolRelation.getStockAmount().add(productRelation.getAssetAmount()));
            poolRelation.setFrozenAmount(poolRelation.getFrozenAmount().subtract(productRelation.getAssetAmount()));
            //资产
            asset.setStockAmount(asset.getStockAmount().add(productRelation.getAssetAmount()));
            asset.setFrozenAmount(asset.getFrozenAmount().subtract(productRelation.getAssetAmount()));
            //资产池
            pool.setStockAmount(pool.getStockAmount().add(productRelation.getAssetAmount()));
            pool.setFrozenAmount(pool.getFrozenAmount().subtract(productRelation.getAssetAmount()));
//            }
            //更新产品资产关联关系
            if (assetProductRelationDao.updateSelectiveWithLock(productRelation) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
            //更新资产资产池关系
            if (assetPoolRelationDao.updateSelectiveWithLock(poolRelation) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
            //更新资产
            if (assetDao.updateSelectiveWithLock(asset) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
            //更新资产池
            if (poolDao.updateSelectiveWithLock(pool) <= 0) {
                throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
            }
        }
        return BaseResponse.build();
    }

    /**
     * 取消产品资产匹配关系(产品审核不通过,占用资产释放)
     *
     * @param req 取消产品资产匹配关系请求对象
     * @return 取消产品资产匹配关系响应对象
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BaseResponse cancelAssetProductRelation(@Valid CancelAssetProductRelationRequest req) throws Exception {
        AssetProductRelation queryRelation = new AssetProductRelation();
        queryRelation.setProductCode(req.getProductCode());
        List<AssetProductRelation> relations = assetProductRelationDao.select(queryRelation, Page.getNullPage());
        if (CollectionUtils.isNotEmpty(relations)) {
            for (AssetProductRelation relation : relations) {
                AssetPoolRelation poolRelation = new AssetPoolRelation();
                poolRelation.setPoolId(relation.getPoolId());
                poolRelation.setAssetId(relation.getAssetId());
                poolRelation = assetPoolRelationDao.selectOne(poolRelation);
                if (poolRelation == null) {
                    throw new BusinessException(Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC,
                            "未查询到资产与资产池关联关系");
                }
                Pool pool = poolDao.selectByPrimaryKey(relation.getPoolId());
                if (pool == null) {
                    throw new BusinessException(Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC,
                            "未查询到资产池");
                }
                Asset asset = assetDao.selectByPrimaryKey(relation.getAssetId());
                if (asset == null) {
                    throw new BusinessException(Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC,
                            "未查询到资产");
                }
                //资产和资产池关联关系
                poolRelation.setStockAmount(poolRelation.getStockAmount().add(relation.getAssetAmount()));
                poolRelation.setFrozenAmount(poolRelation.getFrozenAmount().subtract(relation.getAssetAmount()));
                //资产
                asset.setStockAmount(asset.getStockAmount().add(relation.getAssetAmount()));
                asset.setFrozenAmount(asset.getFrozenAmount().subtract(relation.getAssetAmount()));
                //资产池
                pool.setStockAmount(pool.getStockAmount().add(relation.getAssetAmount()));
                pool.setFrozenAmount(pool.getFrozenAmount().subtract(relation.getAssetAmount()));
                //删除产品资产关联关系
                assetProductRelationDao.deleteByPrimaryKey(relation.getId());
                //更新资产资产池关系
                if (assetPoolRelationDao.updateSelectiveWithLock(poolRelation) <= 0) {
                    throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
                }
                //更新资产
                if (assetDao.updateSelectiveWithLock(asset) <= 0) {
                    throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
                }
                //更新资产池
                if (poolDao.updateSelectiveWithLock(pool) <= 0) {
                    throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
                }
            }
        }else {
            return BaseResponse.build(Constants.ASSET_PRODUCT_NOT_EXIST_CODE, Constants.ASSET_PRODUCT_NOT_EXIST_DESC);
        }
        return BaseResponse.build();
    }

    /**
     * 查询产品关联资产信息
     * @param req 查询产品关联资产请求对象
     * @return 查询产品关联资产响应对象
     */
    @Override
    public QueryProductRelatedAssetInfoResponse queryProductRelatedAssetInfo(@Valid QueryProductRelatedAssetInfoRequest req) throws Exception {

        QueryProductRelatedAssetInfoResponse resp = BaseResponse.build(QueryProductRelatedAssetInfoResponse.class);

        AssetProductRelation productRelation = new AssetProductRelation();
        productRelation.setProductCode(req.getProductCode());

        List<AssetProductRelation> productRelations = assetProductRelationDao.select(productRelation, Page.getNullPage());
        Pool pool = null;
        if(productRelations.size() > 0){
            AssetProductRelation relation = productRelations.get(0);
            pool = poolDao.selectDetailByCode(relation.getPoolCode());
            if (null == pool){
                resp.setRespCode(Constants.POOL_NOT_EXIST_CODE);
                resp.setRespMsg(Constants.POOL_NOT_EXIST_DESC);
                return resp;
            }
        }else {
            resp = BaseResponse.build(QueryProductRelatedAssetInfoResponse.class, Constants.ASSET_PRODUCT_NOT_EXIST_CODE, Constants.ASSET_PRODUCT_NOT_EXIST_DESC);
            return resp;
        }

        ProductRelatedAssetInfoModel infoModel = new ProductRelatedAssetInfoModel();
        FinanceSubject financeSubject = pool.getFinanceSubject();
        if(null != financeSubject){
            infoModel.setCooperationOrgName(financeSubject.getSubjectShowName());//合作结构
        }
        Trustee trustee = pool.getTrustee();
        if(null != trustee){
            infoModel.setTrusteeName(trustee.getTrusteeShowName());//受托方
            infoModel.setCertNo(trustee.getCertNo());
        }
        resp.setInfoModel(infoModel);

        return resp;
    }
}
