package com.zb.fincore.ams.common.enums;

/**
 * 功能: 资产备案类型
 * 日期: 2017/4/25 0025 16:45
 * 版本: V1.0
 */
public enum AssetRegisterExchangeEnum {

    //备案状态:1:未备案,2:备案中,3:生成合同,4:备案完成,5:备案失败
    UN_REGISTER(1,"UN_REGISTER", "未备案"),
    REGISTERING(2,"REGISTERING", "备案中"),
    GENERATE_CONTRACT(3,"GENERATE_CONTRACT", "生成合同"),
    REGISTERED(4,"REGISTERED", "完成"),
    REGISTER_FAILED(5,"REGISTER_FAILED", "备案失败");

    private int code;
    private String value;
    private String desc;

    AssetRegisterExchangeEnum(int code,String value, String desc) {
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
     * @param code
     * @return
     */
    public static AssetRegisterExchangeEnum getEnumItem(int code) {
        for (AssetRegisterExchangeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    /**
     * 通过value获取enum对象
     * @param value
     * @return
     */
    public static AssetRegisterExchangeEnum getEnumItem(String value) {
        for (AssetRegisterExchangeEnum item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
