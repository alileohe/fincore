package com.zb.fincore.ams.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.enums.AssetApprovalStatusEnum;
import com.zb.fincore.ams.common.enums.AssetCollectStatusEnum;
import com.zb.fincore.ams.common.enums.PoolLoadSwitchEnum;
import com.zb.fincore.ams.common.enums.RegisterTypeEnum;
import com.zb.fincore.ams.common.exception.BusinessException;
import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.dto.req.CreateAssetPoolRelationRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetPoolRelationRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolAssetListRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetPoolRelationResponse;
import com.zb.fincore.ams.facade.model.AssetModel;
import com.zb.fincore.ams.facade.model.AssetPoolRelationModel;
import com.zb.fincore.ams.facade.model.FinanceSubjectModel;
import com.zb.fincore.ams.service.AssetPoolRelationService;
import com.zb.fincore.ams.service.dal.dao.AssetDao;
import com.zb.fincore.ams.service.dal.dao.AssetPoolRelationDao;
import com.zb.fincore.ams.service.dal.dao.PoolDao;
import com.zb.fincore.ams.service.dal.model.Asset;
import com.zb.fincore.ams.service.dal.model.AssetPoolRelation;
import com.zb.fincore.ams.service.dal.model.Pool;
import com.zb.fincore.common.utils.BeanUtils;
import com.zb.fincore.common.utils.CollectionUtils;
import com.zb.fincore.common.utils.DecimalUtils;

/**
 * 功能: 资产池资产匹配关系服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/21 0021 17:17
 * 版本: V1.0
 */
@Service
public class AssetPoolRelationServiceImpl implements AssetPoolRelationService {

    @Autowired
    private PoolDao poolDao;

    @Autowired
    private AssetDao assetDao;

    @Autowired
    private AssetPoolRelationDao relationDao;

    /**
     * 创建资产资产池关联关系
     *
     * @param req 创建资产资产池关联关系请求对象
     * @return 创建资产资产池关联关系响应对象
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CreateAssetPoolRelationResponse createAssetPoolRelation(@Valid CreateAssetPoolRelationRequest req) throws Exception {
        Pool pool = poolDao.selectByCode(req.getPoolCode());
        //检测资产池是否存在
        if (pool == null) {
            return BaseResponse.build(CreateAssetPoolRelationResponse.class, Constants.POOL_NOT_EXIST_CODE, Constants.POOL_NOT_EXIST_DESC);
        }
        //检测资产池出入状态
        if (pool.getLoadSwitchStatus() != PoolLoadSwitchEnum.SAVE.getCode() && pool.getLoadSwitchStatus() != PoolLoadSwitchEnum.LOAD_SAVE.getCode()) {
            return BaseResponse.build(CreateAssetPoolRelationResponse.class, Constants.POOL_CAN_NOT_SAVE_CODE, Constants.POOL_CAN_NOT_SAVE_DESC);
        }
        List<Asset> assets = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;
        Set set = new HashSet();
        int index = 0;
        for (String assetCode : req.getAssetCodeList()) {
            //检测资产编号是否重复
            set.add(assetCode);
            if(set.size() != index + 1){
                return BaseResponse.build(CreateAssetPoolRelationResponse.class, Constants.ASSET_CODE_NOT_REPEAT_CODE, Constants.ASSET_CODE_NOT_REPEAT_DESC);
            }
            if(StringUtils.isBlank(assetCode)){
                return BaseResponse.build(CreateAssetPoolRelationResponse.class, Constants.ASSET_CODE_NOT_NULL_CODE, Constants.ASSET_CODE_NOT_NULL_DESC);
            }

            Asset asset = assetDao.selectByCode(assetCode);
            //检测资产是否存在
            if (asset == null) {
                return BaseResponse.build(CreateAssetPoolRelationResponse.class, Constants.ASSET_NOT_EXIST_CODE, Constants.ASSET_NOT_EXIST_DESC);
            }
            //检测资产审核状态
            if (asset.getApprovalStatus() != AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode()) {
                return BaseResponse.build(CreateAssetPoolRelationResponse.class, Constants.ASSET_NOT_APPROVAL_CODE, Constants.ASSET_NOT_APPROVAL_DESC);
            }
            //检测资产募集状态
            if (asset.getCollectStatus() != AssetCollectStatusEnum.ESTABLISHED.getCode()) {
                return BaseResponse.build(CreateAssetPoolRelationResponse.class, Constants.ASSET_COLLECT_NOT_ESTABLISHED_CODE, Constants.ASSET_COLLECT_NOT_ESTABLISHED_DESC);
            }
            //检测是否属于同一融资方
            if (!asset.getFinanceSubjectCode().equals(pool.getFinanceSubjectCode())) {
                return BaseResponse.build(CreateAssetPoolRelationResponse.class, Constants.NOT_SAME_SUBJECT_CODE, Constants.NOT_SAME_SUBJECT_DESC);
            }
            //检测资产是否已经绑定过资产池
            if (DecimalUtils.gt(asset.getDistributeAmount(), BigDecimal.ZERO)) {
                return BaseResponse.build(CreateAssetPoolRelationResponse.class, Constants.ASSET_RELATION_POOL_CODE, Constants.ASSET_RELATION_POOL_DESC);
            }
            //全部分配给资产池
            asset.setDistributeAmount(asset.getAssetAmount());
            totalAmount = totalAmount.add(asset.getAssetAmount());
            assets.add(asset);
            index ++;
        }
        //检测资产池金额上限
        if (DecimalUtils.isNotNullOrZero(pool.getLimitAmount())) {
            if (DecimalUtils.gt(totalAmount.add(pool.getTotalAmount()), pool.getLimitAmount())) {
                return BaseResponse.build(CreateAssetPoolRelationResponse.class, Constants.POOL_OVER_LIMIT_CODE, Constants.POOL_OVER_LIMIT_DESC);
            }
        }

        List<AssetPoolRelation> relations = new ArrayList<>();
        for (Asset asset : assets) {
            AssetPoolRelation relation = new AssetPoolRelation();
            relation.setPoolId(pool.getId());
            relation.setPoolCode(pool.getPoolCode());
            relation.setAssetId(asset.getId());
            relation.setAssetCode(asset.getAssetCode());
            relation.setRelationAmount(asset.getAssetAmount());
            relation.setStockAmount(asset.getStockAmount());
            relation.setFrozenAmount(BigDecimal.ZERO);
            relation.setSaleAmount(BigDecimal.ZERO);
            relation.setCreateBy(req.getCreateBy());
            relation.setModifyBy(req.getCreateBy());
            pool.setTotalAmount(pool.getTotalAmount().add(asset.getAssetAmount()));
            pool.setStockAmount(pool.getStockAmount().add(asset.getStockAmount()));
            relations.add(relation);
        }
        if (poolDao.updateSelectiveWithLock(pool) <= 0) {
            throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
        }
        assetDao.batchUpdateWithLock(assets);
        relationDao.insertList(relations);
        List<AssetPoolRelationModel> relationModels = null;
        AssetPoolRelation relation = new AssetPoolRelation();
        relation.setPoolId(pool.getId());
        relation.setPoolCode(pool.getPoolCode());
        relations = relationDao.select(relation, new Page());
        if (!CollectionUtils.isNullOrEmpty(relations)) {
            relationModels = BeanUtils.copyAs(relations, AssetPoolRelationModel.class);
        }

        CreateAssetPoolRelationResponse resp = BaseResponse.build(CreateAssetPoolRelationResponse.class);
        resp.setAssetPoolRelationModelList(relationModels);
        return resp;
    }

    /**
     * 查询资产资产池关联关系
     *
     * @param req 查询资产资产池关联关系请求对象
     * @return 查询资产资产池关联关系响应对象
     */
    @Override
    public PageQueryResponse<AssetPoolRelationModel> queryAssetPoolRelation(@Valid QueryAssetPoolRelationRequest req) throws Exception {
        PageQueryResponse<AssetPoolRelationModel> resp = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        AssetPoolRelation relation = new AssetPoolRelation();
        PropertyUtils.copyProperties(page, req);
        PropertyUtils.copyProperties(relation, req);

        int totalCount = relationDao.selectCount(relation);
        List<AssetPoolRelationModel> relationModels = new ArrayList<>();
        if (totalCount > 0) {
            List<AssetPoolRelation> relations = relationDao.selectDetail(relation, page);
            for (AssetPoolRelation a : relations) {
                AssetPoolRelationModel model = BeanUtils.copyAs(a, AssetPoolRelationModel.class);
                if (a.getAsset() != null) {
                    AssetModel assetModel = BeanUtils.copyAs(a.getAsset(), AssetModel.class);
                    model.setAssetModel(assetModel);
                }
                relationModels.add(model);
            }
        } else {
            resp = BaseResponse.build(PageQueryResponse.class, Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC);
        }
        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(relationModels);
        return resp;
    }

