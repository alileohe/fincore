package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.facade.model.AssetProductRelationModel;
import com.zb.fincore.ams.facade.model.ProductRelatedAssetInfoModel;

import java.util.List;

/**
 * 功能: 产品占用资产响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 14:13
 * 版本: V1.0
 */
public class CreateAssetProductRelationResponse extends BaseResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -1503970780922614319L;

    /**
     * 产品与资产匹配关系列表
     */
    private List<AssetProductRelationModel> assetProductRelationModels;

    /**
     * 产品关联资产信息
     */
    private ProductRelatedAssetInfoModel infoModel;

    public ProductRelatedAssetInfoModel getInfoModel() {
        return infoModel;
    }

    public void setInfoModel(ProductRelatedAssetInfoModel infoModel) {
        this.infoModel = infoModel;
    }

    public List<AssetProductRelationModel> getAssetProductRelationModels() {
        return assetProductRelationModels;
    }

    public void setAssetProductRelationModels(List<AssetProductRelationModel> assetProductRelationModels) {
        this.assetProductRelationModels = assetProductRelationModels;
    }
}
