package com.zb.fincore.ams.service;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.dto.req.CreateTrusteeRequest;
import com.zb.fincore.ams.facade.dto.req.QueryTrusteeListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryTrusteeRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateTrusteeResponse;
import com.zb.fincore.ams.facade.model.TrusteeModel;

import javax.validation.Valid;

/**
 * 功能: 受托方服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 17:04
 * 版本: V1.0
 */
public interface TrusteeService {

    /**
     * 创建受托方
     *
     * @param req 创建受托方请求对象
     * @return 创建受托方响应对象
     */
    CreateTrusteeResponse createTrustee(@Valid CreateTrusteeRequest req) throws Exception;

    /**
     * 查询受托方列表
     *
     * @param req 查询受托方列表请求对象
     * @return 分页查询基础响应对象
     */
    PageQueryResponse<TrusteeModel> queryTrusteeList(QueryTrusteeListRequest req) throws Exception;

    /**
     * 查询受托方详情
     *
     * @param req 查询受托方详情请求
     * @return 基础查询响应对象
     */
    QueryResponse<TrusteeModel> queryTrustee(@Valid QueryTrusteeRequest req) throws Exception;
}
