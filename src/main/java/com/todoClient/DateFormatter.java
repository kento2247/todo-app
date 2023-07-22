package com.todoClient;

import java.util.Date;

public class DateFormatter {
    public static Date format(String originalDateStr) {
        if (originalDateStr.length() != 16) {
            return null;
        }
        if (originalDateStr.charAt(4) != '/' || originalDateStr.charAt(7) != '/' || originalDateStr.charAt(10) != ' '
                || originalDateStr.charAt(13) != ':') {
            System.out.println("invalid format");
            return null;
        }
        int yyyy = Integer.parseInt(originalDateStr.substring(0, 4));
        int MM = Integer.parseInt(originalDateStr.substring(5, 7));
        int dd = Integer.parseInt(originalDateStr.substring(8, 10));
        int HH = Integer.parseInt(originalDateStr.substring(11, 13));
        int mm = Integer.parseInt(originalDateStr.substring(14, 16));
        return new Date(yyyy - 1900, MM - 1, dd, HH, mm);
        // SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        // originalFormat.setTimeZone(TimeZone.getTimeZone("GSM+9"));
        // SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        // targetFormat.setTimeZone(TimeZone.getTimeZone("GSM+0"));
        // try {
        // Date date = originalFormat.parse(originalDateStr);
        // String formattedDateString = targetFormat.format(date);
        // return targetFormat.parse(formattedDateString);
        // } catch (ParseException e) {
        // e.printStackTrace();
        // return null;
        // }
    }

    public static String format(Date originalDate) {
        int yyyy = originalDate.getYear() + 1900;
        int MM = originalDate.getMonth() + 1;
        int dd = originalDate.getDate();
        int HH = originalDate.getHours();
        int mm = originalDate.getMinutes();
        String mm_str = String.valueOf(mm);
        if (mm_str.length() == 1) {
            mm_str = "0" + mm_str;
        }
        String dd_str = String.valueOf(dd);
        if (dd_str.length() == 1) {
            dd_str = "0" + dd_str;
        }
        String HH_str = String.valueOf(HH);
        if (HH_str.length() == 1) {
            HH_str = "0" + HH_str;
        }
        String MM_str = String.valueOf(MM);
        if (MM_str.length() == 1) {
            MM_str = "0" + MM_str;
        }

        String targetDateStr = yyyy + "/" + MM_str + "/" + dd_str + " " + HH_str + ":" + mm_str;
        return targetDateStr;
        // try {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        // String targetDateStr = sdf.format(originalDate);
        // return targetDateStr;
        // } catch (Exception e) {
        // e.printStackTrace();
        // return null;
        // }
    }
}
