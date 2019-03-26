package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能: 产品占用资产请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 14:06
 * 版本: V1.0
 */
public class CreateAssetProductRelationRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 3723500513641590234L;

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
     * 产品规模
     */
    @NotNull(message = "产品规模不能为空")
    @DecimalMin(value = "0.00", message = "产品规模必须大于零")
    private BigDecimal productAmount;

    /**
     * 产品起息日
     */
    @NotNull(message = "产品起息时间不能为空")
    private Date productValueStartTime;

    /**
     * 产品到期时间
     */
    @NotNull(message = "产品到期时间不能为空")
    private Date productExpireTime;

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

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public Date getProductValueStartTime() {
        return productValueStartTime;
    }

    public void setProductValueStartTime(Date productValueStartTime) {
        this.productValueStartTime = productValueStartTime;
    }

    public Date getProductExpireTime() {
        return productExpireTime;
    }

    public void setProductExpireTime(Date productExpireTime) {
        this.productExpireTime = productExpireTime;
    }
}
