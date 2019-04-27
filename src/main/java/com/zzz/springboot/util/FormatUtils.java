package com.zzz.springboot.util;

public class FormatUtils {

    public static String getFormatter(int length) {
        String formatter = "yyyy-MM-dd";
        if (length == 16) {
            formatter = "yyyy-MM-dd HH:mm";
        } else if (length == 19) {
            formatter = "yyyy-MM-dd HH:mm:ss";
        } else if (length == 20) {
            formatter = "yyyy-MM-dd HH:mm:ss.S";
        } else if (length == 21) {
            formatter = "yyyy-MM-dd HH:mm:ss.SS";
        } else if (length == 22) {
            formatter = "yyyy-MM-dd HH:mm:ss.SSS";
        }
        return formatter;
    }
}