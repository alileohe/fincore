package com.zb.fincore.ams.facade;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.dto.req.CreatePoolRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolLeftAssetRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolRequest;
import com.zb.fincore.ams.facade.dto.resp.CreatePoolResponse;
import com.zb.fincore.ams.facade.model.PoolLeftAssetModel;
import com.zb.fincore.ams.facade.model.PoolModel;

/**
 * 功能: 资产池服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 15:48
 * 版本: V1.0
 */
public interface PoolServiceFacade {

    /**
     * 创建资产池
     *
     * @param req 创建资产池请求对象
     * @return 创建资产池响应对象
     */
    CreatePoolResponse createPool(CreatePoolRequest req);

    /**
     * 查询资产池列表
     *
     * @param req 查询资产池列表请求对象
     * @return 查询资产池列表响应对象
     */
    PageQueryResponse<PoolModel> queryPoolList(QueryPoolListRequest req);

    /**
     * 查询资产池详情
     *
     * @param req 查询资产池详情请求对象
     * @return 查询资产池详情响应对象
     */
    QueryResponse<PoolModel> queryPool(QueryPoolRequest req);

    /**
     * 查询资产池剩余资产
     */
    PageQueryResponse<PoolLeftAssetModel> queryLeftAssetAmountList(QueryPoolLeftAssetRequest req);

}
