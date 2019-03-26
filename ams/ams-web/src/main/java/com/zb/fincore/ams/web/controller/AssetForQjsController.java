package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.facade.AssetForP2PFacade;
import com.zb.fincore.ams.facade.AssetForQjsFacade;
import com.zb.fincore.ams.facade.dto.req.CreateAssetForP2PRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 功能: 侨金所资产RESTFUL接口
 * 创建: mabiao - mabiao@zillionfortune.com
 * 日期: 2017-10-10
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/assetForQjsService")
public class AssetForQjsController {

//    @Autowired
//    private AssetForQjsFacade assetForQjsFacade;
//
//    /**
//     * 创建资产
//     */
//    @RequestMapping(value = "/createAssetForQjs", method = RequestMethod.POST)
//    public CreateAssetResponse createAssetForQjs(@RequestBody CreateAssetForP2PRequest req) {
//        return assetForQjsFacade.createAssetForQjs(req);
//    }
//
//    /**
//     * 导入资产
//     */
//    @RequestMapping(value = "/importAssetForQjs", method = RequestMethod.POST)
//    public CreateAssetResponse importAssetForQjs(@RequestBody CreateAssetForP2PRequest req) {
//        return assetForQjsFacade.importAssetForQjs(req);
//    }
//
//    /**
//     * 上传信批信息
//     */
//    @RequestMapping(value = "/importAssetPublishInfo", method = RequestMethod.POST)
//    public CreateAssetResponse importAssetPublishInfo(@RequestBody CreateAssetForP2PRequest req) {
//        return assetForQjsFacade.importAssetPublishInfo(req);
//    }

}
