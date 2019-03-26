package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.AssetRepayPlanServiceFacade;
import com.zb.fincore.ams.facade.dto.req.QueryAssetRepayPlanListRequest;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能: 资产回款计划服务RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 10:11
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/assetRepayPlanService")
public class AssetRepayPlanController {

    @Autowired
    private AssetRepayPlanServiceFacade assetRepayPlanServiceFacade;

    /**
     * 查询资产回款计划列表
     *
     * @param req 查询资产回款计划列表请求
     * @return 查询资产回款计划列表响应
     */
    @RequestMapping(value = "/queryAssetRepayPlanList", method = RequestMethod.POST)
    public PageQueryResponse<AssetRepayPlanModel> queryAssetRepayPlanList(QueryAssetRepayPlanListRequest req) {
        return assetRepayPlanServiceFacade.queryAssetRepayPlanList(req);
    }
}
