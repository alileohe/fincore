package com.zb.fincore.common.enums.product;

/**
 * Created by niuzhanjun on 2017/1/11 0011.
 */
public enum PatternCodeTypeEnum {

	/** 01 - 现金管理 */
    CASH_MANAGEMENT("01", "现金管理"),
    /** 02 - 定期类 */
    PERIODIC_REGULAR("02", "定期类"),
    /** 03 - 净值型 */
    NET_ASSET("03", "净值型"),
    /** 04 - 阶梯收益 */
    LADDER("04", "阶梯收益"),
    /** 05 - N日循环计划 */
    N_LOOP_PLAN("05", "N日循环计划");

    private String code;
    private String desc;

    PatternCodeTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过code获取enum对象
     *
     * @param code
     * @return
     */
    public static PatternCodeTypeEnum getEnumItem(String code) {
        for (PatternCodeTypeEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
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
}
