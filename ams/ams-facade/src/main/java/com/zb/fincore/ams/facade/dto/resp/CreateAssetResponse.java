package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;

/**
 * 功能: 创建底层资产响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 11:18
 * 版本: V1.0
 */
public class CreateAssetResponse extends BaseResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -3523272615694462632L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 资产编码
     */
    private String assetCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }
}
