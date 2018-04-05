package com.agolovachev.bitcoinpriceindexer.Historical;

import android.support.annotation.Nullable;

import com.agolovachev.bitcoinpriceindexer.model.Currency;
import com.agolovachev.bitcoinpriceindexer.model.CurrencyCode;
import com.agolovachev.bitcoinpriceindexer.model.CurrentPrice;
import com.agolovachev.bitcoinpriceindexer.model.Historical;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

public class DefaultHistoricalRepository implements HistoricalRepository {
    private static final String HISTORICAL_FOR_WEEK = "https://api.coindesk.com/v1/bpi/historical/close.json?start=2018-03-29&end=2018-04-05";
    private static final String CURRENT_PRICE = "https://api.coindesk.com/v1/bpi/currentprice.json";

    private OkHttpClient mClient = new OkHttpClient();
    private Gson mGson = new Gson();

    @Nullable
    @Override
    public Map<String, Float> getHistoricalForWeek() throws IOException {
        String forKzt = HISTORICAL_FOR_WEEK.concat("&currency=KZT");
        Request request = new Request.Builder()
                .url(forKzt)
                .build();
        Response response = mClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return manageHistoricalResponse(response);
        }

        return null;
    }

    @Nullable
    @Override
    public Map<CurrencyCode, Currency> getCurrentPrice() throws IOException {
        Request request = new Request.Builder()
                .url(CURRENT_PRICE)
                .build();
        Response response = mClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return manageCurrentPriceResponse(response);
        }

        return null;
    }

    @Nullable
    private Map<CurrencyCode, Currency> manageCurrentPriceResponse(Response response){
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
        CurrentPrice currentPrice = mGson.fromJson(responseBody, CurrentPrice.class);

        return currentPrice.getBpi();
    }

    @Nullable
    private Map<String, Float> manageHistoricalResponse(Response response) {
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
