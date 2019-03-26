package com.zb.fincore.pms.facade.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zb.fincore.pms.common.dto.BaseResponse;
import com.zb.fincore.pms.common.dto.PageQueryResponse;
import com.zb.fincore.pms.common.exception.ExceptionHandler;
import com.zb.fincore.pms.facade.product.ProductServiceForP2PFacade;
import com.zb.fincore.pms.facade.product.dto.req.ApproveProductRequest;
import com.zb.fincore.pms.facade.product.dto.req.QueryProductInfoForTradeRequest;
import com.zb.fincore.pms.facade.product.dto.req.QueryProductInfoRequest;
import com.zb.fincore.pms.facade.product.dto.req.QueryProductListRequest;
import com.zb.fincore.pms.facade.product.dto.req.RegisterProductRequest;
import com.zb.fincore.pms.facade.product.dto.req.UpdateProductCollectStatusRequest;
import com.zb.fincore.pms.facade.product.dto.req.UpdateProductSaleStatusRequest;
import com.zb.fincore.pms.facade.product.dto.resp.QueryProductInfoResponse;
import com.zb.fincore.pms.facade.product.dto.resp.RegisterProductResponse;
import com.zb.fincore.pms.facade.product.model.ProductModel;
import com.zb.fincore.pms.service.product.ProductForP2PService;

/**
 * 功能: 产品数据库Facade实现类
 * 日期: 2017/4/6 0006 16:58
 * 版本: V1.0
 *
 */
@Service
public class ProductServiceForP2PFacadeImpl extends AbstractProductServiceFacadeImpl implements ProductServiceForP2PFacade {

    @Autowired
    private ProductForP2PService productForP2PService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 货架系统 产品注册
     * @param req
     * @return
     */
    @Override
    public RegisterProductResponse registerProduct(RegisterProductRequest req) {
        try {
            return productForP2PService.registerProduct(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, RegisterProductResponse.class);
        }
    }
    
    /**
     * 产品审核
     * @param req
     * @return
     */
    @Override
    public BaseResponse approveProduct(ApproveProductRequest req) {
        try {
            return productForP2PService.approveProduct(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,PageQueryResponse.class);
        }
    }

    /**
     * 产品详情
     * @param req
     * @return
     */
    @Override
    public QueryProductInfoResponse queryProductInfo(QueryProductInfoRequest req) {
        try {
            return productForP2PService.queryProductInfo(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, QueryProductInfoResponse.class);
        }
    }
    
    /**
     * 待上架产品列表
     * @param req
     * @return
     */
    @Override
    public PageQueryResponse<ProductModel> queryProductList(QueryProductListRequest req) {
    	try {
            return productForP2PService.queryProductListForP2P(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,PageQueryResponse.class);
        }
    }

    /**
     * 供交易系统调用  产品列表查询
     * @param req
     * @return
     */
	@Override
	public PageQueryResponse<ProductModel> queryProductListForTrade(QueryProductListRequest req) {
		try {
            return productForP2PService.queryProductListForTrade(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,PageQueryResponse.class);
        }
	}

	/**
     * 供交易系统调用  产品详情查询
     * @param req
     * @return
     */
	@Override
	public QueryProductInfoResponse queryProductInfoForTrade(QueryProductInfoForTradeRequest req) {
		try {
            return productForP2PService.queryProductInfoForTrade(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, QueryProductInfoResponse.class);
        }
	}
	
	/**
     * 产品上线(销售状态)
     * 将产品状态为 已部署 设置为上线状态
     * @param req
     * @return
     */
    @Override
    public BaseResponse putProductOnLine(UpdateProductSaleStatusRequest req) {
        try {
            return productForP2PService.putProductOnLineForP2P(req);
        }catch (Exception e){
            return exceptionHandler.handleException(e);
        }
    }
    
    /**
     * 更新产品募集状态
     */
    @Override
    public BaseResponse updateProductCollectStatus(UpdateProductCollectStatusRequest req) {
        try {
            return productForP2PService.updateProductCollectStatus(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

}
