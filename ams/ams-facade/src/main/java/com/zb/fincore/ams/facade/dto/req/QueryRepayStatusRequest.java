package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 功能: 查询借款人还款状态请求
 * Created by MABIAO on 2017/9/12 0012.
 */
public class QueryRepayStatusRequest extends BaseRequest {

	/**
     * SerialVersionUID
     */
	private static final long serialVersionUID = 6235894872861428576L;

    /**
     * 产品编码
     */
    @NotBlank(message = "产品编码不能为空")
    private String productCode;


    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
