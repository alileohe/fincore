package com.zb.fincore.pms.facade.product;

import com.zb.fincore.pms.common.dto.BaseResponse;
import com.zb.fincore.pms.common.dto.PageQueryResponse;
import com.zb.fincore.pms.facade.product.dto.req.QueryProductInfoForTradeRequest;
import com.zb.fincore.pms.facade.product.dto.req.QueryProductListRequest;
import com.zb.fincore.pms.facade.product.dto.req.UpdateProductCollectStatusRequest;
import com.zb.fincore.pms.facade.product.dto.resp.QueryProductInfoResponse;
import com.zb.fincore.pms.facade.product.model.ProductModel;

/**
 * 功能: 产品数据库服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/6 0006 16:55
 * 版本: V1.0
 */
public interface ProductServiceForP2PFacade extends BaseProductServiceFacade{
	/**
     * 供交易系统调用  产品列表查询
     * @param req
     * @return
     */
    PageQueryResponse<ProductModel> queryProductListForTrade(QueryProductListRequest req);
    
    /**
     * 供交易系统调用 产品详情查询
     * @param req
     * @return
     */
    QueryProductInfoResponse queryProductInfoForTrade(QueryProductInfoForTradeRequest req);
    
    /**
     * 供交易系统调用 更新产品募集状态
     */
    BaseResponse updateProductCollectStatus(UpdateProductCollectStatusRequest req);
}
