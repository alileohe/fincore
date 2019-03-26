package com.zb.fincore.pms.service.product;


import javax.validation.Valid;

import com.zb.fincore.pms.facade.product.dto.req.ChangeProductStockForP2PRequest;
import com.zb.fincore.pms.facade.product.dto.resp.ChangeProductStockResponse;

/**
 * 功能: 产品缓存接口类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/6 0006 16:58
 * 版本: V1.0
 */
public interface ProductCacheForP2PService extends ProductCacheService {


    /**
     * 占用/释放/赎回/取消 库存
     *
     * @param req
     * @return
     * @throws Exception
     */
    ChangeProductStockResponse changeProductStockForP2P(@Valid ChangeProductStockForP2PRequest reqForP2P) throws Exception;

}
