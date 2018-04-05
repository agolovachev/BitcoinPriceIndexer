package com.agolovachev.bitcoinpriceindexer.Historical;

import android.support.annotation.Nullable;

import com.agolovachev.bitcoinpriceindexer.model.Historical;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

public class DefaultHistoricalRepository implements HistoricalRepository {
    private static final String HISTORICAL_FOR_WEEK = "https://api.coindesk.com/v1/bpi/historical/close.json?start=2018-03-29&end=2018-04-05";

    private OkHttpClient mClient;
    private Request mRequest;
    private Gson mGson = new Gson();

    @Override
    public Map<String, Float> getHistoricalForWeek() throws IOException {
        String forKzt = HISTORICAL_FOR_WEEK.concat("&currency=KZT");
        mClient = new OkHttpClient();
        mRequest = new Request.Builder()
                .url(forKzt)
                .build();
        Response response = mClient.newCall(mRequest).execute();
        if (response.isSuccessful()) {
            return manageResponse(response);
        }

        return null;
    }

    @Nullable
    private Map<String, Float> manageResponse(Response response) {
        String responseBody;
        try {
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
        if (responseBody.isEmpty()) {
            return null;
        }

        Historical historical = mGson.fromJson(responseBody, Historical.class);

        return historical.getBpi();
    }
}
