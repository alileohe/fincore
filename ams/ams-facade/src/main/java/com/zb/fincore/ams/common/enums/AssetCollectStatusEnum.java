package com.zb.fincore.ams.common.enums;

/**
 * ProductCollectStatusEnum
 * 资产募集状态枚举
 *
 * @author MABIAO
 */

public enum AssetCollectStatusEnum {

    WAIT_COLLECT(10, "待审核"),
    WAIT_ESTABLISH(12, "待成立"),
    ESTABLISHED(13, "已成立"),
    FAILURE_SALE(18, "流标"),
    CAN_NOT_USE(99, "不可用");

    private int code;
    private String desc;

    AssetCollectStatusEnum(int code, String desc) {
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
    public static AssetCollectStatusEnum getEnumItem(int code) {
        for (AssetCollectStatusEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
