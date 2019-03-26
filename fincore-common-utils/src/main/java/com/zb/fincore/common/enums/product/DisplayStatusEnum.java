package com.zb.fincore.common.enums.product;

/**
 * 功能: 显示状态枚举
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/23 0023 15:57
 * 版本: V1.0
 */
public enum DisplayStatusEnum {

    VISIBLE(1, "显示"),

    INVISIBLE(2, "不显示");

    private int code;
    private String desc;

    DisplayStatusEnum(int code, String desc) {
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
