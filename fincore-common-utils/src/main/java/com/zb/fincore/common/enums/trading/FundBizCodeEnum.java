package com.zb.fincore.common.enums.trading;

/**
 * Created by niuzhanjun on 2017/1/11 0011.
 */
public enum FundBizCodeEnum {
    INVEST_REQUEST("022", "申购申请"),
    INVEST_CONFIRM("122", "申购确认"),
    REDEEM_REQUEST("024", "赎回"),
    REDEEM_RESERVATION("025", "赎回预约"),
    REDEEM_CONFIRM("124", "赎回确认");

    private String code;
    private String desc;

    FundBizCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
