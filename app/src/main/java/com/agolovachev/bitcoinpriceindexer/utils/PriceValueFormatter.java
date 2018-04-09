package com.agolovachev.bitcoinpriceindexer.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class PriceValueFormatter implements IAxisValueFormatter {
    private DecimalFormat mFormat;

    public PriceValueFormatter() {
        mFormat = new DecimalFormat("###,###,##0.0");
    }


    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value);
    }
}
