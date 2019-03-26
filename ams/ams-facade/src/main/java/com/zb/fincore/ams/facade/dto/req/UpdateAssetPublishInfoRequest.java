package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 功能: 更新披露信息请求对象
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/7/8 0008 10:15
 * 版本: V1.0
 */
public class UpdateAssetPublishInfoRequest extends BaseRequest {

    @NotBlank(message = "资产编码不能为空")
    private String assetCode;

    @NotBlank(message = "披露信息不能为空")
    private String publishInfo;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getPublishInfo() {
        return publishInfo;
    }

    public void setPublishInfo(String publishInfo) {
        this.publishInfo = publishInfo;
    }
}
