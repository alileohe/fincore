package com.zb.fincore.ams.facade;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.facade.dto.req.CreateAssetForP2PRequest;
import com.zb.fincore.ams.facade.dto.req.CreateAssetProductRelationRequest;
import com.zb.fincore.ams.facade.dto.req.RelateAssetProductForP2PRequest;
import com.zb.fincore.ams.facade.dto.req.UpdateAssetStockRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;

/**
 * 功能: P2P资产服务接口
 * 创建: mabiao - mabiao@zillionfortune.com
 * 日期: 2017-9-1
 * 版本: V1.0
 */
public interface AssetForP2PFacade {

    /**
     * 创建产品资产匹配关系(占用资产)
     *
     * @param req 创建产品资产匹配关系请求对象
     * @return 创建产品资产匹配关系响应对象
     */
    CreateAssetProductRelationResponse createAssetProductRelation(CreateAssetProductRelationRequest req);

    /**
     * 创建资产
     * @param req
     * @return
     */
    CreateAssetResponse createAssetForP2P(CreateAssetForP2PRequest req);

    /**
     * 资产产品匹配
     */
    BaseResponse saveAssetProductRelation(RelateAssetProductForP2PRequest req);
    
    /**
     * 更新资产库存
     */
    BaseResponse updateAssetStock(UpdateAssetStockRequest req);
}
