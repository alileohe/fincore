package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.PageQueryRequest;

/**
 * 查询备案记录列表请求
 * Created by MABIAO on 2017/7/4 0004.
 */
public class QueryExchangeRegisterRecordListRequest extends PageQueryRequest {

    private String assetCode;//资产编码

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

}
