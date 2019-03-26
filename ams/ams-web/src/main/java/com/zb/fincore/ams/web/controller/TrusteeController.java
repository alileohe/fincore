package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.TrusteeServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreateTrusteeRequest;
import com.zb.fincore.ams.facade.dto.req.QueryTrusteeListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryTrusteeRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateTrusteeResponse;
import com.zb.fincore.ams.facade.model.TrusteeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能: 受托方服务RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/29 0029 13:51
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/trusteeService")
public class TrusteeController {

    @Autowired
    private TrusteeServiceFacade trusteeServiceFacade;

    /**
     * 创建受托方
     *
     * @param req 创建受托方请求对象
     * @return 创建受托方响应对象
     */
    @RequestMapping(value = "/createTrustee", method = RequestMethod.POST)
    public CreateTrusteeResponse createTrustee(@RequestBody CreateTrusteeRequest req) {
        return trusteeServiceFacade.createTrustee(req);
    }

    /**
     * 查询受托方列表
     *
     * @param req 查询受托方列表请求对象
     * @return 分页查询基础响应对象
     */
    @RequestMapping(value = "/queryTrusteeList", method = RequestMethod.POST)
    public PageQueryResponse<TrusteeModel> queryTrusteeList(@RequestBody QueryTrusteeListRequest req) {
        return trusteeServiceFacade.queryTrusteeList(req);

    }

    /**
     * 查询受托方详情
     *
     * @param req 查询受托方详情请求
     * @return 基础查询响应对象
     */
    @RequestMapping(value = "/queryTrustee", method = RequestMethod.POST)
    public QueryResponse<TrusteeModel> queryTrustee(@RequestBody QueryTrusteeRequest req) {
        return trusteeServiceFacade.queryTrustee(req);
    }
}
