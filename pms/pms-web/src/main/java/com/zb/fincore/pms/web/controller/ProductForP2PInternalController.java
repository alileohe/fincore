package com.zb.fincore.pms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zb.fincore.pms.common.dto.BaseResponse;
import com.zb.fincore.pms.common.dto.PageQueryResponse;
import com.zb.fincore.pms.common.model.GenericQueryListResponse;
import com.zb.fincore.pms.common.model.GenericResponse;
import com.zb.fincore.pms.facade.product.ProductServiceForP2PFacade;
import com.zb.fincore.pms.facade.product.dto.req.QueryProductListRequest;
import com.zb.fincore.pms.facade.product.dto.req.UpdateProductSyncStatusRequest;
import com.zb.fincore.pms.facade.product.model.ProductModel;

/**
 * 供唐小僧调用接口   不解密
 * Created on 2017/8/17.
 */
@RestController
@RequestMapping(value = "/internal/productForP2PService")
public class ProductForP2PInternalController {

    @Autowired
    ProductServiceForP2PFacade productServiceForP2PFacade;

    /**
     * 货架系统P2P定期待上架产品列表查询
     * <p/>
     * 测试时浏览器地址栏输入：http://localhost:8080/pms/internal/productForP2PService/queryProductList.json
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/queryProductList", method = RequestMethod.POST)
    public GenericQueryListResponse<ProductModel> queryProductList(@RequestBody QueryProductListRequest req) {
        PageQueryResponse<ProductModel> pageQueryResponse = productServiceForP2PFacade.queryProductList(req);
        GenericQueryListResponse<ProductModel> response = GenericQueryListResponse.build(GenericQueryListResponse.class);
        response.setCode(pageQueryResponse.getRespCode());
        response.setMsg(pageQueryResponse.getRespMsg());
        response.setDataList(pageQueryResponse.getDataList());
        return response;
    }
    
    /**
     * 产品上架（产品同步状态 更新），唐小僧查询上线的产品列表之后，单个产品做落库登记 . <br/>
     * 产品接收 修改同步状态为“已同步”
     * 测试时浏览器地址栏输入：http://localhost:8080/pms/internal/productForP2PService/updateProductSynStatus.json
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "/updateProductSynStatus", method = RequestMethod.POST)
    public GenericResponse updateProductSynStatus(@RequestBody UpdateProductSyncStatusRequest req) {
    	BaseResponse baseResponse = productServiceForP2PFacade.updateProductSynStatus(req);
    	GenericResponse response = GenericResponse.build();
        response.setCode(baseResponse.getRespCode());
        response.setMsg(baseResponse.getRespMsg());
        return response;
    }
    
}
