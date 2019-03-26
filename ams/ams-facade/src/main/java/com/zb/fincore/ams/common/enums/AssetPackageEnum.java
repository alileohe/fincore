package com.zb.fincore.ams.common.enums;

/**
 * 功能: 资产包装类型
 * 日期: 2017/4/25 0025 16:45
 * 版本: V1.0
 */
public enum AssetPackageEnum {

    PACKAGED(1, "已包装"),
    NO_PACKAGE(2, "未包装");

    private int code;
    private String desc;

    AssetPackageEnum(int code, String desc) {
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
    public static AssetPackageEnum getEnumItem(int code) {
        for (AssetPackageEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
