package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import com.zb.fincore.ams.facade.model.ChangeAssetProductModel;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 功能: 变更产品资产关联金额请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 10:30
 * 版本: V1.0
 */
public class ChangeAssetProductRelationRequest extends BaseRequest {

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
     * 变更明细
     */
    @NotNull(message = "变更列表不能为空")
    @Size(min = 1, message = "变更列表不能为空")
    private List<ChangeAssetProductModel> changeAssetProductModels;

    private Integer releaseType;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<ChangeAssetProductModel> getChangeAssetProductModels() {
        return changeAssetProductModels;
    }

    public void setChangeAssetProductModels(List<ChangeAssetProductModel> changeAssetProductModels) {
        this.changeAssetProductModels = changeAssetProductModels;
    }

    public Integer getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }
}
