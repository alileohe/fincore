package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

/**
 * 创建文件模板参数请求
 */
public class CreateFileTemplateParamRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 7179945016299648993L;

    @NotBlank(message = "模板编码不能为空")
    private String templateCode;

    @NotBlank(message = "资产编码不能为空")
    private String assetCode;

    private String templateContent;

    @NotBlank(message = "模板参数不能为空")
    private String templateParam;

//    @NotBlank(message = "创建人不能为空")
    private String createBy;

    private String registerName;

    private BigDecimal registerRate;

    private BigDecimal registerAmount;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public BigDecimal getRegisterRate() {
        return registerRate;
    }

    public void setRegisterRate(BigDecimal registerRate) {
        this.registerRate = registerRate;
    }

    public BigDecimal getRegisterAmount() {
        return registerAmount;
    }

    public void setRegisterAmount(BigDecimal registerAmount) {
        this.registerAmount = registerAmount;
    }
}
