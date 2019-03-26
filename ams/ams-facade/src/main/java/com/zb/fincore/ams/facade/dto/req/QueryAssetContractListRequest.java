package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 功能: 批量创建底层资产请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/25 0025 18:08
 * 版本: V1.0
 */
public class QueryAssetContractListRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -5486469235289279150L;

    /**
     * 资产编码
     */
    @NotBlank(message = "资产编码不能为空")
    private String assetCode;

    /**
     * 模板编码
     */
    private String templateCode;

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
