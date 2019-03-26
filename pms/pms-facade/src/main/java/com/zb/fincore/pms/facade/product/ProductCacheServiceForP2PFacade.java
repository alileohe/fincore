package com.zb.fincore.pms.facade.product;

import com.zb.fincore.pms.facade.product.dto.req.ChangeProductStockForP2PRequest;
import com.zb.fincore.pms.facade.product.dto.resp.ChangeProductStockResponse;

/**
 * 功能: 产品缓存服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/17 0017 09:44
 * 版本: V1.0
 */
public interface ProductCacheServiceForP2PFacade extends BaseProductCacheServiceFacade {


    /**
     * 占用/释放/赎回/取消 库存
     *
     * @param req
     * @return
     * @throws Exception
     */
    ChangeProductStockResponse changeProductStockForP2P(ChangeProductStockForP2PRequest reqForP2P);

}
