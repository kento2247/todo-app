package com.todoClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static Date format(String originalDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            Date date = sdf.parse(originalDateStr);
            System.out.println("変換された日時：" + date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(Date originalDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String targetDateStr = sdf.format(originalDate);
        return targetDateStr;
    }
}
