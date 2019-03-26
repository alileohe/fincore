package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.PageQueryRequest;

/**
 * 功能: 查询资产回款计划列表请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 09:51
 * 版本: V1.0
 */
public class QueryAssetRepayPlanListRequest extends PageQueryRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 91500528969015669L;

    /**
     * 资产主键
     */
    private Long assetId;

    /**
     * 资产编码
     */
    private String assetCode;

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }
}
