package com.agolovachev.bitcoinpriceindexer.Historical;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.agolovachev.bitcoinpriceindexer.R;
import com.agolovachev.bitcoinpriceindexer.loader.CurrentPriceLoader;
import com.agolovachev.bitcoinpriceindexer.loader.HistoricalLoader;
import com.agolovachev.bitcoinpriceindexer.model.Currency;
import com.agolovachev.bitcoinpriceindexer.model.CurrencyCode;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoricalActivity extends AppCompatActivity {
    private static final int HISTORICAL_LOADER_ID = 0;
    private static final int CURRENT_PRICE_LOADER_ID = 1;

    HistoricalLoader mHistoricalLoader;
    CurrentPriceLoader mCurrentPriceLoader;
    LineChart mLineChart;
    TextView mHeaderTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HistoricalRepository repository = new DefaultHistoricalRepository();
        mHistoricalLoader = new HistoricalLoader(this, repository);
        mCurrentPriceLoader = new CurrentPriceLoader(this, repository);
        getSupportLoaderManager().initLoader(HISTORICAL_LOADER_ID, null, historicalCallback);
        getSupportLoaderManager().initLoader(CURRENT_PRICE_LOADER_ID, null, currencyCallback);

        mLineChart = findViewById(R.id.activity_main_line_chart);
        mHeaderTextView = findViewById(R.id.activity_main_text_view);
    }

    private LoaderManager.LoaderCallbacks<Map<String, Float>> historicalCallback =
            new LoaderManager.LoaderCallbacks<Map<String, Float>>() {
                @Override
                public Loader<Map<String, Float>> onCreateLoader(int i, Bundle bundle) {
                    return mHistoricalLoader;
                }

                @Override
                public void onLoadFinished(Loader<Map<String, Float>> loader, @Nullable Map<String, Float> data) {
                    getSupportLoaderManager().destroyLoader(0);
                    if (data == null) {
                        return;
                    }

                    populateLineChart(data);
                }

                @Override
                public void onLoaderReset(Loader<Map<String, Float>> loader) {
                }
            };

    private LoaderManager.LoaderCallbacks<Map<CurrencyCode, Currency>> currencyCallback =
            new LoaderManager.LoaderCallbacks<Map<CurrencyCode, Currency>>() {
                @Override
                public Loader<Map<CurrencyCode, Currency>> onCreateLoader(int id, Bundle args) {
                    return mCurrentPriceLoader;
                }

                @Override
                public void onLoadFinished(Loader<Map<CurrencyCode, Currency>> loader, @Nullable Map<CurrencyCode, Currency> data) {
                    if (data == null || data.isEmpty()) {
                        return;
                    }
                    showCurrentPrice(data);
                }

                @Override
                public void onLoaderReset(Loader<Map<CurrencyCode, Currency>> loader) {
                }
            };

    private void populateLineChart(Map<String, Float> data) {
        List<Entry> entries = new ArrayList<>();
        Map<String, Float> objects = new HashMap<>(data);

        float x = 0;
        for (Map.Entry<String, Float> entry : objects.entrySet()) {
            entries.add(new Entry(x++, entry.getValue()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);
        mLineChart.invalidate();
    }

    private void showCurrentPrice(Map<CurrencyCode, Currency> data) {
        data.remove(CurrencyCode.GBP);
        StringBuilder builder = new StringBuilder("Current price: ");
        for (Map.Entry<CurrencyCode, Currency> entry : data.entrySet()) {
            builder
                    .append(entry.getKey())
                    .append(" : ")
                    .append(entry.getValue().getRate())
                    .append(" ");
        }

        mHeaderTextView.setText(builder.toString().trim());
    }
}