package com.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * author: LG
 * date: 2019-11-07 17:41
 * desc:
 */
public class TimeUtils {


    public static String format(Date time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(time);
        return format;
    }


    public static String format(long time){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(time);
        String format = formatter.format(cal.getTime());
        return format;
    }
    public static String format(Instant time){

        String format =  DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").format(LocalDateTime.ofInstant(time, ZoneId.systemDefault()));


        return format;
    }
}
