package com.zb.fincore.common.enums;

/**
 * Created by mengkai on 2017/4/9
 */
public enum BizCodeEnum {
    INVEST_REQUEST("022", "申购申请"), INVEST_CONFIRM("122", "申购确认"),
    REDEEM_REQUEST("025", "赎回预约"),ADVANCE_REDEEM_CONFIRM("125", "预约兑付确认"),
    AUTO_REDEEM_CONFIRM("124", "到期兑付确认"),
    DAILY_INCOME("201","每日收益"),REPAYMENT_PLAN("301","还款计划"),
    REDEEM_INFORM("401","兑付通知");
    private String code;
    private String desc;

    BizCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static boolean validateBizCode(String code) {
        BizCodeEnum[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            BizCodeEnum bizCodeEnum = arr$[i$];
            if(bizCodeEnum.getCode().equals(code)) {
                return true;
            }
        }

        return false;
    }

}
