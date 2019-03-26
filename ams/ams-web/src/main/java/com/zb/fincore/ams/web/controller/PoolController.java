package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.PoolServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreatePoolRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolLeftAssetRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolRequest;
import com.zb.fincore.ams.facade.dto.resp.CreatePoolResponse;
import com.zb.fincore.ams.facade.model.PoolLeftAssetModel;
import com.zb.fincore.ams.facade.model.PoolModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能: 资产池RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 15:49
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/poolService")
public class PoolController {

    @Autowired
    private PoolServiceFacade poolServiceFacade;

    /**
     * 创建资产池
     *
     * @param req 创建资产池请求对象
     * @return 创建资产池响应对象
     */
    @RequestMapping(value = "/createPool", method = RequestMethod.POST)
    public CreatePoolResponse createPool(@RequestBody CreatePoolRequest req) {
        return poolServiceFacade.createPool(req);
    }

    /**
     * 查询资产池列表
     *
     * @param req 查询资产池列表请求对象
     * @return 查询资产池列表响应对象
     */
    @RequestMapping(value = "/queryPoolList", method = RequestMethod.POST)
    public PageQueryResponse<PoolModel> queryPoolList(@RequestBody QueryPoolListRequest req) {
        return poolServiceFacade.queryPoolList(req);
    }

    /**
     * 查询资产池详情
     *
     * @param req 查询资产池详情请求对象
     * @return 查询资产池详情响应对象
     */
    @RequestMapping(value = "/queryPool", method = RequestMethod.POST)
    public QueryResponse<PoolModel> queryPool(@RequestBody QueryPoolRequest req) {
        return poolServiceFacade.queryPool(req);
    }

    /**
     * 查询资产池剩余资产
     * @param req 查询资产池剩余资产请求对象
     * @return 查询资产池剩余资产响应对象
     */
    @RequestMapping(value = "/queryLeftAssetAmountList", method = RequestMethod.POST)
    public PageQueryResponse<PoolLeftAssetModel> queryLeftAssetAmountList(@RequestBody QueryPoolLeftAssetRequest req) {
        return poolServiceFacade.queryLeftAssetAmountList(req);
    }
}
