package com.zb.fincore.pms.facade.product.impl;

import com.zb.fincore.pms.common.dto.BaseResponse;
import com.zb.fincore.pms.common.exception.ExceptionHandler;
import com.zb.fincore.pms.facade.product.ProductJobServiceFacade;
import com.zb.fincore.pms.facade.product.dto.req.UpdateProductCollectStatusRequest;
import com.zb.fincore.pms.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 产品数据库Facade实现类
 * 创建: MABIAO
 * 日期: 2017/4/6 0006 16:58
 * 版本: V1.0
 *
 */
@Service
public class ProductJobServiceFacadeImpl implements ProductJobServiceFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 产品存续期结束 --》产品到期 JOB调用
     * @return
     */
    @Override
    public BaseResponse putProductOutValue() {
        try {
            return productService.putProductOutValue();
        } catch (Exception e) {
            return exceptionHandler.handleException(e,BaseResponse.class);
        }
    }

    /**
     * 募集期结束 --》产品待成立    JOB调用
     * @return
     */
    @Override
    public BaseResponse putProductWaitingEstablish() {
        try {
            return productService.putProductWaitingEstablish();
        } catch (Exception e) {
            return exceptionHandler.handleException(e, BaseResponse.class);
        }
    }
    
    /**
     * 募集期结束 --》产品存续期    JOB调用
     * @return
     */
    @Override
    public BaseResponse putProductValuing() {
        try {
            return productService.putProductValuing();
        } catch (Exception e) {
            return exceptionHandler.handleException(e, BaseResponse.class);
        }
    }

    /**
     * 产品募集期开始 JOB调用
     * @return
     */
    @Override
    public BaseResponse startProductRaising() {
        try {
            return productService.startProductRaising();
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 产品存续期 开始起息 JOB调用
     * @return
     */
    @Override
    public BaseResponse putProductInValueOfJob() {
        try {
            return productService.putProductInValueOfJob();
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * JOB 阶梯信息更新
     * description : 阶梯产品每个收益阶段结束，更新为新的阶段阶梯信息
     * @return
     */
    @Override
    public BaseResponse updateProductLadderInfo() {
        try {
            return productService.updateProductLadderInfo();
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }
}
