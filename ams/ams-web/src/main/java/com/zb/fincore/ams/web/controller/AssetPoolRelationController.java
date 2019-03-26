package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.AssetPoolRelationServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreateAssetPoolRelationRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetListForManageRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetPoolRelationRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolAssetListRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetPoolRelationResponse;
import com.zb.fincore.ams.facade.model.AssetModel;
import com.zb.fincore.ams.facade.model.AssetPoolRelationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能: 资产池资产匹配关系RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/21 0021 17:08
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/assetPoolRelationService")
public class AssetPoolRelationController {

    @Autowired
    private AssetPoolRelationServiceFacade assetPoolRelationServiceFacade;

    /**
     * 创建资产资产池关联关系
     *
     * @param req 创建资产资产池关联关系请求对象
     * @return 创建资产资产池关联关系响应对象
     */
    @RequestMapping(value = "/createAssetPoolRelation", method = RequestMethod.POST)
    public CreateAssetPoolRelationResponse createAssetPoolRelation(@RequestBody CreateAssetPoolRelationRequest req) {
        return assetPoolRelationServiceFacade.createAssetPoolRelation(req);
    }

    /**
     * 查询资产资产池关联关系
     *
     * @param req 查询资产资产池关联关系请求对象
     * @return 查询资产资产池关联关系响应对象
     */
    @RequestMapping(value = "/queryAssetPoolRelation", method = RequestMethod.POST)
    public PageQueryResponse<AssetPoolRelationModel> queryAssetPoolRelation(@RequestBody QueryAssetPoolRelationRequest req) {
        return assetPoolRelationServiceFacade.queryAssetPoolRelation(req);
    }

    /**
     * 查询资产池可匹配资产列表
     *
     * @param req 查询资产资产池关联关系请求对象
     * @return 查询资产资产池关联关系响应对象
     */
    @RequestMapping(value = "/queryPoolAssetList", method = RequestMethod.POST)
    public PageQueryResponse<AssetModel> queryPoolAssetList(@RequestBody QueryPoolAssetListRequest req) {
        return assetPoolRelationServiceFacade.queryPoolAssetList(req);
    }
}
