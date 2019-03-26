package com.zb.fincore.ams.common.enums;

/**
 * 功能: 交易结构类型
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/25 0025 16:45
 * 版本: V1.0
 */
public enum PoolTypeEnum {

    DIRECTED_DELEGATION(1, "定向委托收益池"),
    P2P_LOAN(2, "p2p实时放款"),
    EXCHANGE_LISTING(3, "交易所挂牌");

    private int code;
    private String desc;

    PoolTypeEnum(int code, String desc) {
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
    public static PoolTypeEnum getEnumItem(int code) {
        for (PoolTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
