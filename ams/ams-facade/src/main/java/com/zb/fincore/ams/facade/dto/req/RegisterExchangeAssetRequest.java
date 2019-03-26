package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 功能：资产备案请求参数
 * Created by MABIAO on 2017/6/15 0015.
 */
public class RegisterExchangeAssetRequest extends BaseRequest {

    @NotBlank(message = "资产编码不能为空")
    private String parentAssetCode;

    private String assetCode;

    @NotBlank(message = "交易所编码不能为空")
    private String tradeInstCode;

    @NotBlank(message = "摘牌方编码不能为空")
    private String delistedInstCode;

    @NotBlank(message = "挂牌方编码不能为空")
    private String listedInstCode;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getTradeInstCode() {
        return tradeInstCode;
    }

    public void setTradeInstCode(String tradeInstCode) {
        this.tradeInstCode = tradeInstCode;
    }

    public String getDelistedInstCode() {
        return delistedInstCode;
    }

    public void setDelistedInstCode(String delistedInstCode) {
        this.delistedInstCode = delistedInstCode;
    }

    public String getListedInstCode() {
        return listedInstCode;
    }

    public void setListedInstCode(String listedInstCode) {
        this.listedInstCode = listedInstCode;
    }

    public String getParentAssetCode() {
        return parentAssetCode;
    }

    public void setParentAssetCode(String parentAssetCode) {
        this.parentAssetCode = parentAssetCode;
    }
}
