package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.FinanceSubjectServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreateFinanceSubjectRequest;
import com.zb.fincore.ams.facade.dto.req.QueryFinanceSubjectListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryFinanceSubjectRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateFinanceSubjectResponse;
import com.zb.fincore.ams.facade.model.FinanceSubjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能: 融资主体服务RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/29 0029 13:51
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/financeSubjectService")
public class FinanceSubjectController {

    @Autowired
    private FinanceSubjectServiceFacade financeSubjectServiceFacade;

    /**
     * 创建融资主体
     *
     * @param req 创建融资主体请求对象
     * @return 创建融资主体响应对象
     */
    @RequestMapping(value = "/createFinanceSubject", method = RequestMethod.POST)
    public CreateFinanceSubjectResponse createFinanceSubject(@RequestBody CreateFinanceSubjectRequest req) {
        return financeSubjectServiceFacade.createFinanceSubject(req);
    }

    /**
     * 查询融资主体列表
     *
     * @param req 查询融资主体列表请求对象
     * @return 分页查询基础响应对象
     */
    @RequestMapping(value = "/queryFinanceSubjectList", method = RequestMethod.POST)
    public PageQueryResponse<FinanceSubjectModel> queryFinanceSubjectList(@RequestBody QueryFinanceSubjectListRequest req) {
        return financeSubjectServiceFacade.queryFinanceSubjectList(req);
    }

    /**
     * 查询融资主体详情
     *
     * @param req 查询融资主体详情请求
     * @return 基础查询响应对象
     */
    @RequestMapping(value = "/queryFinanceSubject", method = RequestMethod.POST)
    public QueryResponse<FinanceSubjectModel> queryFinanceSubject(@RequestBody QueryFinanceSubjectRequest req) {
        return financeSubjectServiceFacade.queryFinanceSubject(req);
    }
}
