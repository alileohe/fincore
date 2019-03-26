package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 更新备案状态请求
 * Created by MABIAO on 2017/6/27 0027.
 */
public class UpdateRegisterExchangeInfoRequest extends BaseRequest {

    @NotBlank(message = "资产编码不能为空")
    private String assetCode;

    @NotNull(message = "备案状态不能为空")
    private Integer registerStatus;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public Integer getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }
}
