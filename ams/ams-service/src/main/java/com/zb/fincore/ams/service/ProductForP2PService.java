package com.zb.fincore.ams.service;

import com.zb.p2p.facade.api.resp.CommonResp;
import com.zb.p2p.facade.api.resp.product.ProductDTO;

import java.util.Date;
import java.util.List;

/**
 * P2P借款申请 调用产品服务
 * Created by zhangxin on 2017/9/5.
 */
public interface ProductForP2PService {

    /**
     * 根据资产+起息时间+期限 查询产品
     * @param assetPoolCode
     * @param valueTime
     * @param investPeriod
     * @return
     */
    CommonResp<ProductDTO> queryProductInfoForAssetMatch(String assetPoolCode, Date valueTime, Integer investPeriod);

}
