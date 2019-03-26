package com.zb.fincore.common.service.dto;

import java.util.Date;

/**
 * Created by niuzhanjun on 2017/1/11 0011.
 */
public class TradeDayDto {
    private String calendarModeCode;
    private Date theDay;
    private int isWorkDay;      //是否工作日  1:是  0:不是
    private int isTradeDay;     //是否交易日  1:是  0:不是
    private int isHkTradeDay;   //是否香港交易日  1:是  0:不是

    public String getCalendarModeCode() {
        return calendarModeCode;
    }

    public void setCalendarModeCode(String calendarModeCode) {
        this.calendarModeCode = calendarModeCode;
    }

    public Date getTheDay() {
        return theDay;
    }

    public void setTheDay(Date theDay) {
        this.theDay = theDay;
    }

    public int getIsWorkDay() {
        return isWorkDay;
    }

    public void setIsWorkDay(int isWorkDay) {
        this.isWorkDay = isWorkDay;
    }

    public int getIsTradeDay() {
        return isTradeDay;
    }

    public void setIsTradeDay(int isTradeDay) {
        this.isTradeDay = isTradeDay;
    }

    public int getIsHkTradeDay() {
        return isHkTradeDay;
    }

    public void setIsHkTradeDay(int isHkTradeDay) {
        this.isHkTradeDay = isHkTradeDay;
    }
}
