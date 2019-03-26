package com.zb.fincore.ams.service;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.QueryProductRelatedAssetInfoResponse;
import com.zb.fincore.ams.facade.model.AssetProductRelationModel;

import javax.validation.Valid;
import java.util.List;

/**
 * 功能: P2P资产服务接口
 * 创建: mabiao - mabiao@zillionfortune.com
 * 日期: 2017-9-1
 * 版本: V1.0
 */
public interface AssetForP2PService {

    /**
     * 创建产品资产匹配关系
     * @param req 创建产品资产匹配关系请求对象
     * @return 创建产品资产匹配关系响应对象
     */
    CreateAssetProductRelationResponse createAssetProductRelation(CreateAssetProductRelationRequest req) throws Exception;

    /**
     * 创建资产
     * @param req
     * @return
     * @throws Exception
     */
    CreateAssetResponse createAssetForP2P(@Valid CreateAssetForP2PRequest req) throws Exception;

    /**
     * 资产产品匹配
     */
    BaseResponse saveAssetProductRelation(@Valid RelateAssetProductForP2PRequest req) throws Exception;
    
    /**
     * 更新资产库存
     */
    BaseResponse updateAssetStock(@Valid UpdateAssetStockRequest req) throws Exception;
}
