package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.AssetServiceFacade;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import com.zb.fincore.ams.facade.model.AssetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能: 资产RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 11:25
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/assetService")
public class AssetController {

    @Autowired
    private AssetServiceFacade assetServiceFacade;

    /**
     * 创建底层资产
     *
     * @param req 创建底层资产请求
     * @return 创建底层资产响应
     */
    @RequestMapping(value = "/createAsset", method = RequestMethod.POST)
    public CreateAssetResponse createAsset(@RequestBody CreateAssetRequest req) {
        return assetServiceFacade.createAsset(req);
    }

    /**
     * 批量创建底层资产
     *
     * @param req 批量创建底层资产请求
     * @return 批量创建底层资产请求
     */
    @RequestMapping(value = "/batchCreateAsset", method = RequestMethod.POST)
    public BatchCreateAssetResponse batchCreateAsset(@RequestBody BatchCreateAssetRequest<CreateAssetRequest> req) {
        return assetServiceFacade.batchCreateAsset(req);
    }

    /**
     * 资产管理查询资产列表
     *
     * @param req 查询资产列表请求
     * @return 查询资产列表响应
     */
    @RequestMapping(value = "/queryAssetListForManage", method = RequestMethod.POST)
    public PageQueryResponse<AssetModel> queryAssetListForManage(@RequestBody QueryAssetListForManageRequest req) {
        return assetServiceFacade.queryAssetListForManage(req);
    }

    /**
     * 资产审核列表
     *
     * @param req 资产审核列表请求
     * @return 查询资产审核列表响应
     */
    @RequestMapping(value = "/queryAssetListForApproval", method = RequestMethod.POST)
    public PageQueryResponse<AssetModel> queryAssetListForApproval(@RequestBody QueryAssetListForManageRequest req) {
        return assetServiceFacade.queryAssetListForApproval(req);
    }

    /**
     * 查询资产详情
     *
     * @param req 查询资产详情请求
     * @return 查询资产详情响应
     */
    @RequestMapping(value = "/queryAsset", method = RequestMethod.POST)
    public QueryResponse<AssetModel> queryAsset(@RequestBody QueryAssetRequest req) {
        return assetServiceFacade.queryAsset(req);
    }

    /**
     * 资产成立JOB
     */
    @RequestMapping(value = "/putAssetEstablished", method = RequestMethod.POST)
    public BaseResponse putAssetEstablished() {
        return assetServiceFacade.putAssetEstablished();
    }

    /**
     * 资产到期JOB
     */
    @RequestMapping(value = "/putAssetExpired", method = RequestMethod.POST)
    public BaseResponse putAssetExpired() {
        return assetServiceFacade.putAssetExpired();
    }

    /**
     * 更新资产披露信息
     *
     * @param req 更新资产披露信息请求对象
     * @return 通用响应对象
     */
    @RequestMapping(value = "/updateAssetPublishInfo", method = RequestMethod.POST)
    public BaseResponse updateAssetPublishInfo(@RequestBody UpdateAssetPublishInfoRequest req) {
        return assetServiceFacade.updateAssetPublishInfo(req);
    }
}
