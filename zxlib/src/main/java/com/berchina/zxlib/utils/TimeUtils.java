package com.berchina.zxlib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by zx on 2017/3/14 8:23
 * 项目名称：ZxLib
 * 类描述：时间相关工具类
 * 备注
 */
public class TimeUtils {

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }


    public static final SimpleDateFormat DEFAULT_SDF =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());


    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param milliseconds 毫秒时间戳
     * @return 时间字符串
     */
    public static String milliseconds2String(long milliseconds) {
        return milliseconds2String(milliseconds, DEFAULT_SDF);
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param milliseconds 毫秒时间戳
     * @param format       时间格式
     * @return 时间字符串
     */
    public static String milliseconds2String(long milliseconds, SimpleDateFormat format) {
        return format.format(new Date(milliseconds));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time) {
        return string2Milliseconds(time, DEFAULT_SDF);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time, SimpleDateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return Date类型
     */
    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_SDF);
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date类型
     */
    public static Date string2Date(String time, SimpleDateFormat format) {
        return new Date(string2Milliseconds(time, format));
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time Date类型时间
     * @return 时间字符串
     */
    public static String date2String(Date time) {
        return date2String(time, DEFAULT_SDF);
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param time   Date类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date time, SimpleDateFormat format) {
        return format.format(time);
    }

    /**
     * 将Date类型转为时间戳
     *
     * @param time Date类型时间
     * @return 毫秒时间戳
     */
    public static long date2Milliseconds(Date time) {
        return time.getTime();
    }

    /**
     * 将时间戳转为Date类型
     *
     * @param milliseconds 毫秒时间戳
     * @return Date类型时间
     */
    public static Date milliseconds2Date(long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * 毫秒时间戳单位转换（单位：unit）
     *
     * @param milliseconds 毫秒时间戳
     * @param unit         <ul>
     *                     <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *                     <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *                     <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *                     <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *                     <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *                     </ul>
     * @return unit时间戳
     */
    private static long milliseconds2Unit(long milliseconds, ConstUtils.TimeUnit unit) {
        switch (unit) {
            case MSEC:
                return milliseconds / ConstUtils.MSEC;
            case SEC:
                return milliseconds / ConstUtils.SEC;
            case MIN:
                return milliseconds / ConstUtils.MIN;
            case HOUR:
                return milliseconds / ConstUtils.HOUR;
            case DAY:
                return milliseconds / ConstUtils.DAY;
        }
        return -1;
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time0 时间字符串1
     * @param time1 时间字符串2
     * @param unit  <ul>
     *              <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *              <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *              <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *              <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *              <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, ConstUtils.TimeUnit unit) {
        return getIntervalTime(time0, time1, unit, DEFAULT_SDF);
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为format</p>
     *
     * @param time0  时间字符串1
     * @param time1  时间字符串2
     * @param unit   <ul>
     *               <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *               <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *               <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *               <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *               <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, ConstUtils.TimeUnit unit, SimpleDateFormat format) {
        return Math.abs(milliseconds2Unit(string2Milliseconds(time0, format)
                - string2Milliseconds(time1, format), unit));
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2都为Date类型</p>
     *
     * @param time0 Date类型时间1
     * @param time1 Date类型时间2
     * @param unit  <ul>
     *              <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *              <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *              <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *              <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *              <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(Date time0, Date time1, ConstUtils.TimeUnit unit) {
        return Math.abs(milliseconds2Unit(date2Milliseconds(time1)
                - date2Milliseconds(time0), unit));
    }

    /**
     * 获取当前时间
     *
     * @return 毫秒时间戳
     */
    public static long getCurTimeMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 时间字符串
     */
    public static String getCurTimeString() {
        return date2String(new Date());
    }

    /**
     * 获取当前时间
     * <p>格式为用户自定义</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getCurTimeString(SimpleDateFormat format) {
        return date2String(new Date(), format);
    }

    /**
     * 获取当前时间
     * <p>Date类型</p>
     *
     * @return Date类型时间
     */
    public static Date getCurTimeDate() {
        return new Date();
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @param unit <ul>
     *             <li>{@link ConstUtils.TimeUnit#MSEC}:毫秒</li>
     *             <li>{@link ConstUtils.TimeUnit#SEC }:秒</li>
     *             <li>{@link ConstUtils.TimeUnit#MIN }:分</li>
     *             <li>{@link ConstUtils.TimeUnit#HOUR}:小时</li>
     *             <li>{@link ConstUtils.TimeUnit#DAY }:天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, ConstUtils.TimeUnit unit) {
        return getIntervalByNow(time, unit, DEFAULT_SDF);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为format</p>
     *
     * @param time   时间字符串
     * @param unit   <ul>
     *               <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *               <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *               <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *               <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *               <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, ConstUtils.TimeUnit unit, SimpleDateFormat format) {
        return getIntervalTime(getCurTimeString(), time, unit, format);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time为Date类型</p>
     *
     * @param time Date类型时间
     * @param unit <ul>
     *             <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *             <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *             <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *             <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *             <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getIntervalByNow(Date time, ConstUtils.TimeUnit unit) {
        return getIntervalTime(getCurTimeDate(), time, unit);
    }

    /**
     * 判断闰年
     *
     * @param year 年份
     * @return {@code true}: 闰年<br>{@code false}: 平年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}