package com.zb.fincore.ams.facade.impl;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.AssetRepayPlanServiceFacade;
import com.zb.fincore.ams.facade.dto.req.QueryAssetRepayPlanListRequest;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;
import com.zb.fincore.ams.service.AssetRepayPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 资产回款计划服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 09:56
 * 版本: V1.0
 */
@Service
public class AssetRepayPlanServiceFacadeImpl implements AssetRepayPlanServiceFacade {

    @Autowired
    private AssetRepayPlanService assetRepayPlanService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 查询资产回款计划列表
     *
     * @param req 查询资产回款计划列表请求
     * @return 查询资产回款计划列表响应
     */
    @Override
    public PageQueryResponse<AssetRepayPlanModel> queryAssetRepayPlanList(QueryAssetRepayPlanListRequest req) {
        try {
            return assetRepayPlanService.queryAssetRepayPlanList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
    }
}
