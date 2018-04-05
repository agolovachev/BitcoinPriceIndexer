package com.agolovachev.bitcoinpriceindexer.Historical;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.agolovachev.bitcoinpriceindexer.R;
import com.agolovachev.bitcoinpriceindexer.loader.HistoricalLoader;
import com.agolovachev.bitcoinpriceindexer.model.Historical;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoricalActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private static final String BASE_HISTORICAL = "https://api.coindesk.com/v1/bpi/historical/close.json?start=2018-01-01&end=2018-03-01";

    OkHttpClient mClient;
    Request mRequest;
    HistoricalLoader mHistoricalLoader;
    LineChart mLineChart;
    Gson mGson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClient = new OkHttpClient();
        mRequest = new Request.Builder()
                .url(BASE_HISTORICAL)
                .build();
        mHistoricalLoader = new HistoricalLoader(this, mClient, mRequest);
        getSupportLoaderManager().initLoader(0, null, defaultCallback);

        mLineChart = findViewById(R.id.activity_main_line_chart);

    }

    private LoaderManager.LoaderCallbacks<Response> defaultCallback = new LoaderManager.LoaderCallbacks<Response>() {
        @Override
        public Loader<Response> onCreateLoader(int i, Bundle bundle) {
            return mHistoricalLoader;
        }

        @Override
        public void onLoadFinished(Loader<Response> loader, Response response) {
            getSupportLoaderManager().destroyLoader(0);
            try {
                String responseBody = response.body().string();
                System.out.println(responseBody);
                Historical historical = mGson.fromJson(responseBody, Historical.class);
                System.out.println(historical);

                populateLineChart(historical.getBpi());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onLoaderReset(Loader<Response> loader) { }
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