package com.zb.fincore.common.enums.product;

/**
 * ProductRiskLevelEnum
 * 产品风险等级枚举
 * 2016年11月22日
 *
 * @author zhangxin
 */
public enum ProductRiskLevelEnum {

    PRODUCT_RISK_LEVEL_1("1", "R1"),
    PRODUCT_RISK_LEVEL_2("2", "R2"),
    PRODUCT_RISK_LEVEL_3("3", "R3"),
    PRODUCT_RISK_LEVEL_4("4", "R4"),
    PRODUCT_RISK_LEVEL_5("5", "R5");

    private String code;
    private String desc;

    ProductRiskLevelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
    public static ProductRiskLevelEnum getEnumItem(String code) {
        for (ProductRiskLevelEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
