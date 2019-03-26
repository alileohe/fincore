package com.zb.fincore.common.enums;

/**
 * Created by fangyang on 2017/2/21.
 */
public enum CalendarModelEnum {

    YEARLY(1, 365, "一年=365天"), PARTYEAR(2, 360, "一年=360天"), LITTLEYEAR(3, 300, "一年=300天");

    private Integer code;
    private Integer days;
    private String desc;

    CalendarModelEnum(Integer code, Integer days, String desc) {
        this.code = code;
        this.days = days;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Integer getCalendarModelDate(Integer code) {
        for (CalendarModelEnum c : CalendarModelEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.days;
            }
        }
        return null;
    }

    /**
     * 验证日历模式是否在异常
     *
     * @param mode
     * @return
     */
    public static boolean validateCalendarMode(Integer mode) {
        for (CalendarModelEnum calendarModelEnum : CalendarModelEnum.values()) {
            if (calendarModelEnum.getCode().equals(mode)) {
                return true;
            }
        }
        return false;
    }
}
