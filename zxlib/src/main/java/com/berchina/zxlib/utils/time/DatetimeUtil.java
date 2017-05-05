package com.berchina.zxlib.utils.time;

import com.berchina.zxlib.utils.empty.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


public class DatetimeUtil {

    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String DATE_PATTERN1 = "yyyy年MM月dd日";


    public static String ZH_CN_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String ZH_CN_DATETIME_HOURS = "yyyy-MM-dd HH:mm";

    public static String ZH_CN_DATETIME_HOURS1 = "MM-dd HH:mm";
    public static String ZH_CN_DATETIME_HOURS2 = "MM月dd日";

    public static String NOMARK_DATETIME_PATTERN = "yyyyMMddHHmmss";

    public final static String TIME_PATTERN = "HH:mm:ss";
    public final static String TIME_PATTERN1 = "HH:mm";
    public final static String TIME_PATTERN2 = "HH";

    public final static String DATE_TIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;
    public final static String DateString1 = "刚刚";
    public final static String DateString2 = "分钟前";
    public final static String DateString3 = "1小时前";
    public final static String DateString4 = "今天 ";
    public final static String DateString5 = "昨天 ";

    /**
     * Date to Strin
     */
    public static String formatDate(Date date, String formatStyle) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
            String formatDate = sdf.format(date);
            return formatDate;
        } else {
            return "";
        }

    }

    public static String dateFormat(String timestamp) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(timestamp);
            return outputFormat.format(date);
        } catch (ParseException e) {
            return "unknown";
        }
    }

    /**
     * Date to Date
     */
    public static Date formatDate(String formatStyle, Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
            String formatDate = sdf.format(date);
            try {
                return sdf.parse(formatDate);
            } catch (ParseException e) {
                e.printStackTrace();
                return new Date();
            }
        } else {
            return new Date();
        }
    }

    /**
     * 将String格式的日起转为date类型
     *
     * @param pattern
     * @param locale
     * @param zone
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static final Date convertStringToDate(String pattern, Locale locale, TimeZone zone,
                                                 String strDate) throws ParseException {
        if (locale == null) locale = Locale.getDefault();
        if (zone == null) zone = TimeZone.getDefault();
        SimpleDateFormat df = new SimpleDateFormat(pattern, locale);
        df.setTimeZone(zone);
        try {
            return df.parse(strDate);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
    }

    /**
     * 将String格式的日起转为date类型
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static final Date convertStringToDate(String strDate) {
        Locale locale = Locale.CHINESE;
        try {
            return convertStringToDate(DATE_PATTERN, locale, null, strDate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将String格式的日起转为date类型
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static final Date convertStringToDate(String strDate, String sytle) {
        Locale locale = Locale.CHINESE;
        try {
            return convertStringToDate(sytle, locale, null, strDate);
        } catch (Exception e) {
            return null;
        }
    }

    public static final String convertDateToString(String pattern, Locale locale, TimeZone zone,
                                                   Date aDate) {
        if (locale == null) locale = Locale.getDefault();
        if (zone == null) zone = TimeZone.getDefault();
        SimpleDateFormat df = new SimpleDateFormat(pattern, locale);
        df.setTimeZone(zone);
        try {
            return df.format(aDate);
        } catch (Exception e) {
            return "";
        }
    }

    public static final String convertDateToString(String pattern, Date aDate) {
        Locale locale = Locale.CHINESE;
        return convertDateToString(pattern, locale, null, aDate);
    }

    /**
     * 提供yyyy-MM-dd类型的日期字符串转化
     */
    public static final Date getBeginDate(String beginDate) {
        Locale locale = Locale.CHINESE;
        try {
            return convertStringToDate("yyyy-MM-dd HH:mm:ss", locale, null, beginDate + " 00:00:00");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 提供yyyy-MM-dd类型的日期字符串转化 专门提供Web页面结束日期转化 如输入2006-07-27，则转化为2006-07-28 00:00:00
     */
    public static final Date getEndDate(String endDate) {
        Locale locale = Locale.CHINESE;
        try {
            Date date = convertStringToDate("yyyy-MM-dd HH:mm:ss", locale, null, endDate + " 00:00:00");
            return new Date(date.getTime() + 24 * 3600 * 1000);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 提供yyyy-MM-dd类型的日期字符串转化 专门提供Web页面前一日日期转化 如输入2006-07-27，则转化为2006-07-26 00:00:00
     */
    public static final Date getPreDate(String endDate) {
        Locale locale = Locale.CHINESE;
        try {
            Date date = convertStringToDate("yyyy-MM-dd HH:mm:ss", locale, null, endDate + " 00:00:00");
            return new Date(date.getTime() - 24 * 3600 * 1000);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 提供yyyy-MM-dd类型的日期字符串转化 专门提供Web页面前N日日期转化 如输入2006-07-27，则转化为2006-07-26 00:00:00
     */
    public static final Date getPreDate(String endDate, int day) {
        Locale locale = Locale.CHINESE;
        try {
            Date date = convertStringToDate("yyyy-MM-dd HH:mm:ss", locale, null, endDate + " 00:00:00");
            return new Date(date.getTime() - day * 24 * 3600 * 1000);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * yyyy年mm月dd日 星期w
     */
    public static String getFullDateStr() {
        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINESE);
        return format.format(new Date());
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1
     * @param date2
     * @return
     */

    public static int diffdates(Date date1, Date date2) {
        int result = 0;
        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();

        gc1.setTime(date1);
        gc2.setTime(date2);
        result = getDays(gc1, gc2);

        return result;
    }

    public static int getDays(GregorianCalendar g1, GregorianCalendar g2) {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;

        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        } else {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }

        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.DATE, 1);
            elapsed++;
        }
        return elapsed;
    }

    /**
     * 功能：将表示时间的字符串以给定的样式转化为java.util.Date类型 且多于或少于给定的时间多少小时（formatStyle和formatStr样式相同）
     *
     * @return java.util.Date
     * @param:formatStyle 要格式化的样式, 如:yyyy-MM-dd HH:mm:ss
     * @param:formatStr 待转化的字符串(表示的是时间)
     * @param:hour 多于或少于的小时数(可正可负) 单位为小时
     */
    public static Date formartDate(String formatStyle, String formatStr, int hour) {
        SimpleDateFormat format = new SimpleDateFormat(formatStyle, Locale.CHINA);
        try {
            Date date = new Date();
            date.setTime(format.parse(formatStr).getTime() + hour * 60 * 60 * 1000);
            return date;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 获取现在时刻
     */
    public static Date getNow() {
        return new Date(new Date().getTime());
    }

    /**
     * 获取现在时刻
     */
    public static Date getNows() {
        return formatDate(DATE_PATTERN, getNow());
    }

    /**
     * 获取制定小时后
     */
    public static Date getPreHours(Date time, int hour) {
        return new Date(time.getTime() + hour * 3600 * 1000L);
    }

    /**
     * 获取前一小时
     */
    public static Date getPreHour() {
        return new Date(new Date().getTime() - 3600 * 1000L);
    }

    /**
     * 获取下一小时
     */
    public static Date getNextHour() {
        return new Date(new Date().getTime() + 3600 * 1000L);
    }

    /**
     * 获取昨天
     */
    public static Date getYesterday() {
        return new Date(new Date().getTime() - 24 * 3600 * 1000L);
    }

    /**
     * 获取前天
     */
    public static Date getPreYesterday() {
        return new Date(new Date().getTime() - 48 * 3600 * 1000L);
    }

    /**
     * 获取
     */
    public static Date getYesterdayDate(Date day) {
        return new Date(day.getTime() - 24 * 3600 * 1000L);
    }

    /**
     * 获取明天
     */
    public static Date getTomorrowDate(Date day) {
        return new Date(day.getTime() + 24 * 3600 * 1000L);
    }

    /**
     * 获取传入的时间的12小时后时间
     */
    public static Date getShier(Date day) {
        return new Date(day.getTime() + 12 * 3600 * 1000L);
    }

    /**
     * 根据传入的值返回时间
     */
    public static Date getTimeDay(Date day, int time) {
        return new Date(day.getTime() + time * 24 * 3600 * 1000L);
    }

    /**
     * 获取后天
     */
    public static Date getDate_3(Date day) {
        return new Date(day.getTime() + 72 * 3600 * 1000L);
    }

    /**
     * 获取上周
     */
    public static Date getLastWeek(Date day) {
        return new Date(day.getTime() - 7 * 24 * 3600 * 1000L);
    }

    /**
     * 获取下周
     */
    public static Date getNextWeek(Date day) {
        return new Date(day.getTime() + 7 * 24 * 3600 * 1000L);
    }

    /**
     * 获取上个月
     */
    public static Date getLastMonth() {
        return getLastMonth(new Date());
    }

    /**
     * 获得指定时间的某月的第一天
     *
     * @param date
     * @return
     */
    public static Date getMonthFirstDay(Date date) {
        int[] dateArr = getDateArray(date);
        String year = String.valueOf(dateArr[0]);
        String month = String.valueOf(dateArr[1]);
        month = month.length() == 1 ? "0" + month : month;
        Date retDate = convertStringToDate(year + month + "01", "yyyyMMdd");
        return retDate;
    }

    /**
     * 获得指定时间的某月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getMonthLastDay(Date date) {
        int[] dateArr = getDateArray(date);
        int year = dateArr[0];
        int month = dateArr[1];
        int maxDayOfMonth = getMaxDayOfMonth(year, month);
        String monStr = String.valueOf(month);
        monStr = monStr.length() == 1 ? "0" + monStr : monStr;
        Date retDate =
                convertStringToDate(String.valueOf(year) + String.valueOf(monStr)
                        + String.valueOf(maxDayOfMonth), "yyyyMMdd");
        return retDate;
    }

    /**
     * 获取制定时间的上个月
     */
    public static Date getLastMonth(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        cal.getTime();
        return cal.getTime();
    }

    /**
     * 获取制定时间的下个月
     */
    public static Date getNextMonth(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.getTime();
        return cal.getTime();
    }

    /**
     * 获取指定年和月中该月的最大天数
     *
     * @param year  指定年
     * @param month 指定月 1-12
     * @return 该月最大天数
     */
    public static int getMaxDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.set(year, month - 1, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据指定的年份和指定的第多少周序号得到该周的第一天和最后一天日期
     *
     * @param year   指定的年份,如2006
     * @param weekNo 指定年份中的第多少周,如37
     * @return 该周的起始日期后该周的结束日期<br>
     * Date[0] 起始日期<br>
     * Date[1] 结束日期
     */
    public static Date[] getGivenWeekDates(int year, int weekNo) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        Date begin = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 6);
        Date end = cal.getTime();
        return new Date[]{begin, end};
    }

    /**
     * 根据指定日期获取其在一年中的第多少周
     *
     * @param date 指定的日期,为null默认为当时日期
     * @return 当年的第多少周序号, 如37
     */
    public static int getWeekNo(Date date) {
        if (date == null) date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取制定时间的年份
     *
     * @return 年份
     */
    public static int getYear() {
        Date date = getNows();
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取制定时间的年份
     *
     * @return 年份
     */
    public static int getYear(int year) {
        Date date = getNows();
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.setTime(date);
        return cal.get(Calendar.YEAR) + year;
    }

    /**
     * 获取制定时间的月份
     *
     * @param date 制定时间
     * @return 年份
     */
    public static int getMonth(Date date) {
        if (date == null) date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

	  /*
       * public static void main(String[] args) { Date dd = convertStringToDate("2006-9-1"); Date d =
	   * getLastMonth(dd); System.out.println(fmtDate(d, "yyyy-MM-dd")); }
	   */

    /**
     * 格式化日期
     *
     * @param date  被格式化的日期
     * @param style 显示的样式，如yyyyMMdd
     */
    public static String fmtDate(Date date, String style) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(style);
        return dateFormat.format(date);
    }

    /**
     * 得到当前日期
     *
     * @return int[] int[0] 年 int[1] 月 int[2] 日 int[3] 时 int[4] 分 int[5] 秒
     */
    public static int[] getCurrentDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.setTime(new Date());
        int[] date = new int[6];
        date[0] = cal.get(Calendar.YEAR);
        date[1] = cal.get(Calendar.MONTH) + 1;
        date[2] = cal.get(Calendar.DATE);
        date[3] = cal.get(Calendar.HOUR_OF_DAY);
        date[4] = cal.get(Calendar.MINUTE);
        date[5] = cal.get(Calendar.SECOND);
        return date;
    }

    /**
     * 得到指定日期
     *
     * @return int[] int[0] 年 int[1] 月 int[2] 日 int[3] 时 int[4] 分 int[5] 秒
     */
    public static int[] getDateArray(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.setTime(date);
        int[] dateArr = new int[6];
        dateArr[0] = cal.get(Calendar.YEAR);
        dateArr[1] = cal.get(Calendar.MONTH) + 1;
        dateArr[2] = cal.get(Calendar.DATE);
        dateArr[3] = cal.get(Calendar.HOUR_OF_DAY);
        dateArr[4] = cal.get(Calendar.MINUTE);
        dateArr[5] = cal.get(Calendar.SECOND);
        return dateArr;
    }

    /**
     * 设置制定的年份和月份，再得到该日期的前多少月或后多少月的日期年份和月份
     *
     * @param year      指定的年份，如 2006
     * @param month     制定的月份，如 6
     * @param monthSect 月份的差值 如：现在为2006年5月份，要得到后4月，则monthSect = 4，正确日期结果为2006年9月
     *                  如：现在为2006年5月份，要得到前4月，则monthSect = -4，正确日期结果为2006年1月 如：monthSect = 0，则表示为year年month月
     * @return int[] int[0] 年份 int[1] 月份
     */
    public static int[] getLimitMonthDate(int year, int month, int monthSect) {
        year = year < 1 ? 1 : year;
        month = month > 12 ? 12 : month;
        month = month < 1 ? 1 : month;
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), new Locale("zh", "CN"));
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.add(Calendar.MONTH, monthSect);
        int[] yAndM = new int[2];
        yAndM[0] = cal.get(Calendar.YEAR);
        yAndM[1] = cal.get(Calendar.MONTH);
        if (yAndM[1] == 0) {
            yAndM[0] = yAndM[0] - 1;
            yAndM[1] = 12;
        }
        return yAndM;
    }

    public static Date getDate(Date date, String style) {
        SimpleDateFormat format = new SimpleDateFormat(style);
        try {
            return format.parse(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getSimpleDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到本月的第一天和最后一天的字符串的数组
     *
     * @param month 格式 'yyyyMM'
     * @return
     */
    public static String[] getMonFirstLastDays(String month) {
        Date thisDate = convertStringToDate(month, "yyyyMM");
        Date firstDay = getMonthFirstDay(thisDate);
        Date lastDay = getMonthLastDay(thisDate);
        return new String[]{convertDateToString("yyyyMMdd", firstDay),
                convertDateToString("yyyyMMdd", lastDay)};
    }

    /**
     * 获取传入时间的当月的日期 yyyymmdd author:Liu Liming
     *
     * @param yyyymmdd
     * @return
     */
    public static String getFirstDate(String yyyymmdd) {
        try {
            Date date = DatetimeUtil.convertStringToDate("yyyyMMdd", null, null, yyyymmdd);
            Calendar curCal = Calendar.getInstance();
            curCal.setTime(date);
            curCal.set(Calendar.DATE, 1);
            return DatetimeUtil.convertDateToString("yyyyMMdd", curCal.getTime());
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 获取传入时间的当月的日期 yyyymmdd author:Liu Liming
     *
     * @param yyyymmdd
     * @return yyyymmdd
     */
    public static String getLastDate(String yyyymmdd) {
        try {
            String sDate = getFirstDate(yyyymmdd);
            Date date = DatetimeUtil.convertStringToDate("yyyyMMdd", null, null, sDate);
            Calendar retVal = Calendar.getInstance();
            retVal.setTime(date);
            retVal.add(Calendar.MONTH, 1);
            retVal.add(Calendar.DATE, -1);
            return DatetimeUtil.convertDateToString("yyyyMMdd", retVal.getTime());

        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 获取Next天
     */
    public static String getNextDay(Object date, int amount) {
        SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            if (date instanceof String) {
                calendar.setTime(frm.parse(date.toString()));
            } else if (date instanceof Date) {
                calendar.setTime((Date) date);
            }
            calendar.add(Calendar.DATE, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return frm.format(calendar.getTime());
    }

    /**
     * 获取Next天
     */
    public static String getNextDay(Object date, int amount, String pattern) {
        SimpleDateFormat frm = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        try {
            if (date instanceof String) {
                calendar.setTime(frm.parse(date.toString()));
            } else if (date instanceof Date) {
                calendar.setTime((Date) date);
            }
            calendar.add(Calendar.DATE, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return frm.format(calendar.getTime());
    }

    /**
     * 获取Next MINUTE
     */
    public static String getNextMinute(Object date, int amount) {
        SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            if (date instanceof String) {
                calendar.setTime(frm.parse(date.toString()));
            } else if (date instanceof Date) {
                calendar.setTime((Date) date);
            }
            calendar.add(Calendar.MINUTE, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return frm.format(calendar.getTime());
    }

    /**
     * 将字符串20080808 转换成 2008-08-08
     */
    public static String getDateForm(String date) {
        if (StringUtils.isEmpty(date) || date.length() != 8) {
            return "0000-00-00";
        }
        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
    }

    /**
     * @param date   合法的java日期格式
     * @param day    计算的天数
     * @param format 日期格式化参数 与date格式一致
     * @return
     * @Description:计算给定date在给定day后的值
     */
    public static String calculateDate(String date, int day, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            Date d = df.parse(date);
            Calendar c = Calendar.getInstance();

            c.setTime(d);
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + day);

            return df.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断是否为同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        boolean result = false;
        try {
            String date1Str = DatetimeUtil.convertDateToString("yyyyMMdd", date1);
            String date2Str = DatetimeUtil.convertDateToString("yyyyMMdd", date2);
            if (date1Str.equals(date2Str)) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date getOptinDate(Date date, Integer moth) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formatdate = formatter.format(date);

        formatdate.substring(0, 4);
        formatdate.substring(4, 6);
        formatdate.substring(6, 8);

        return date;
    }

    /**
     * 得到每个月的天数
     *
     * @param year
     * @param moth
     * @return Integer
     * @throws
     * @author 李炯
     * @date 2014-9-18 下午05:38:30
     * @Title getMothDays
     * @Description
     */
    public static Integer getMothDays(int year, int moth) {
        int days = 0;
        Integer[] moth31 = new Integer[]{1, 3, 5, 7, 8, 10, 12};
        Integer[] moth30 = new Integer[]{4, 6, 9, 11};

        for (int i = 0; i < moth31.length; i++) {
            if (moth == moth31[i]) {
                days = 31;
                break;
            }
        }

        for (int i = 0; i < moth30.length; i++) {
            if (moth == moth30[i]) {
                days = 30;
                break;
            }
        }
        if (moth == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                days = 29;// 闰年
            } else {
                days = 28;
            }

        }
        return days;

    }

    /**
     * @return Date
     * @throws ParseException 得到几个月后的今天
     * @throws
     * @author 李炯
     * @date 2014-9-18 下午09:46:08
     * @Title getNextAllDate
     * @Description
     */
    public static Date getNextAllDate(int nextMonth) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formatdate = formatter.format(calendar.getTime());
        // 截取今天的年份
        String n = formatdate.substring(0, 4);
        // 截取今天的月份
        Integer m = Integer.valueOf(formatdate.substring(5, 7));
        // 截取今天的日期
        Integer d = Integer.valueOf(formatdate.substring(8, 10));
        int year = Integer.valueOf(n);
//	    System.out.println("nowYear:" + n + "---m::" + m + "--d::" + d);

        // 短信提醒按每个月31天算
        int initday = 31;
        // 当前月还剩余多少天没有过
        int lastDay = initday - d;
        int openDays = initday * nextMonth;
        // 日子递减
        openDays = openDays - lastDay;
        // 月份递增
        m = m + 1;
        for (int i = 0; i < nextMonth + 1; i++) {
            if (openDays > initday) {
                openDays = openDays - initday;
                m = m + 1;
            } else {
                break;
            }
        }

        int endMoth = 0;
        int endYear = 0;
        if (m > 12) {
            endMoth = m - 12;
            endYear = year + 1;
        } else {
            endMoth = m;
            endYear = year;
        }

        String endDate = endYear + "-" + endMoth + "-" + openDays;
//	    System.out.println("endDate--------" + endDate);
        Date nextDate = formatter.parse(endDate);
        return nextDate;
    }

    /**
     * 得到int数组里最大的值
     *
     * @param arr
     * @return int
     * @throws
     * @author 李炯
     * @date 2014-9-19 上午09:53:40
     * @Title getMax
     * @Description
     */
    public static int getMax(Integer[] arr) {
        int maxElement = arr[0];
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > maxElement) maxElement = arr[x];
        }
        return maxElement;
    }


    /**
     * @return int
     * @throws
     * @Description: 1 表示date2比date1大； -1表示date2比date1小；
     * @author 邓集海
     * @date 2014-12-4下午02:06:01
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
//	        System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
//	        System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static int compare_date(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static int diffdates1(Date date1, Date date2) {
        int result = 0;
        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();

        gc1.setTime(date1);
        gc2.setTime(date2);
        result = getMinutl(gc1, gc2);

        return result;
    }

    public static int getMinutl(GregorianCalendar g1, GregorianCalendar g2) {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;

        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        } else {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }

        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.HOUR_OF_DAY);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.HOUR_OF_DAY);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.MINUTE, 1);
            elapsed++;
        }
        return elapsed;
    }

    /**
     * 根据传入的时间与当前时间相比较 得到String
     *
     * @param date
     * @return String
     * @author 何高建
     * @date 2016-8-8下午02:35:01
     */
    public static String getDateString(Date date) {
        Date nowtime = formatDate(ZH_CN_DATETIME_HOURS, getNow());
        if (date.getTime() >= nowtime.getTime() - 1 * 60 * 1000) {
            return DateString1;
        } else if (date.getTime() >= nowtime.getTime() - 59 * 60 * 1000) {
            return diffdates1(date, nowtime) + DateString2;
        }/*else if(date.getTime()>nowtime.getTime()-2*60*60*1000){
                 return DateString3;
			 }else if(isSameDay(date, nowtime)){
				 return DateString4+formatDate(date, TIME_PATTERN1);
			 }else if(isSameDay(date, getYesterday())){
				 return DateString5+formatDate(date, TIME_PATTERN1);
			 }*/
        return formatDate(date, ZH_CN_DATETIME_HOURS1);
    }


    public static String getDateString1(Date date) {
        Date nowtime = formatDate(ZH_CN_DATETIME_HOURS, getNow());
        if (date.getTime() >= nowtime.getTime() - 1 * 60 * 1000) {
            return DateString1;
        } else if (date.getTime() >= nowtime.getTime() - 59 * 60 * 1000) {
            return diffdates1(date, nowtime) + DateString2;
        }/*else if(date.getTime()>nowtime.getTime()-2*60*60*1000){
                 return DateString3;
			 }else if(isSameDay(date, nowtime)){
				 return DateString4+formatDate(date, TIME_PATTERN1);
			 }else if(isSameDay(date, getYesterday())){
				 return DateString5+formatDate(date, TIME_PATTERN1);
			 }*/
        return "今天" + formatDate(date, TIME_PATTERN1);
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     * @throws ParseException
     */
    public static long[] getDistanceTimes(String str1, String str2, String DateStyle)
            throws ParseException {
        DateFormat df = new SimpleDateFormat(DateStyle);
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min};
        return times;
    }

    public static boolean isTong(Date date1, Date date2) {
        String date1Str = formatDate(date1, DATE_PATTERN);
        String date2Str = formatDate(date2, DATE_PATTERN);
        if (date1Str.equals(date2Str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static Date timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return null;
        }
        if (format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return convertStringToDate(sdf.format(new Date(Long.valueOf(seconds + "000"))), DATE_PATTERN);
    }


    public static String getWeekDayString(Date date) {
        String weekString = "";

        final String dayNames[] = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        weekString = dayNames[dayOfWeek - 1];

        return weekString;

    }

    public static boolean getmin(Date date1, Date date2) {
        if (formatDate(ZH_CN_DATETIME_HOURS, date2).getTime() >=
                formatDate(ZH_CN_DATETIME_HOURS, date1).getTime() + 2 * 60 * 1000) {
            return true;
        }
        return false;
    }
}
