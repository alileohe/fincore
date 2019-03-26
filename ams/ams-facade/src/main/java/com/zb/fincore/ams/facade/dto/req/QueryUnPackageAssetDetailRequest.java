package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;

/**
 * 授信资产生成合同详情请求参数
 * Created by MABIAO on 2017/6/29 0029.
 */
public class QueryUnPackageAssetDetailRequest extends BaseRequest {

    private String assetCode;//资产编码

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

}
