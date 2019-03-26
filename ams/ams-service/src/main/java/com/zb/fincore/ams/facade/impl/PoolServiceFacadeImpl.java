package com.zb.fincore.ams.facade.impl;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.PoolServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreatePoolRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolLeftAssetRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolRequest;
import com.zb.fincore.ams.facade.dto.resp.CreatePoolResponse;
import com.zb.fincore.ams.facade.model.PoolLeftAssetModel;
import com.zb.fincore.ams.facade.model.PoolModel;
import com.zb.fincore.ams.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 资产池服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 15:48
 * 版本: V1.0
 */
@Service
public class PoolServiceFacadeImpl implements PoolServiceFacade {

    @Autowired
    private PoolService poolService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 创建资产池
     *
     * @param req 创建资产池请求对象
     * @return 创建资产池响应对象
     */
    @Override
    public CreatePoolResponse createPool(CreatePoolRequest req) {
        try {
            return poolService.createPool(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, CreatePoolResponse.class);
        }
    }

    /**
     * 查询资产池列表
     *
     * @param req 查询资产池列表请求对象
     * @return 查询资产池列表响应对象
     */
    @Override
    public PageQueryResponse<PoolModel> queryPoolList(QueryPoolListRequest req) {
        try {
            return poolService.queryPoolList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
    }

    /**
     * 查询资产池详情
     *
     * @param req 查询资产池详情请求对象
     * @return 查询资产池详情响应对象
     */
    @Override
    public QueryResponse<PoolModel> queryPool(QueryPoolRequest req) {
        try {
            return poolService.queryPool(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, QueryResponse.class);
        }
    }

    /**
     * 查询资产池剩余资产
     */
    @Override
    public PageQueryResponse<PoolLeftAssetModel> queryLeftAssetAmountList(QueryPoolLeftAssetRequest req) {
        try {
            return poolService.queryLeftAssetAmountList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
    }
}
