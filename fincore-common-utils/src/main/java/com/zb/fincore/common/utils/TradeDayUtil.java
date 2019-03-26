package com.zb.fincore.common.utils;

import com.alibaba.fastjson.JSON;
import com.zb.fincore.common.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.*;


/**
 * Created by wangwanbin on 2017/11/1.
 * 判断当前日期是否为交易日、工作日
 */
public class TradeDayUtil {
    private static final Map<String, TradeDay> map = new HashMap<>();

    private static Logger logger = LoggerFactory.getLogger(TradeDayUtil.class);

    //加载文件
    static {
        try {
            String jarPath = TradeDayUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
            URL url = new URL("jar:file:" + jarPath + "!/date.json");//通过读取jar文件，获取url
            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s;
            StringBuilder sb = new StringBuilder();
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            //String转换为Json
            List<TradeDay> tradeDayList = JSON.parseArray(sb.toString(), TradeDay.class);
            for (TradeDay tradeDay : tradeDayList) {
                map.put(tradeDay.getDate(), tradeDay);
            }
        } catch (Exception e) {
            logger.error("加载日期异常", e);
        }
    }

    /**
     * 判断是否为工作日
     *
     * @param date
     * @return
     */
    public static boolean isWorkDay(String date) {
        if (map.get(date) == null) {
            throw new CommonException("日期[" + date + "]未在配置中登记");
        }
        return map.get(date).getWorkDay().equals("1") ? true : false;
    }

    /**
     * 判断是否为交易日
     *
     * @param date
     * @return
     */
    public static boolean isTradeDay(String date) {
        if (map.get(date) == null) {
            throw new CommonException("日期[" + date + "]未在配置中登记");
        }
        return map.get(date).getTradeDay().equals("1") ? true : false;
    }

    /**
     * 获取下一个自然日
     *
     * @param dateStr
     * @return
     */
    public static String getNextDay(String dateStr) {
        Date date = DateUtils.addDay(DateUtils.parse(dateStr, "yyyy-MM-dd"), 1);
        return DateUtils.format(date, DateUtils.DEFAULT_DATA_FORMAT);
    }

    /**
     * 获取下一个工作日
     *
     * @param dateStr
     * @return
     */
    public static String getNextWorkDay(String dateStr) {
        String nextDayStr = getNextDay(dateStr);
        try {
            if (isWorkDay(nextDayStr)) {
                return nextDayStr;
            } else {
                return getNextWorkDay(nextDayStr);
            }
        } catch (CommonException e) {
            return nextDayStr;
        }
    }

    /**
     * 获取下一个交易日
     *
     * @param dateStr
     * @return
     */
    public static String getNextTradeDay(String dateStr) {
        String nextDayStr = getNextDay(dateStr);
        try {
            if (isTradeDay(nextDayStr)) {
                return nextDayStr;
            } else {
                return getNextTradeDay(nextDayStr);
            }
        } catch (CommonException e) {
            return nextDayStr;
        }

    }

}
