package com.zb.fincore.ams.common.enums;

/**
 * 功能: 资产注册类型
 */
public enum RegisterTypeEnum {

    NORMAL(1,"NORMAL", "普通录入"),
    RECORD(2,"RECORD", "授信录入"),
    REGISTER(3,"REGISTER", "交易所录入"),
    MSD(4,"MSD", "马上贷");

    private int code;
    private String value;
    private String desc;

    RegisterTypeEnum(int code,String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
    public static RegisterTypeEnum getEnumItem(int code) {
        for (RegisterTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    /**
     * 通过value获取enum对象
     *
     * @param value
     * @return
     */
    public static RegisterTypeEnum getEnumItem(String value) {
        for (RegisterTypeEnum item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }
}
