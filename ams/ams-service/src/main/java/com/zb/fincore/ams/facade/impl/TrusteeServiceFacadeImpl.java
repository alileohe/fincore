package com.zb.fincore.ams.facade.impl;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.TrusteeServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreateTrusteeRequest;
import com.zb.fincore.ams.facade.dto.req.QueryTrusteeListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryTrusteeRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateTrusteeResponse;
import com.zb.fincore.ams.facade.model.TrusteeModel;
import com.zb.fincore.ams.service.TrusteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 受托方服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 17:12
 * 版本: V1.0
 */
@Service
public class TrusteeServiceFacadeImpl implements TrusteeServiceFacade {

    @Autowired
    private TrusteeService trusteeService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 创建受托方
     *
     * @param req 创建受托方请求对象
     * @return 创建受托方响应对象
     */
    @Override
    public CreateTrusteeResponse createTrustee(CreateTrusteeRequest req) {
        try {
            return trusteeService.createTrustee(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, CreateTrusteeResponse.class);
        }
    }

    /**
     * 查询受托方列表
     *
     * @param req 查询受托方列表请求对象
     * @return 分页查询基础响应对象
     */
    @Override
    public PageQueryResponse<TrusteeModel> queryTrusteeList(QueryTrusteeListRequest req) {
        try {
            return trusteeService.queryTrusteeList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
    }

    /**
     * 查询受托方详情
     *
     * @param req 查询受托方详情请求
     * @return 基础查询响应对象
     */
    @Override
    public QueryResponse<TrusteeModel> queryTrustee(QueryTrusteeRequest req) {
        try {
            return trusteeService.queryTrustee(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, QueryResponse.class);
        }
    }
}
