package com.zb.fincore.ams.common.enums;

/**
 * 功能: 合同类型
 * 日期: 2017/4/25 0025 16:45
 * 版本: V1.0
 */
public enum ContractTypeEnum {

    ORDER_CONTRACT(1, "订单合同"),
    REGISTER_CONTRACT(2, "备案合同");

    private int code;
    private String desc;

    ContractTypeEnum(int code, String desc) {
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
    public static ContractTypeEnum getEnumItem(int code) {
        for (ContractTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
