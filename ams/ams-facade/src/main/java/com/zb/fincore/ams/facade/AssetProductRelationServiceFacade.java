package com.zb.fincore.ams.facade;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.dto.resp.QueryProductRelatedAssetInfoResponse;
import com.zb.fincore.ams.facade.model.AssetProductRelationModel;

/**
 * 功能: 产品资产匹配关系服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 17:31
 * 版本: V1.0
 */
public interface AssetProductRelationServiceFacade {

    /**
     * 查询产品资产匹配关系
     *
     * @param req 查询产品资产匹配关系请求对象
     * @return 分页查询基础响应对象
     */
    PageQueryResponse<AssetProductRelationModel> queryAssetProductRelation(QueryAssetProductRelationRequest req);

    /**
     * 创建产品资产匹配关系(占用资产)
     *
     * @param req 创建产品资产匹配关系请求对象
     * @return 创建产品资产匹配关系响应对象
     */
    CreateAssetProductRelationResponse createAssetProductRelation(CreateAssetProductRelationRequest req);

    /**
     * 更改产品资产匹配关系(资产匹配/赎回释放)
     *
     * @param req 更改产品资产匹配关系请求对象
     * @return 更改产品资产匹配关系响应对象
     */
    BaseResponse changeAssetProductRelation(ChangeAssetProductRelationRequest req);

    /**
     * 产品未售出释放资产
     * @param req 产品未售出释放资产请求对象
     * @return 产品未售出释放资产响应对象
     */
    BaseResponse releaseAssetForProductUnSale(ChangeAssetProductRelationRequest req);

    /**
     * 取消产品资产匹配关系(产品审核不通过,占用资产释放)
     *
     * @param req 取消产品资产匹配关系请求对象
     * @return 取消产品资产匹配关系响应对象
     */
    BaseResponse cancelAssetProductRelation(CancelAssetProductRelationRequest req);

    /**
     * 查询产品关联资产信息
     * @param req 查询产品关联资产请求对象
     * @return 查询产品关联资产响应对象
     */
    QueryProductRelatedAssetInfoResponse queryProductRelatedAssetInfo(QueryProductRelatedAssetInfoRequest req);
}
