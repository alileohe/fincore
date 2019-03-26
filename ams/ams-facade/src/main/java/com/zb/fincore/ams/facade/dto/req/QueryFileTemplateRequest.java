package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询模板详情请求
 */
public class QueryFileTemplateRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1051950876353590392L;

    @NotBlank(message = "模板编码不能为空")
    private String templateCode;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
