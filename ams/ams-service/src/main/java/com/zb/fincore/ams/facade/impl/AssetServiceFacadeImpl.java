package com.zb.fincore.ams.facade.impl;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.AssetServiceFacade;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import com.zb.fincore.ams.facade.model.AssetModel;
import com.zb.fincore.ams.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 资产服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 13:34
 * 版本: V1.0
 */
@Service
public class AssetServiceFacadeImpl implements AssetServiceFacade {

    @Autowired
    private AssetService assetService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 创建底层资产
     *
     * @param req 创建底层资产请求
     * @return 创建底层资产响应
     */
    @Override
    public CreateAssetResponse createAsset(CreateAssetRequest req) {
        try {
            return assetService.createAsset(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, CreateAssetResponse.class);
        }
    }

    /**
     * 批量创建底层资产
     *
     * @param req 批量创建底层资产请求
     * @return 批量创建底层资产请求
     */
    @Override
    public BatchCreateAssetResponse batchCreateAsset(BatchCreateAssetRequest<CreateAssetRequest> req) {
        try {
            return assetService.batchCreateAsset(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, BatchCreateAssetResponse.class);
        }
    }

    /**
     * 资产管理查询资产列表
     *
     * @param req 查询资产列表请求
     * @return 查询资产列表响应
     */
    @Override
    public PageQueryResponse<AssetModel> queryAssetListForManage(QueryAssetListForManageRequest req) {
        try {
            return assetService.queryAssetListForManage(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
    }

    /**
     * 资产审核列表
     *
     * @param req 资产审核列表请求
     * @return 查询资产审核列表响应
     */
    @Override
    public PageQueryResponse<AssetModel> queryAssetListForApproval(QueryAssetListForManageRequest req) {
        try {
            return assetService.queryAssetListForApproval(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
    }

    /**
     * 查询资产详情
     *
     * @param req 查询资产详情请求
     * @return 查询资产详情响应
     */
    @Override
    public QueryResponse<AssetModel> queryAsset(QueryAssetRequest req) {
        try {
            return assetService.queryAsset(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, QueryResponse.class);
        }
    }

    /**
     * 资产成立
     */
    @Override
    public BaseResponse putAssetEstablished() {
        try {
            return assetService.putAssetEstablished();
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 资产到期
     */
    @Override
    public BaseResponse putAssetExpired() {
        try {
            return assetService.putAssetExpired();
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 更新资产披露信息
     *
     * @param req 更新资产披露信息请求对象
     * @return 通用响应对象
     */
    @Override
    public BaseResponse updateAssetPublishInfo(UpdateAssetPublishInfoRequest req) {
        try {
            return assetService.updateAssetPublishInfo(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }
}
