package com.zb.fincore.ams.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.AssetCheckServiceFacade;
import com.zb.fincore.ams.facade.dto.req.QueryAssetTransactionRequest;
import com.zb.fincore.ams.facade.dto.req.QueryNotInProcessAssetRequest;
import com.zb.fincore.ams.facade.model.AssetTransactionModel;
import com.zb.fincore.ams.facade.model.InProcessAssetModel;
import com.zb.fincore.ams.facade.model.NotInProcessAssetModel;

/**
 * 功能: 资产清算RESTFUL接口
 * 创建: weizhenggong - weizhenggong@zillionfortune.com
 * 日期: 2017/7/10
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/assetCheckService")
public class AssetCheckController {
	
    @Autowired
    private AssetCheckServiceFacade assetCheckServiceFacade;
    
    /**
     * 已起息资产明细
     *
     * @param 无(不需要参数)
     * @return 审核资产响应对象
     */
    @RequestMapping(value = "/queryInProcessAsset", method = RequestMethod.POST)
    public PageQueryResponse<InProcessAssetModel> queryInProcessAsset() {
        return assetCheckServiceFacade.queryInProcessAsset();
    }

    
    
    @RequestMapping(value = "/queryNotInProcessAsset", method = RequestMethod.POST)
    public PageQueryResponse<NotInProcessAssetModel> queryNotInProcessAsset(@RequestBody QueryNotInProcessAssetRequest req) {
        return assetCheckServiceFacade.queryNotInProcessAsset(req);
    }

    @RequestMapping(value = "/queryAssetTransaction", method = RequestMethod.POST)
    public PageQueryResponse<AssetTransactionModel> queryAssetTransaction(QueryAssetTransactionRequest req) {
        return assetCheckServiceFacade.queryAssetTransaction(req);
    }

}
