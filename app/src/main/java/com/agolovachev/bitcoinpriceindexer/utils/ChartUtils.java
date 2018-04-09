package com.agolovachev.bitcoinpriceindexer.utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import java.util.ArrayList;
import java.util.List;

public class ChartUtils {
    public static void setXAxis(LineChart chart) {
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
    }

    public static void setYAxis(LineChart chart) {
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxis(YAxis.AxisDependency.LEFT);
        leftAxis.mDecimals = 2;
    }

    public static List<String> getFormattedDatesForXAxis(List<String> unformattedDates) {
        List<String> formattedDates = new ArrayList<>();
        for (String date : unformattedDates) {
            String newDate = date.substring(5);
            formattedDates.add(newDate);
        }

        return formattedDates;
    }

}
