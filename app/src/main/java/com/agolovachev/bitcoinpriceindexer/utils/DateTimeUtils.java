package com.agolovachev.bitcoinpriceindexer.utils;


import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.Map;

public class DateTimeUtils {
    private static LocalDate today = LocalDate.now();
    private static LocalDate weekBefore = today.minusWeeks(1);
    private static LocalDate monthBefore = today.minusMonths(1);
    private static LocalDate yearBefore = today.minusYears(1);

    private static String format = "YYYY-MM-dd";
    private static DateTimeFormatter sFormatter = DateTimeFormatter.ofPattern(format);

    public static LocalDate getToday() {
        return today;
    }

    public static LocalDate getWeekBefore() {
        return weekBefore;
    }

    public static LocalDate getMonthBefore() {
        return monthBefore;
    }

    public static LocalDate getYearBefore() {
        return yearBefore;
    }

    public static String formQueryForWeek() {
        return "?start=" +
                weekBefore +
                "&end=" +
                today;
    }

    public static String formQueryForMonth() {
        return "?start=" +
                monthBefore +
                "&end=" +
                today;
    }

    public static String formQueryForYear() {
        return "?start=" +
                yearBefore +
                "&end=" +
                today;
    }

    public static Map<String, Float> getWeeklyAverageForMonth(Map<String, Float> rawData) {
        Map<String, Float> averageData = new HashMap<>();

        return averageData;
    }
}