    /**
     * 查询资产池可匹配资产列表
     */
    @Override
    public PageQueryResponse<AssetModel> queryPoolAssetList(@Valid QueryPoolAssetListRequest req) throws Exception {
        PageQueryResponse<AssetModel> resp = BaseResponse.build(PageQueryResponse.class);
        Pool pool = poolDao.selectByCode(req.getPoolCode());
        if(null == pool){
            resp = BaseResponse.build(PageQueryResponse.class, Constants.POOL_NOT_EXIST_CODE, Constants.POOL_NOT_EXIST_DESC);
            return resp;
        }
        Page page = new Page();
        PropertyUtils.copyProperties(page, req);
        Asset asset = new Asset();
        asset.setApprovalStatus(AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());//审核通过
        asset.setCollectStatus(AssetCollectStatusEnum.ESTABLISHED.getCode());//已成立
        asset.setFinanceSubjectCode(pool.getFinanceSubjectCode());
//        asset.setStorageStatus(StorageTypeEnum.STORAGED.getCode());
        asset.setRegisterType(RegisterTypeEnum.NORMAL.getCode());

        int totalCount = assetDao.queryPoolAssetListCount(asset);
        List<AssetModel> models = new ArrayList<AssetModel>();
        if(totalCount > 0){
            List<Asset> assets = assetDao.queryPoolAssetList(asset,page);
            for(Asset asset2 : assets){
                AssetModel assetModel = BeanUtils.copyAs(asset2,AssetModel.class);
                if(null != asset2.getFinanceSubject()){
                    FinanceSubjectModel financeSubjectModel = BeanUtils.copyAs(asset2.getFinanceSubject(),FinanceSubjectModel.class);
                    assetModel.setFinanceSubjectModel(financeSubjectModel);
                }
                models.add(assetModel);
            }
        }

        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(models);
        return resp;
    }
}
