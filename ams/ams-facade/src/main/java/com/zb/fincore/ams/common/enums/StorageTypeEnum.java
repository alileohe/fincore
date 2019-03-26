package com.zb.fincore.ams.common.enums;

/**
 * 功能: 入库类型
 * 日期: 2017/4/25 0025 16:45
 * 版本: V1.0
 */
public enum StorageTypeEnum {

    STORAGED(1, "已入库"),
    UN_STORAGED(2, "未入库");

    private int code;
    private String desc;

    StorageTypeEnum(int code, String desc) {
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
    public static StorageTypeEnum getEnumItem(int code) {
        for (StorageTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
