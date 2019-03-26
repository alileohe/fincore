package com.zb.fincore.ams.service;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import com.zb.fincore.ams.facade.model.AssetModel;

import javax.validation.Valid;

/**
 * 功能: 资产服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 13:39
 * 版本: V1.0
 */
public interface AssetService {

    /**
     * 创建底层资产
     *
     * @param req 创建底层资产请求
     * @return 创建底层资产响应
     */
    CreateAssetResponse createAsset(@Valid CreateAssetRequest req) throws Exception;

    /**
     * 批量创建底层资产
     *
     * @param req 批量创建底层资产请求
     * @return 批量创建底层资产请求
     */
    BatchCreateAssetResponse batchCreateAsset(@Valid BatchCreateAssetRequest<CreateAssetRequest> req) throws Exception;

    /**
     * 资产管理查询资产列表
     *
     * @param req 查询资产列表请求
     * @return 查询资产列表响应
     */
    PageQueryResponse<AssetModel> queryAssetListForManage(QueryAssetListForManageRequest req) throws Exception;

    /**
     * 资产审核列表
     *
     * @param req 资产审核列表请求
     * @return 查询资产审核列表响应
     */
    PageQueryResponse<AssetModel> queryAssetListForApproval(QueryAssetListForManageRequest req) throws Exception;

    /**
     * 查询资产详情
     *
     * @param req 查询资产详情请求
     * @return 查询资产详情响应
     */
    QueryResponse<AssetModel> queryAsset(@Valid QueryAssetRequest req) throws Exception;

    /**
     * 资产成立
     *
     * @return
     * @throws Exception
     */
    BaseResponse putAssetEstablished() throws Exception;

    /**
     * 资产到期
     *
     * @return
     * @throws Exception
     */
    BaseResponse putAssetExpired() throws Exception;

    /**
     * 更新资产披露信息
     *
     * @param req 更新资产披露信息请求对象
     * @return 通用响应对象
     */
    BaseResponse updateAssetPublishInfo(@Valid UpdateAssetPublishInfoRequest req) throws Exception;
}
