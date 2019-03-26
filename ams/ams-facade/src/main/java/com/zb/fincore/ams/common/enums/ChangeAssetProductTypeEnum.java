package com.zb.fincore.ams.common.enums;

/**
 * 功能: 产品资产关系变更类型枚举
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 10:38
 * 版本: V1.0
 */
public enum ChangeAssetProductTypeEnum {

    OCCUPY(1, "占用"),

    RELEASE(2, "释放");

    private int code;
    private String desc;

    ChangeAssetProductTypeEnum(int code, String desc) {
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
    public static ChangeAssetProductTypeEnum getItem(int code) {
        for (ChangeAssetProductTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
