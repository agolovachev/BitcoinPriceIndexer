package com.agolovachev.bitcoinpriceindexer.Historical;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.agolovachev.bitcoinpriceindexer.R;
import com.agolovachev.bitcoinpriceindexer.loader.HistoricalLoader;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoricalActivity extends AppCompatActivity {

    HistoricalLoader mHistoricalLoader;
    LineChart mLineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HistoricalRepository repository = new DefaultHistoricalRepository();
        mHistoricalLoader = new HistoricalLoader(this, repository);
        getSupportLoaderManager().initLoader(0, null, defaultCallback);

        mLineChart = findViewById(R.id.activity_main_line_chart);

    }

    private LoaderManager.LoaderCallbacks<Map<String, Float>> defaultCallback =
            new LoaderManager.LoaderCallbacks<Map<String, Float>>() {
                @Override
                public Loader<Map<String, Float>> onCreateLoader(int i, Bundle bundle) {
                    return mHistoricalLoader;
                }

                @Override
                public void onLoadFinished(Loader<Map<String, Float>> loader, Map<String, Float> data) {
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
}