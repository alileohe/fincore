package com.zb.fincore.ams.common.enums;

/**
 * 功能: 模板类型
 */
public enum TemplateTypeEnum {

    TEMPLATE_1(1,"ddrz-01", "模板1"),
    TEMPLATE_2(2,"ddrz-02", "模板2"),
    TEMPLATE_3(3,"ddrz-03", "模板3"),
    TEMPLATE_4(4,"ddrz-04", "模板4");

    private int code;
    private String value;
    private String desc;

    TemplateTypeEnum(int code,String value, String desc) {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 通过code获取enum对象
     *
     * @param code
     * @return
     */
    public static TemplateTypeEnum getEnumItem(int code) {
        for (TemplateTypeEnum item : values()) {
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
    public static TemplateTypeEnum getEnumItem(String value) {
        for (TemplateTypeEnum item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
