package com.zb.fincore.ams.common.enums;

/**
 * 功能: 还款方式枚举
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 10:16
 * 版本: V1.0
 */
public enum AssetRepaymentTypeEnum {

    ONE_TIME_DEBT(1, "本息一次性偿还"),
    MONTHLY_INTEREST_PAYMENT(2, "每月付息，到期还本"),
    EQUAL_PRINCIPAL_INTEREST(3, "等额本息"),
    EQUAL_PRINCIPAL(4, "等额本金"),
    AUTO_PAY_INTEREST_AUTO_INVEST_PRINCIPAL(5, "利息自动拨付，本金复投");

    private int code;
    private String desc;

    AssetRepaymentTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 通过code获取enum对象
     *
     * @param code
     * @return
     */
    public static AssetRepaymentTypeEnum getEnumItem(int code) {
        for (AssetRepaymentTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
