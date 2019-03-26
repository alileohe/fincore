package com.zb.fincore.ams.common.enums;

/**
 * 资产释放类型
 * Created by MABIAO on 2017/7/3 0003.
 */
public enum ReleaseTypeEnum {

    SALE(1, "产品已售出"),
    UN_SALE(2, "产品未售出");

    private int code;
    private String desc;

    ReleaseTypeEnum(int code, String desc) {
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
    public static ReleaseTypeEnum getEnumItem(int code) {
        for (ReleaseTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
