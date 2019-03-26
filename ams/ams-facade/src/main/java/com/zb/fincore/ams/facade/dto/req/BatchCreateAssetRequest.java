package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;

import java.util.List;

/**
 * 功能: 批量创建底层资产请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/25 0025 18:08
 * 版本: V1.0
 */
public class BatchCreateAssetRequest<T> extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 9140202121240758835L;

    /**
     * 创建资产请求对象
     */
    List<T> createAssetRequestList;

    public List<T> getCreateAssetRequestList() {
        return createAssetRequestList;
    }

    public void setCreateAssetRequestList(List<T> createAssetRequestList) {
        this.createAssetRequestList = createAssetRequestList;
    }
}
