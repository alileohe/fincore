package com.zb.fincore.ams.facade.impl;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.AssetProductRelationServiceFacade;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.dto.resp.QueryProductRelatedAssetInfoResponse;
import com.zb.fincore.ams.facade.model.AssetProductRelationModel;
import com.zb.fincore.ams.service.AssetProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 产品资产匹配关系服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 17:32
 * 版本: V1.0
 */
@Service
public class AssetProductRelationServiceFacadeImpl implements AssetProductRelationServiceFacade {

    @Autowired
    private AssetProductRelationService assetProductRelationService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 查询产品资产匹配关系
     *
     * @param req 查询产品资产匹配关系请求对象
     * @return 分页查询基础响应对象
     */
    @Override
    public PageQueryResponse<AssetProductRelationModel> queryAssetProductRelation(QueryAssetProductRelationRequest req) {
        try {
            return assetProductRelationService.queryAssetProductRelation(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
    }

    /**
     * 创建产品资产匹配关系(占用资产)
     *
     * @param req 创建产品资产匹配关系请求对象
     * @return 创建产品资产匹配关系响应对象
     */
    @Override
    public CreateAssetProductRelationResponse createAssetProductRelation(CreateAssetProductRelationRequest req) {
        try {
            return assetProductRelationService.createAssetProductRelation(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, CreateAssetProductRelationResponse.class);
        }
    }

    /**
     * 更改产品资产匹配关系(资产匹配/赎回释放)
     *
     * @param req 更改产品资产匹配关系请求对象
     * @return 更改产品资产匹配关系响应对象
     */
    @Override
    public BaseResponse changeAssetProductRelation(ChangeAssetProductRelationRequest req) {
        try {
            return assetProductRelationService.changeAssetProductRelation(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 产品未售出释放资产
     * @param req 产品未售出释放资产请求对象
     * @return 产品未售出释放资产响应对象
     */
    @Override
    public BaseResponse releaseAssetForProductUnSale(ChangeAssetProductRelationRequest req) {
        try {
            return assetProductRelationService.releaseAssetForProductUnSale(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 取消产品资产匹配关系(产品审核不通过,占用资产释放)
     *
     * @param req 取消产品资产匹配关系请求对象
     * @return 取消产品资产匹配关系响应对象
     */
    @Override
    public BaseResponse cancelAssetProductRelation(CancelAssetProductRelationRequest req) {
        try {
            return assetProductRelationService.cancelAssetProductRelation(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 查询产品关联资产信息
     * @param req 查询产品关联资产请求对象
     * @return 查询产品关联资产响应对象
     */
    @Override
    public QueryProductRelatedAssetInfoResponse queryProductRelatedAssetInfo(QueryProductRelatedAssetInfoRequest req) {
        try {
            return assetProductRelationService.queryProductRelatedAssetInfo(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,QueryProductRelatedAssetInfoResponse.class);
        }
    }
}
