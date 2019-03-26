package com.zb.fincore.common.enums;

/**
 * Created by wangwanbin on 2017/3/2.
 */
public enum ChannelEnum {
	TXS("11", "TXS", "唐小僧"), YW("21", "YW", "摇旺"), ZD("31", "ZD", "资鼎"), QJS("41", "QJS", "侨金所"), GAN("51", "GAN", "赣金所"), MSD("61", "MSD", "马上贷"), QYLC("12", "QYLC", "企业理财");

    private String channelNo;
    private String code;
    private String desc;

    ChannelEnum(String channelNo, String code, String desc) {
        this.channelNo = channelNo;
        this.code = code;
        this.desc = desc;
    }

    /**
     * 验证渠道名称是否在异常
     *
     * @param channel
     * @return
     */
    public static boolean validateChannel(String channel) {
        for (ChannelEnum channelEnum : ChannelEnum.values()) {
            if (channelEnum.getCode().equals(channel)) {
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

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }
}
