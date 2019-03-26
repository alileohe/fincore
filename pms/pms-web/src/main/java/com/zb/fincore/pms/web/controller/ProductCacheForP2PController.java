package com.zb.fincore.pms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zb.fincore.pms.common.dto.BaseResponse;
import com.zb.fincore.pms.common.dto.QueryResponse;
import com.zb.fincore.pms.facade.line.dto.req.QueryCacheProductRequest;
import com.zb.fincore.pms.facade.line.dto.req.QueryCacheProductStockRequest;
import com.zb.fincore.pms.facade.product.ProductCacheServiceForP2PFacade;
import com.zb.fincore.pms.facade.product.dto.req.ChangeProductStockForP2PRequest;
import com.zb.fincore.pms.facade.product.dto.req.FreezeProductStockRequest;
import com.zb.fincore.pms.facade.product.dto.resp.ChangeProductStockResponse;
import com.zb.fincore.pms.facade.product.dto.resp.FreezeProductStockResponse;
import com.zb.fincore.pms.facade.product.model.ProductModel;
import com.zb.fincore.pms.facade.product.model.ProductStockModel;

/**
 * 功能: 产品缓存服务RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/12 0012 18:47
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/productCacheForP2PService")
public class ProductCacheForP2PController {

    @Autowired
    private ProductCacheServiceForP2PFacade productCacheServiceForP2PFacade;
    
    

    /**
     * 刷新产品缓存
     *
     * @return 通用结果
     */
    @RequestMapping(value = "/refreshProductCache", method = RequestMethod.POST)
    public BaseResponse refreshProductCache() {
        return productCacheServiceForP2PFacade.refreshProductCache();
    }

    /**
     * 查询缓存中产品详情
     *
     * @param req 产品详情查询请求对象
     * @return 产品详情询响应对象
     */
    @RequestMapping(value = "/queryProduct", method = RequestMethod.POST)
    public QueryResponse<ProductModel> queryProduct(@RequestBody QueryCacheProductRequest req) {
        return productCacheServiceForP2PFacade.queryProduct(req);
    }

    /**
     * 查询缓存中产品库存信息
     *
     * @param req 查询请求对象
     * @return 响应对象
     */
    @RequestMapping(value = "/queryProductStock", method = RequestMethod.POST)
    public QueryResponse<ProductStockModel> queryProductStock(@RequestBody QueryCacheProductStockRequest req) {
        return productCacheServiceForP2PFacade.queryProductStock(req);
    }

    /**
     * 冻结产品库存
     *
     * @param req 冻结产品库存请求对象
     * @return 响应对象
     */
    @RequestMapping(value = "/freezeProductStock", method = RequestMethod.POST)
    public FreezeProductStockResponse freezeProductStock(@RequestBody FreezeProductStockRequest req) {
        return productCacheServiceForP2PFacade.freezeProductStock(req);
    }

    /**
     * 占用/释放/赎回/取消 库存（P2P）
     * 
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/changeProductStock", method = RequestMethod.POST)
    public ChangeProductStockResponse changeProductStock(@RequestBody ChangeProductStockForP2PRequest req) {
        return productCacheServiceForP2PFacade.changeProductStockForP2P(req);
    }
}
