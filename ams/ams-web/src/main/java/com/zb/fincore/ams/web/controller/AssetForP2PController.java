package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.facade.AssetForP2PFacade;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 功能: P2P资产RESTFUL接口
 * 创建: mabiao - mabiao@zillionfortune.com
 * 日期: 2017-9-1
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/assetForP2PService")
public class AssetForP2PController {

    @Autowired
    private AssetForP2PFacade assetForP2PFacade;

    /**
     * 创建产品资产匹配关系
     * @param req 创建产品资产匹配关系请求对象
     * @return 创建产品资产匹配关系响应对象
     */
    @RequestMapping(value = "/createAssetProductRelation", method = RequestMethod.POST)
    public CreateAssetProductRelationResponse createAssetProductRelation(@RequestBody CreateAssetProductRelationRequest req) {
        return assetForP2PFacade.createAssetProductRelation(req);
    }

    /**
     * 创建资产
     */
    @RequestMapping(value = "/createAssetForP2P", method = RequestMethod.POST)
    public CreateAssetResponse createAssetForP2P(@RequestBody CreateAssetForP2PRequest req) {
        return assetForP2PFacade.createAssetForP2P(req);
    }

    /**
     * 资产匹配
     */
    @RequestMapping(value = "/saveAssetProductRelation", method = RequestMethod.POST)
    public BaseResponse saveAssetProductRelation(@RequestBody RelateAssetProductForP2PRequest req) {
        return assetForP2PFacade.saveAssetProductRelation(req);
    }

    /**
     * 更新资产还款金额
     */
    @RequestMapping(value = "/updateAssetForP2P", method = RequestMethod.POST)
    public CreateAssetResponse updateAssetForP2P(@RequestBody CreateAssetForP2PRequest req) {
        return assetForP2PFacade.createAssetForP2P(req);
    }
    
    /**
     * 更新资产库存
     */
    @RequestMapping(value = "/updateAssetStock", method = RequestMethod.POST)
    public BaseResponse updateAssetStock(@RequestBody UpdateAssetStockRequest req) {
        return assetForP2PFacade.updateAssetStock(req);
    }

}
