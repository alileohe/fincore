package com.zb.fincore.common.enums.product;

/**
 * 功能: 产品线状态枚举
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/30 0030 19:12
 * 版本: V1.0
 */
public enum ProductLineStatusEnum {

    NORMAL(1, "正常"),
    CANCELED(2, "已注销");

    private int code;
    private String desc;

    ProductLineStatusEnum(int code, String desc) {
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
}
