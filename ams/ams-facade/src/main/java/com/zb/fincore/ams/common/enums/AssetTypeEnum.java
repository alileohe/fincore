package com.zb.fincore.ams.common.enums;

/**
 * 功能: 资产类型(融资类型)
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/25 0025 16:45
 * 版本: V1.0
 */
public enum AssetTypeEnum {

    PERSONAL_LOAN(1, "个人贷款"),
    ENTERPRISE_LOAN(2, "企业贷款"),
    FINANCE_LEASE(3, "融资租赁"),
    ACCOUNTS_RECEIVABLE(4, "应收账款"),
    PERSONAL_LOAN_EARNINGS_RIGHT(5, "个人贷款收益权"),//原来 1
    ENTERPRISE_LOAN_EARNINGS_RIGHT(6, "企业贷款收益权"),
    FINANCE_LEASE_EARNINGS_RIGHT(7, "融资租赁收益权"),
    ACCOUNTS_RECEIVABLE_EARNINGS_RIGHT(8, "应收账款收益权");//原来 2、3


//    SMALL_LEND(1, "小贷收益权"),
//    ACCOUNTS_RECEIVABLE(2, "应收账款收益权"),
//    DIRECTIONAL_FINANCING(3, "定向融资工具");

    private int code;
    private String desc;

    AssetTypeEnum(int code, String desc) {
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
    public static AssetTypeEnum getEnumItem(int code) {
        for (AssetTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
