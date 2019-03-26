package com.zb.fincore.ams.facade.impl;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.FinanceSubjectServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreateFinanceSubjectRequest;
import com.zb.fincore.ams.facade.dto.req.QueryFinanceSubjectListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryFinanceSubjectRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateFinanceSubjectResponse;
import com.zb.fincore.ams.facade.model.FinanceSubjectModel;
import com.zb.fincore.ams.service.FinanceSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 融资主体服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 13:35
 * 版本: V1.0
 */
@Service
public class FinanceSubjectServiceFacadeImpl implements FinanceSubjectServiceFacade {

    @Autowired
    private FinanceSubjectService financeSubjectService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 创建融资主体
     *
     * @param req 创建融资主体请求对象
     * @return 创建融资主体响应对象
     */
    @Override
    public CreateFinanceSubjectResponse createFinanceSubject(CreateFinanceSubjectRequest req) {
        try {
            return financeSubjectService.createFinanceSubject(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, CreateFinanceSubjectResponse.class);
        }
    }

    /**
     * 查询融资主体列表
     *
     * @param req 查询融资主体列表请求对象
     * @return 分页查询基础响应对象
     */
    @Override
    public PageQueryResponse<FinanceSubjectModel> queryFinanceSubjectList(QueryFinanceSubjectListRequest req) {
        try {
            return financeSubjectService.queryFinanceSubjectList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
    }

    /**
     * 查询融资主体详情
     *
     * @param req 查询融资主体详情请求
     * @return 基础查询响应对象
     */
    @Override
    public QueryResponse<FinanceSubjectModel> queryFinanceSubject(QueryFinanceSubjectRequest req) {
        try {
            return financeSubjectService.queryFinanceSubject(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, QueryResponse.class);
        }
    }
}
