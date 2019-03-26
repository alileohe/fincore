package com.zb.fincore.pms.service.product;

import javax.validation.Valid;

import com.zb.fincore.pms.facade.product.dto.req.QueryProductInfoForTradeRequest;
import com.zb.fincore.pms.facade.product.dto.resp.QueryProductInfoResponse;

/**
 * 功能: 产品数据库接口类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/6 0006 16:57
 * 版本: V1.0
 */
public interface ProductForP2PService extends BaseProductService {
	/**
     * 供交易系统调用  产品详情查询
     * @param req
     * @return
     */
	QueryProductInfoResponse queryProductInfoForTrade(@Valid QueryProductInfoForTradeRequest req) throws Exception;
}
