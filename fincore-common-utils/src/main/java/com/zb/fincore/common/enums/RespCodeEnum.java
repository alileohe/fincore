package com.zb.fincore.common.enums;

import org.apache.commons.lang3.StringUtils;


/**
 * ClassName: RespCodeEnum <br/>
 * Function: 响应类. <br/>
 * Date: 2016年12月13日 下午2:14:10 <br/>
 *
 * @author mengkai@zillionfortune.com
 * @since JDK 1.7
 */
public enum RespCodeEnum {
    /**
     * 000000: 处理成功
     **/
    SUCCESS("000000", "处理成功"),
    /**
     * 999999: 系统异常
     **/
    FAIL("999999", "系统异常");

    private String code;
    private String desc;

    RespCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code() {
        return code;
    }

    public String desc() {
        return desc;
    }

    public static String getDesc(String code) {
        for (RespCodeEnum item : RespCodeEnum.values()) {
            if (StringUtils.equals(item.code, code)) {
                return item.desc();
            }
        }

        return null;
    }

}
