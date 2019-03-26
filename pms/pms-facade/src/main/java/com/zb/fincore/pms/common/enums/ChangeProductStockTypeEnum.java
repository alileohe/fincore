package com.zb.fincore.pms.common.enums;

/**
 * 功能: 产品库存变更类型
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/11 0011 09:15
 * 版本: V1.0
 */
public enum ChangeProductStockTypeEnum {

    FREEZE(1, "冻结"),

    OCCUPY(2, "占用"),

    RELEASE(3, "释放"),

    REDEEM(4, "赎回"),

    CANCEL(5, "取消");

    private int code;
    private String desc;

    ChangeProductStockTypeEnum(int code, String desc) {
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
    public static ChangeProductStockTypeEnum getItem(int code) {
        for (ChangeProductStockTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
