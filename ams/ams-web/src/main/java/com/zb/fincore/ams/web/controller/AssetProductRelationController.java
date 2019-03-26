package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.AssetProductRelationServiceFacade;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.dto.resp.QueryProductRelatedAssetInfoResponse;
import com.zb.fincore.ams.facade.model.AssetProductRelationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能: 产品资产关系RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 17:34
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/assetProductRelationService")
public class AssetProductRelationController {

    @Autowired
    private AssetProductRelationServiceFacade assetProductRelationServiceFacade;

    /**
     * 查询产品资产匹配关系
     *
     * @param req 查询产品资产匹配关系请求对象
     * @return 分页查询基础响应对象
     */
    @RequestMapping(value = "/queryAssetProductRelation", method = RequestMethod.POST)
    public PageQueryResponse<AssetProductRelationModel> queryAssetProductRelation(@RequestBody QueryAssetProductRelationRequest req) {
        return assetProductRelationServiceFacade.queryAssetProductRelation(req);
    }

    /**
     * 创建产品资产匹配关系(占用资产)
     *
     * @param req 创建产品资产匹配关系请求对象
     * @return 创建产品资产匹配关系响应对象
     */
    @RequestMapping(value = "/createAssetProductRelation", method = RequestMethod.POST)
    public CreateAssetProductRelationResponse createAssetProductRelation(@RequestBody CreateAssetProductRelationRequest req) {
        return assetProductRelationServiceFacade.createAssetProductRelation(req);
    }

    /**
     * 更改产品资产匹配关系(资产匹配/赎回释放)
     * @param req 更改产品资产匹配关系请求对象
     * @return 更改产品资产匹配关系响应对象
     */
    @RequestMapping(value = "/changeAssetProductRelation", method = RequestMethod.POST)
    public BaseResponse changeAssetProductRelation(@RequestBody ChangeAssetProductRelationRequest req) {
        return assetProductRelationServiceFacade.changeAssetProductRelation(req);
    }

    /**
     * 产品未售出释放资产
     * @param req 产品未售出释放资产请求对象
     * @return 产品未售出释放资产响应对象
     */
    @RequestMapping(value = "/releaseAssetForProductUnSale", method = RequestMethod.POST)
    public BaseResponse releaseAssetForProductUnSale(@RequestBody ChangeAssetProductRelationRequest req) {
        return assetProductRelationServiceFacade.releaseAssetForProductUnSale(req);
    }

    /**
     * 取消产品资产匹配关系(产品审核不通过,占用资产释放)
     * @param req 取消产品资产匹配关系请求对象
     * @return 取消产品资产匹配关系响应对象
     */
    @RequestMapping(value = "/cancelAssetProductRelation", method = RequestMethod.POST)
    public BaseResponse cancelAssetProductRelation(@RequestBody CancelAssetProductRelationRequest req) {
        return assetProductRelationServiceFacade.cancelAssetProductRelation(req);
    }


    /**
     * 查询产品关联资产信息
     * @param req 查询产品关联资产请求对象
     * @return 查询产品关联资产响应对象
     */
    @RequestMapping(value = "/queryProductRelatedAssetInfo", method = RequestMethod.POST)
    public QueryProductRelatedAssetInfoResponse queryProductRelatedAssetInfo(@RequestBody QueryProductRelatedAssetInfoRequest req) {
        return assetProductRelationServiceFacade.queryProductRelatedAssetInfo(req);
    }
}
