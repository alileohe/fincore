package com.zb.fincore.ams.facade;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.dto.req.QueryAssetRepayPlanListRequest;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;

/**
 * 功能: 资产回款计划服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 09:50
 * 版本: V1.0
 */
public interface AssetRepayPlanServiceFacade {

    /**
     * 查询资产回款计划列表
     *
     * @param req 查询资产回款计划列表请求
     * @return 查询资产回款计划列表响应
     */
    PageQueryResponse<AssetRepayPlanModel> queryAssetRepayPlanList(QueryAssetRepayPlanListRequest req);
}
