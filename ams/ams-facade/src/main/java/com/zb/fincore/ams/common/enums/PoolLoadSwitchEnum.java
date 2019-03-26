package com.zb.fincore.ams.common.enums;

/**
 * 功能: 装载开关枚举
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/1/10 0010 11:40
 * 版本: V1.0
 */
public enum PoolLoadSwitchEnum {

    LOAD_SAVE(1, "可出入"),

    LOAD(2, "可出"),

    SAVE(3, "可入"),

    PACK(4, "包装"),

    NO_LOAD_SAVE(5, "停止出入");

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
     * @return AmsLoadSwitchEnum
     */
    public static PoolLoadSwitchEnum getEnum(Integer code) {
        for (PoolLoadSwitchEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    PoolLoadSwitchEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
