package com.zb.fincore.ams.common.enums;

/**
 * ProductApprovalStatusEnum
 * 资产审核状态枚举
 *
 * @author MABIAO
 */

public enum AssetApprovalStatusEnum {

    UN_SUBMIT(1, "未提交审核"),
    WAIT_APPROVAL(2, "待审核"),
    APPROVAL_SUCCESS(3, "审核通过"),
    APPROVAL_FAILURE(4, "审核未通过");

    private int code;
    private String desc;

    AssetApprovalStatusEnum(int code, String desc) {
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
    public static AssetApprovalStatusEnum getEnumItem(int code) {
        for (AssetApprovalStatusEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
