package com.zb.fincore.common.enums;

/**
 * 功能: 是否枚举
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/23 0023 15:59
 * 版本: V1.0
 */
public enum YesNoEnum {

    YES(1, "是"),

    NO(0, "否");

    private int code;
    private String desc;

    YesNoEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
