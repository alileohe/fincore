package com.zb.fincore.ams.facade;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.dto.req.CreateFinanceSubjectRequest;
import com.zb.fincore.ams.facade.dto.req.QueryFinanceSubjectListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryFinanceSubjectRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateFinanceSubjectResponse;
import com.zb.fincore.ams.facade.model.FinanceSubjectModel;

/**
 * 功能: 融资主体服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 13:32
 * 版本: V1.0
 */
public interface FinanceSubjectServiceFacade {

    /**
     * 创建融资主体
     *
     * @param req 创建融资主体请求对象
     * @return 创建融资主体响应对象
     */
    CreateFinanceSubjectResponse createFinanceSubject(CreateFinanceSubjectRequest req);

    /**
     * 查询融资主体列表
     *
     * @param req 查询融资主体列表请求对象
     * @return 分页查询基础响应对象
     */
    PageQueryResponse<FinanceSubjectModel> queryFinanceSubjectList(QueryFinanceSubjectListRequest req);

    /**
     * 查询融资主体详情
     *
     * @param req 查询融资主体详情请求
     * @return 基础查询响应对象
     */
    QueryResponse<FinanceSubjectModel> queryFinanceSubject(QueryFinanceSubjectRequest req);
}
