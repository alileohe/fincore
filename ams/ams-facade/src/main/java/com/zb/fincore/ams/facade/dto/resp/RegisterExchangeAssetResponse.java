package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.facade.model.ErrorModel;

import java.util.List;

/**
 * 功能: 批量创建底层资产响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/26 0026 10:19
 * 版本: V1.0
 */
public class RegisterExchangeAssetResponse extends BaseResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1391220458205045916L;

    /**
     * 备案资产编码
     */
    private String assetCode;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }
}
