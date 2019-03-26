package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import com.zb.fincore.ams.facade.model.ChangeAssetProductModel;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 功能: 创建产品资产关联
 */
public class RelateAssetProductForP2PRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 5096416532505266624L;

    /**
     * 产品编码
     */
    @NotBlank(message = "产品编码不能为空")
    private String productCode;

    /**
     * 资产池编码
     */
    @NotBlank(message = "资产池编码不能为空")
    private String poolCode;

    /**
     * 资产编码
     */
    @NotNull(message = "资产编码不能为空")
    @Size(min = 1, message = "资产编码不能为空")
    private List<String> assetCodes;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode;
    }

    public List<String> getAssetCodes() {
        return assetCodes;
    }

    public void setAssetCodes(List<String> assetCodes) {
        this.assetCodes = assetCodes;
    }
}
