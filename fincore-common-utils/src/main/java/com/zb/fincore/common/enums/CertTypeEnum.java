package com.zb.fincore.common.enums;

/**
 * Created by wangwanbin on 2017/4/19.
 * 证件类型枚举
 */
public enum CertTypeEnum {
    IDENTITY_CARD(1,"IDENTITY_CARD", "居民身份证"),
    SOCIAL_CREDIT_CODE(2,"SOCIAL_CREDIT_CODE", "社会信用代码"),
    BUSINESS_LICENSE(3,"BUSINESS_LICENSE", "营业执照");

    private int intValue;
    private String code;
    private String desc;

    CertTypeEnum(int intValue,String code, String desc) {
        this.intValue = intValue;
        this.code = code;
        this.desc = desc;
    }

    /**
     * 验证证件类型是否存在
     *
     * @param certType
     * @return
     */
    public static boolean validateCertType(String certType) {
        for (CertTypeEnum channelEnum : CertTypeEnum.values()) {
            if (channelEnum.getCode().equals(certType)) {
                return true;
            }
        }
        return false;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
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
