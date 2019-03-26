package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.facade.model.AssetPoolRelationModel;

import java.util.List;

/**
 * 功能: 创建资产资产池关联关系响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/21 0021 17:03
 * 版本: V1.0
 */
public class CreateAssetPoolRelationResponse extends BaseResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1802005189501841010L;

    /**
     * 资产与资产池关联关系列表
     */
    List<AssetPoolRelationModel> assetPoolRelationModelList;

    public List<AssetPoolRelationModel> getAssetPoolRelationModelList() {
        return assetPoolRelationModelList;
    }

    public void setAssetPoolRelationModelList(List<AssetPoolRelationModel> assetPoolRelationModelList) {
        this.assetPoolRelationModelList = assetPoolRelationModelList;
    }
}
