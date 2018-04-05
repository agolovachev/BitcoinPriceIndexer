package com.agolovachev.bitcoinpriceindexer.utils;


import android.annotation.SuppressLint;

import java.time.LocalDate;

public class DateTimeUtils {
    @SuppressLint("NewApi")
    private static LocalDate today = LocalDate.now();
    @SuppressLint("NewApi")
    private static LocalDate weekBefore = today.minusWeeks(1);
    @SuppressLint("NewApi")
    private static LocalDate monthBefore = today.minusMonths(1);
    @SuppressLint("NewApi")
    private static LocalDate yearBefore = today.minusYears(1);

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
}
