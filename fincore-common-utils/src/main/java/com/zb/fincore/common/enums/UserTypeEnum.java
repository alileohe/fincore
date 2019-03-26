package com.zb.fincore.common.enums;

/**
 * Created by wangwanbin on 2017/3/2.
 */
public enum UserTypeEnum {
    PERSONAL("PERSONAL", "个人"), ENTERPRISE("ENTERPRISE", "机构");

    private String code;
    private String desc;

    UserTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 验证渠道名称是否在异常
     *
     * @param userType
     * @return
     */
    public static boolean validateUserType(String userType) {
        for (UserTypeEnum channelEnum : UserTypeEnum.values()) {
            if (channelEnum.getCode().equals(userType)) {
                return true;
            }
        }
        return false;
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
