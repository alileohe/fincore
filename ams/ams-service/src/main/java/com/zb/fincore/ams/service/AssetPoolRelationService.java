package com.zb.fincore.ams.service;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.dto.req.CreateAssetPoolRelationRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetPoolRelationRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolAssetListRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetPoolRelationResponse;
import com.zb.fincore.ams.facade.model.AssetModel;
import com.zb.fincore.ams.facade.model.AssetPoolRelationModel;

import javax.validation.Valid;

/**
 * 功能: 资产池资产匹配关系服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/21 0021 13:47
 * 版本: V1.0
 */
public interface AssetPoolRelationService {

    /**
     * 创建资产资产池关联关系
     *
     * @param req 创建资产资产池关联关系请求对象
     * @return 创建资产资产池关联关系响应对象
     */
    CreateAssetPoolRelationResponse createAssetPoolRelation(@Valid CreateAssetPoolRelationRequest req) throws Exception;

    /**
     * 查询资产资产池关联关系
     *
     * @param req 查询资产资产池关联关系请求对象
     * @return 查询资产资产池关联关系响应对象
     */
    PageQueryResponse<AssetPoolRelationModel> queryAssetPoolRelation(@Valid QueryAssetPoolRelationRequest req) throws Exception;

    /**
     * 查询资产池可匹配资产列表
     */
    PageQueryResponse<AssetModel> queryPoolAssetList(@Valid QueryPoolAssetListRequest req) throws Exception;
}
