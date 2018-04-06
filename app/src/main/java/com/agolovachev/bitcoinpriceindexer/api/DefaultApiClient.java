package com.agolovachev.bitcoinpriceindexer.api;

import android.support.annotation.Nullable;

import com.agolovachev.bitcoinpriceindexer.model.CurrencyCode;
import com.agolovachev.bitcoinpriceindexer.model.CurrentPrice;
import com.agolovachev.bitcoinpriceindexer.model.Historical;
import com.agolovachev.bitcoinpriceindexer.utils.DateTimeUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class DefaultApiClient implements ApiClient {
    private static final String BASE_HISTORICAL = "https://api.coindesk.com/v1/bpi/historical/close.json";
    private static final String CURRENT_PRICE = "https://api.coindesk.com/v1/bpi/currentprice.json";

    private static final String WEEK = "week";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    private static final String CURRENCY_KZT = "&currency=KZT";
    private static final String CURRENCY_USD = "&currency=USD";
    private static final String CURRENCY_EUR = "&currency=EUR";

    private OkHttpClient mClient;
    Gson mGson;

    public DefaultApiClient() {
        mClient = new OkHttpClient();
        mGson = new Gson();
    }

    @Nullable
    @Override
    public Historical getHistoricalForWeek() {
        Request request = getHistoricalRequestForPeriod(WEEK);
        Response response = getResponse(request);
        if (response == null) {
            return null;
        }

        String responseBody = getResponseBody(response);
        if (responseBody == null) {
            return null;
        }

        return mGson.fromJson(responseBody, Historical.class);
    }

    @Nullable
    @Override
    public Historical getHistoricalForMonth() {
        Request request = getHistoricalRequestForPeriod(MONTH);
        Response response = getResponse(request);
        if (response == null) {
            return null;
        }

        String responseBody = getResponseBody(response);
        if (responseBody == null) {
            return null;
        }

        return mGson.fromJson(responseBody, Historical.class);
    }

    @Nullable
    @Override
    public Historical getHistoricalForYear() {
        Request request = getHistoricalRequestForPeriod(YEAR);
        Response response = getResponse(request);
        if (response == null) {
            return null;
        }

        String responseBody = getResponseBody(response);
        if (responseBody == null) {
            return null;
        }

        return mGson.fromJson(responseBody, Historical.class);
    }

    @Nullable
    @Override
    public CurrentPrice getCurrentPrice() throws IOException {
        Request request = getCurrentPriceRequest();
        Response response = getResponse(request);
        if(response == null) {
            return null;
        }

        String responseBody = getResponseBody(response);
        if (responseBody == null) {
            return null;
        }

        return mGson.fromJson(responseBody, CurrentPrice.class);
    }

    @Nullable
    @Override
    public CurrentPrice getCurrentPriceForCurrency(CurrencyCode neededCurrency) {
        Request request = getCurrentPriceRequestForCurrency(neededCurrency);
        Response response = getResponse(request);
        if(response == null) {
            return null;
        }

        String responseBody = getResponseBody(response);
        if (responseBody == null) {
            return null;
        }

        return mGson.fromJson(responseBody, CurrentPrice.class);
    }

    private Request getHistoricalRequestForPeriod(String period) {
        String formPeriod;
        switch (period) {
            case WEEK:
                formPeriod = BASE_HISTORICAL.concat(DateTimeUtils.formQueryForWeek());
                break;
            case MONTH:
                formPeriod = BASE_HISTORICAL.concat(DateTimeUtils.formQueryForMonth());
                break;
            case YEAR:
                formPeriod = BASE_HISTORICAL.concat(DateTimeUtils.formQueryForYear());
                break;
            default: formPeriod = "";
        }
        //TODO Добавить возможность выбирать валюту (например, через паттерн Builder)
        formPeriod = formPeriod.concat(CURRENCY_KZT);

        return new Request.Builder()
                .url(formPeriod)
                .build();
    }

    private Request getHistoricalRequestForCurrency(String url, CurrencyCode currencyCode) {
        String formCurrency;
        switch (currencyCode) {
            case USD:
                formCurrency = url.concat(CURRENCY_USD);
                break;
            case EUR:
                formCurrency = url.concat(CURRENCY_EUR);
                break;
            case KZT:
                formCurrency = url.concat(CURRENCY_KZT);
                break;
            default: formCurrency = "";
        }

        return new Request.Builder()
                .url(formCurrency)
                .build();
    }

    private Request getCurrentPriceRequestForCurrency(CurrencyCode currencyCode) {
        String formCurrency;
        switch (currencyCode) {
            case EUR:
                formCurrency = CURRENT_PRICE.replace(".json","/EUR.json");
                break;
            case KZT:
                formCurrency = CURRENT_PRICE.replace(".json","/KZT.json");
                break;
            default: formCurrency = "";
        }

        return new Request.Builder()
                .url(formCurrency)
                .build();
    }

    private Request getCurrentPriceRequest() {
        return new Request.Builder()
                .url(CURRENT_PRICE)
                .build();
    }

    private Response getResponse(Request request) {
        try {
            return  mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getResponseBody(Response response) {
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
