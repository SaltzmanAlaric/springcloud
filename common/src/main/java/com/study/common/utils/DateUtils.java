package com.study.common.utils;

import com.study.common.constant.DatePattern;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类.
 */
public class DateUtils {

    private static String[][] QUARTERS = {{"01", "02", "03"}, {"04", "05", "06"}, {"07", "08", "09"}, {"10", "11", "12"}};

    /**
     * 获取时间字符串
     *
     * @return 字符串
     */
    public static String getStringDate(Date date, DatePattern pattern) {
        return new SimpleDateFormat(pattern.getValue()).format(date);
    }

    /**
     * 获取今日凌晨时间
     *
     * @return 时间
     */
    public static Date getTodayBegin() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取时间
     *
     * @return Date
     */
    public static Date getDate(String dateStr, DatePattern pattern) {
        SimpleDateFormat fmt = new SimpleDateFormat(pattern.getValue());
        Date date = null;
        try {
            date = fmt.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取今天从零点到现在的小时集合
     */
    public static List<String> getTodayHours() {
        Date now = new Date();
        int h = now.getHours();
        List<String> hours = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            hours.add(String.format("%02d", i));
        }
        return hours;
    }

    /**
     * 根据当前日期获得现在是当年第几周
     *
     * @param date 日期
     * @return 周序号
     */
    public static int getWeekNum(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 根据当前日期获得本周的日期集合（本周周一到周日日期）
     *
     * @param datePattern 日期格式方式
     * @return 本周日期集合
     */
    public static List<String> getThisWeekDays(DatePattern datePattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(4);
        //获得当前的年
        int weekYear = calendar.get(Calendar.YEAR);
        //获得当前日期属于今年的第几周
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        return getWeekDays(weekYear, weekOfYear, datePattern);
    }

    /**
     * 根据年份和周数获取所在周日期集合（所在周 周一到周日日期）
     *
     * @param weekYear    年份
     * @param weekOfYear  周数
     * @param datePattern 日期格式
     * @return 所在周日期集合
     */
    private static List<String> getWeekDays(int weekYear, int weekOfYear, DatePattern datePattern) {
        List<String> weekDays = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        //获得指定年的第几周的开始日期
        calendar.setWeekDate(weekYear, weekOfYear + 1, 2);
        int n = 7;
        calendar.add(Calendar.DATE, -n);
        for (int i = 0; i < n; i++) {
            String time = DateUtils.getStringDate(calendar.getTime(), datePattern);
            weekDays.add(time);
            calendar.add(Calendar.DATE, 1);
        }
        return weekDays;
    }

    /**
     * 根据当前日期获得上周的日期集合（上周周一到周日日期）
     *
     * @param datePattern 日期格式方式
     * @return 上周日期集合
     */
    public static List<String> getLastWeekDays(DatePattern datePattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(4);
        //获得当前的年
        int weekYear = calendar.get(Calendar.YEAR);
        //获得当前日期属于今年的第几周
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        return getWeekDays(weekYear, weekOfYear - 1, datePattern);
    }

    /**
     * 获取当前时间所在年的最大周数
     *
     * @param year 年份
     * @return 最大周数
     */
    private static int getMaxWeekNumOfYear(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(4);
        int i = c.get(Calendar.DAY_OF_WEEK);
        int week = c.get(Calendar.WEEK_OF_YEAR);
        if (week == 1) {
            c.add(Calendar.DATE, -7);
            week = c.get(Calendar.WEEK_OF_YEAR);
        }
        return week;
    }

    /**
     * 根据年份和季度获取周数集合
     *
     * @param year    年份
     * @param quarter 季度[1,2,3,4]
     * @return 周数集合
     */
    public static List<Integer> getWeeksByQuarter(int year, int quarter) {
        int startWeekNum = getStartWeekNumByQuarter(year, quarter);
        int endWeekNum = getEndWeekNumByQuarter(year, quarter);
        List<Integer> weeks = new ArrayList<>();
        while (startWeekNum <= endWeekNum) {
            weeks.add(startWeekNum++);
        }
        return weeks;
    }

    /**
     * 根据年份和季度获取开始周数
     *
     * @param year    年份
     * @param quarter 季度[1,2,3,4]
     * @return 开始周数
     */
    public static int getStartWeekNumByQuarter(int year, int quarter) {
        int startWeekNum;
        if (quarter == 1) {
            startWeekNum = 1;
        } else {
            startWeekNum = getWeekNum(getDate(year + QUARTERS[quarter - 1][0] + "01", DatePattern.DATE0));
        }
        return startWeekNum;
    }

    /**
     * 根据年份和季度获取结束周数
     *
     * @param year    年份
     * @param quarter 季度[1,2,3,4]
     * @return 结束周数
     */
    public static int getEndWeekNumByQuarter(int year, int quarter) {
        int endWeekNum;
        if (quarter == 4) {
            endWeekNum = getMaxWeekNumOfYear(year);
        } else {
            endWeekNum = getWeekNum(getDate(year + QUARTERS[quarter - 1][2] + (quarter == 1 ? 31 : 30), DatePattern.DATE0));
        }
        return endWeekNum;
    }

}
