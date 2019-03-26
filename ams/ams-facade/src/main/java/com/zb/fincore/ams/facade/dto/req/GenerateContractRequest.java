package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;

/**
 * 生成合同请求
 * Created by MABIAO on 2017/7/4 0004.
 */
public class GenerateContractRequest  extends BaseRequest {

    private String assetCode;//资产编码

    private String templateCode;//模板编码

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
