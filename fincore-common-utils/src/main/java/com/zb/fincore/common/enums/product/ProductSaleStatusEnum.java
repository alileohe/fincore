package com.zb.fincore.common.enums.product;

/**
 * ProductSaleStatusEnum
 * 产品销售状态枚举
 * @author MABIAO
 */

public enum ProductSaleStatusEnum {

	/** 10 - 待部署 */
    PRODUCT_SALE_STATUS_WAIT_DEPLOYED(10, "待部署"),
    /** 11 - 已部署 */
    PRODUCT_SALE_STATUS_DEPLOYED(11, "已部署"),
    /** 12 - 上线 */
    PRODUCT_SALE_STATUS_ON_LINE(12, "上线"),
    /** 13 - 下线 */
    PRODUCT_SALE_STATUS_OFF_LINE(13, "下线"),
    /** 14 - 归档 */
    PRODUCT_SALE_STATUS_FILED(14, "归档");

    private int code;
    private String desc;

    ProductSaleStatusEnum(int code, String desc) {
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
    public static ProductSaleStatusEnum getEnumItem(int code) {
        for (ProductSaleStatusEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

}
