package com.zb.fincore.ams.service;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.dto.req.CreateAssetForP2PRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetRepayPlanListRequest;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;
import com.zb.fincore.ams.service.dal.model.Asset;

/**
 * 功能: 资产回款计划服务
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 09:30
 * 版本: V1.0
 */
public interface AssetRepayPlanService {

    /**
     * 创建资产回款计划
     *
     * @param asset 资产信息
     */
    void createAssetReplayPlan(Asset asset) throws Exception;

    /**
     * 查询资产回款计划列表
     *
     * @param req 查询资产回款计划列表请求
     * @return 查询资产回款计划列表响应
     */
    PageQueryResponse<AssetRepayPlanModel> queryAssetRepayPlanList(QueryAssetRepayPlanListRequest req) throws Exception;

    /**
     * 创建资产回款计划P2P
     * @param asset 资产信息
     */
    void createAssetReplayPlanForP2P(Asset asset,CreateAssetForP2PRequest req) throws Exception;
}
