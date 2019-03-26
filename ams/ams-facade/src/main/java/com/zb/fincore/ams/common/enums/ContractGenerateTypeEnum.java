package com.zb.fincore.ams.common.enums;

/**
 * 功能: 合同生成类型
 * 日期: 2017/4/25 0025 16:45
 * 版本: V1.0
 */
public enum ContractGenerateTypeEnum {

    NO(1, "未生成"),
    YES(2, "已生成");

    private int code;
    private String desc;

    ContractGenerateTypeEnum(int code, String desc) {
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
    public static ContractGenerateTypeEnum getEnumItem(int code) {
        for (ContractGenerateTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
