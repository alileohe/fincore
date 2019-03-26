package com.zb.fincore.ams.common.enums;

/**
 * 功能: 融资方状态
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/25 0025 16:45
 * 版本: V1.0
 */
public enum FinanceStatusEnum {

    NORMAL(1, "正常"),
    FROZEN(2, "冻结");

    private int code;
    private String desc;

    FinanceStatusEnum(int code, String desc) {
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
    public static FinanceStatusEnum getEnumItem(int code) {
        for (FinanceStatusEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
