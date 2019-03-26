package com.zb.fincore.ams.common.enums;

/**
 * 功能: 借款款还款类型
 * 日期: 2017/4/25 0025 16:45
 * 版本: V1.0
 */
public enum RepayStatusEnum {

    WAITING_REPAY(0, "待还款"),
    PART_REPAID(1, "部分还款"),
    REPAID(2, "全部还款");

    private int code;
    private String desc;

    RepayStatusEnum(int code, String desc) {
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
    public static RepayStatusEnum getEnumItem(int code) {
        for (RepayStatusEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
