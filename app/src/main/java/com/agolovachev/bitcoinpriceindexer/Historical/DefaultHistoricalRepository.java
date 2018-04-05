package com.agolovachev.bitcoinpriceindexer.Historical;

import android.support.annotation.Nullable;

import com.agolovachev.bitcoinpriceindexer.model.Currency;
import com.agolovachev.bitcoinpriceindexer.model.CurrencyCode;
import com.agolovachev.bitcoinpriceindexer.model.CurrentPrice;
import com.agolovachev.bitcoinpriceindexer.model.Historical;
import com.agolovachev.bitcoinpriceindexer.utils.DateTimeUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultHistoricalRepository implements HistoricalRepository {
    private static final String HISTORICAL_FOR_WEEK = "https://api.coindesk.com/v1/bpi/historical/close.json?start=2018-03-29&end=2018-04-05";
    private static final String BASE_HISTORICAL = "https://api.coindesk.com/v1/bpi/historical/close.json";
    private static final String CURRENT_PRICE = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private static final String CURRENT_PRICE_FOR_KZT = "https://api.coindesk.com/v1/bpi/currentprice/KZT.json";

    private OkHttpClient mClient = new OkHttpClient();
    private Gson mGson = new Gson();

    @Nullable
    @Override
    public Map<String, Float> getHistoricalForWeek() throws IOException {
        String forWeek = BASE_HISTORICAL.concat(DateTimeUtils.formQueryForWeek());
        String forKzt = forWeek.concat("&currency=KZT");
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
        Map<CurrencyCode, Currency> data = new HashMap<>();
        Request request = new Request.Builder()
                .url(CURRENT_PRICE)
                .build();
        Response response = mClient.newCall(request).execute();
        if (response.isSuccessful()) {
            data.putAll(manageCurrentPriceResponse(response));
        }
        Request requestForKzt = new Request.Builder()
                .url(CURRENT_PRICE_FOR_KZT)
                .build();
        Response responseForKzt = mClient.newCall(requestForKzt).execute();
        if (responseForKzt.isSuccessful()) {
            data.putAll(manageCurrentPriceResponse(responseForKzt));
        }

        return data;
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
