package com.todoClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    //public static Date format(String originalDateStr) {
    //    System.out.println("変換前の日時：" + originalDateStr);
    //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    //    try {
    //        Date date = sdf.parse(originalDateStr);
    //        System.out.println("変換された日時：" + date);
    //        return date;
    //    } catch (ParseException e) {
    //        e.printStackTrace();
    //        return null;
    //    }
    //}

    public static Date format(String originalDateStr) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = originalFormat.parse(originalDateStr);
            String formattedDateString = targetFormat.format(date);
            return targetFormat.parse(formattedDateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(Date originalDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            String targetDateStr = sdf.format(originalDate);
            return targetDateStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
