package com.agolovachev.bitcoinpriceindexer.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class DateValueFormatter implements IAxisValueFormatter {
    private List<String> dates;

    public DateValueFormatter(List<String> dates) {
        this.dates = new ArrayList<>(dates);
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return dates.get((int) value);
    }
}
