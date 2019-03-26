package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.facade.model.ProductRelatedAssetInfoModel;

/**
 * 功能: 查询产品关联资产信息响应
 * Created by MABIAO on 2017/6/12 0012.
 */
public class QueryProductRelatedAssetInfoResponse extends BaseResponse {

    private static final long serialVersionUID = 6708792716796932560L;

    private ProductRelatedAssetInfoModel infoModel;

    public ProductRelatedAssetInfoModel getInfoModel() {
        return infoModel;
    }

    public void setInfoModel(ProductRelatedAssetInfoModel infoModel) {
        this.infoModel = infoModel;
    }
}
