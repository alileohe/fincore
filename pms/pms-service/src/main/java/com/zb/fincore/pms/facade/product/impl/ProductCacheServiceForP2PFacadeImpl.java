package com.zb.fincore.pms.facade.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zb.fincore.pms.common.exception.ExceptionHandler;
import com.zb.fincore.pms.facade.product.ProductCacheServiceForP2PFacade;
import com.zb.fincore.pms.facade.product.dto.req.ChangeProductStockForP2PRequest;
import com.zb.fincore.pms.facade.product.dto.resp.ChangeProductStockResponse;
import com.zb.fincore.pms.service.product.ProductCacheForP2PService;

/**
 * 功能: 产品缓存Facade实现类
 * 创建: MABIAO
 * 日期: 2017/4/6 0006 16:58
 * 版本: V1.0
 */
@Service
public class ProductCacheServiceForP2PFacadeImpl extends AbstractProductCacheServiceFacadeImpl implements ProductCacheServiceForP2PFacade {

    @Autowired
    private ProductCacheForP2PService productCacheForP2PService;

    @Autowired
    private ExceptionHandler exceptionHandler;



    /**
     * 占用/释放/赎回/取消 库存
     *
     * @param reqForP2P
     * @return
     * @throws Exception
     */
    public ChangeProductStockResponse changeProductStockForP2P(ChangeProductStockForP2PRequest reqForP2P) {
        try {
            return productCacheForP2PService.changeProductStockForP2P(reqForP2P);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, ChangeProductStockResponse.class);
        }
    }

}
