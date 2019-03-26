package com.zb.fincore.ams.service.impl;

import com.zb.fincore.ams.service.ProductForP2PService;
import com.zb.fincore.common.utils.BeanUtils;
import com.zb.fincore.common.utils.DateUtils;
import com.zb.fincore.pms.facade.product.ProductServiceForP2PFacade;
import com.zb.fincore.pms.facade.product.dto.req.QueryProductInfoForTradeRequest;
import com.zb.fincore.pms.facade.product.dto.resp.QueryProductInfoResponse;
import com.zb.fincore.pms.facade.product.model.ProductModel;
import com.zb.p2p.facade.api.resp.CommonResp;
import com.zb.p2p.facade.api.resp.product.ProductDTO;
import com.zb.p2p.facade.api.resp.product.ProductPeriodDTO;
import com.zb.p2p.facade.api.resp.product.ProductProfitDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 资产预匹配查询产品服务
 * Created by zhangxin on 2017/9/5.
 */
@Service
public class ProductForP2PServiceImpl implements ProductForP2PService {

    private static Logger logger = LoggerFactory.getLogger(ProductForP2PServiceImpl.class);

    @Autowired
    ProductServiceForP2PFacade productServiceForP2PFacade;

    private static Map<String, ProductDTO> productAssetPoolMap = new ConcurrentHashMap<String, ProductDTO>();

    /**
     * 根据资产池编号，起息时间，期限查询产品  放入本地缓存
     * @param assetPoolCode
     * @param valueTime
     * @param investPeriod
     * @return
     */
    @Override
    public CommonResp<ProductDTO> queryProductInfoForAssetMatch(String assetPoolCode, Date valueTime, Integer investPeriod) {
        CommonResp<ProductDTO> commonResp = new CommonResp<ProductDTO>();

        String valueTimeStr = DateUtils.format(valueTime, DateUtils.DATE_FORMAT_YYMMDD);
        String matchPattern = assetPoolCode + "_" + valueTimeStr + "_" + String.valueOf(investPeriod);

        if(null == productAssetPoolMap.get(matchPattern)){
            ProductDTO productDTO = loadProductInfoByAssetPoolCodeAndInvestPeriod(assetPoolCode, valueTime, investPeriod);
            if(null != productDTO) {
                commonResp.setData(productDTO);
                productAssetPoolMap.put(matchPattern, productDTO);
            }
        }else{
            commonResp.setData(productAssetPoolMap.get(matchPattern));
        }
        return commonResp;
    }

    /**
     * 调用pms服务 根据资产池编号，起息时间，期限查询产品信息
     * @param assetPoolCode
     * @param valueTime
     * @param investPeriod
     * @return
     */
    private ProductDTO loadProductInfoByAssetPoolCodeAndInvestPeriod(String assetPoolCode, Date valueTime, Integer investPeriod){
        logger.info("call pms load product info service start, assetPoolCode :{}, valueTime :{}, investPeriod : {}", assetPoolCode, valueTime, investPeriod);
        QueryProductInfoForTradeRequest request = new QueryProductInfoForTradeRequest();
        request.setAssetPoolCode(assetPoolCode);
        request.setValueTime(valueTime);
        request.setInvestPeriod(investPeriod);
        QueryProductInfoResponse response = productServiceForP2PFacade.queryProductInfoForTrade(request);
        logger.info("call pms load product info result :{}", response);
        if(null == response || null == response.getProductModel()){
            logger.info("call pms load product info result2 :{}", response);
            return null;
        }
        logger.info("call pms load product info convert product :{}", response);
        return convertModel2DTO(response.getProductModel());
    }

    /**
     * 将productModel转换为ProductDTO
     * @param productModel
     * @return
     */
    private ProductDTO convertModel2DTO(ProductModel productModel){
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copy(productModel, productDTO);
        ProductProfitDTO productProfitDTO = new ProductProfitDTO();
        BeanUtils.copy(productModel.getProductProfitModel(), productProfitDTO);
        ProductPeriodDTO productPeriodDTO = new ProductPeriodDTO();
        BeanUtils.copy(productModel.getProductPeriodModel(), productPeriodDTO);
        productDTO.setProductPeriodDTO(productPeriodDTO);
        productDTO.setProductProfitDTO(productProfitDTO);
        return productDTO;
    }
}
