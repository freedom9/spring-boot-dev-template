package com.freedom.springbootdevtemplate.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author freedom
 * @date 2020/10/19 16:48
 */
public class TimeUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认格式化时间
     *
     * @param dateTime
     * @return
     */
    public static String defaultFormatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DEFAULT_PATTERN);
    }

    /**
     * 格式化时间
     *
     * @param dateTime
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        return dtf.format(dateTime);
    }

    /**
     * 将字符串转日期成Long类型的时间戳
     *
     * @param time
     * @param pattern
     * @return
     */
    public static Long convertTimeToLong(String time, String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        LocalDateTime parse = LocalDateTime.parse(time, dtf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
