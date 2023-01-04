package live.xiaoxu.util;

import live.xiaoxu.constants.DateConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * <p>日期工具类</p>
 *
 * @author 小徐
 * @since 2023/1/3 16:51
 */
public class XDateUtils {

    /**
     * {@link java.util.Date Date} 转 {@link java.time.LocalDateTime LocalDateTime}
     *
     * @param date 日期
     * @return 转换的 {@link java.time.LocalDateTime LocalDateTime}
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {

        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * {@link java.time.LocalDateTime LocalDateTime} 转 {@link java.util.Date Date}
     *
     * @param localDateTime 日期
     * @return 转换的 {@link java.util.Date Date}
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取当前年份
     *
     * @return 当前年份
     */
    public static int getCurrentYear() {
        return Integer.parseInt(format(LocalDateTime.now(), DateConstants.YEAR));
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static String getNowTime() {
        return format(LocalDateTime.now(), DateConstants.DEFAULT_DATE_FORMAT);
    }

    /**
     * 获取当前时间
     *
     * @param pattern 样式
     * @return 当前时间
     */
    public static String getNowTime(String pattern) {
        return format(LocalDateTime.now(), pattern);
    }

    /**
     * 格式化时间
     *
     * @param localDateTime 指定时间
     * @param pattern       样式
     * @return 结果
     */
    public static String format(LocalDateTime localDateTime, String pattern) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.CHINA);
        return localDateTime.format(formatter);
    }

    /**
     * 格式化时间
     *
     * @param date    指定时间
     * @param pattern 样式
     * @return 结果
     */
    public static String format(Date date, String pattern) {

        return format(dateToLocalDateTime(date), pattern);
    }

    /**
     * {@link java.lang.String String} 转 {@link java.util.Date Date}
     *
     * @param date    日期
     * @param pattern 格式化字符串
     * @return 结果
     */
    public static Date parse(String date, String pattern) {

        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取最近几年年份
     *
     * @param someYear 0 为今年。1 为去年 + 今年，以此类推
     * @return 最近几年年份
     */
    public static List<Integer> getLastSomeYear(int someYear) {

        return getLastSomeYear(someYear, getCurrentYear());
    }

    /**
     * 获取最近几年年份，根据指定年份倒推
     *
     * @param someYear   指定年份
     * @param targetYear 目标年份
     * @return 指定年份与目标年份的年份列表
     */
    public static List<Integer> getLastSomeYear(int someYear, int targetYear) {

        if (someYear < 0) {
            return new ArrayList<>();
        }
        List<Integer> yearList = new ArrayList<>();
        for (int i = 0; i <= someYear; i++) {
            yearList.add(targetYear - i);
        }
        return yearList;
    }
}