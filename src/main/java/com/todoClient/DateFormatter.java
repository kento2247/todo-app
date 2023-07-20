package com.todoClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String format(String originalDateStr) {
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        try {
            // 元の日付文字列をLocalDateTimeに変換
            LocalDateTime originalDateTime = LocalDateTime.parse(originalDateStr, originalFormatter);
            // ターゲットの形式に変換
            String targetDateStr = originalDateTime.format(targetFormatter);
            return targetDateStr;
        } catch (Exception e) {
            System.out.println("日付の変換に失敗しました。");
            e.printStackTrace();
            return "";
        }
    }
    public static String format(Date originalDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String targetDateStr = sdf.format(originalDate);
        return targetDateStr;
    }
}
