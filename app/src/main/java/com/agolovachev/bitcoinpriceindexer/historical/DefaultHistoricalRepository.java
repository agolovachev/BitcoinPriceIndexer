package com.agolovachev.bitcoinpriceindexer.historical;

import android.support.annotation.Nullable;

import com.agolovachev.bitcoinpriceindexer.api.ApiClient;
import com.agolovachev.bitcoinpriceindexer.api.DefaultApiClient;
import com.agolovachev.bitcoinpriceindexer.model.Currency;
import com.agolovachev.bitcoinpriceindexer.model.CurrencyCode;
import com.agolovachev.bitcoinpriceindexer.model.CurrentPrice;
import com.agolovachev.bitcoinpriceindexer.model.Historical;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultHistoricalRepository implements HistoricalRepository {
    private ApiClient mApiClient;

    public DefaultHistoricalRepository() {
        mApiClient = new DefaultApiClient();
    }

    @Nullable
    @Override
    public Map<String, Float> getHistoricalForWeek() {
        Historical historical = mApiClient.getHistoricalForWeek();
        if (historical != null) {
            return getRatesPerPeriod(historical);
        }

        return null;
    }

    @Nullable
    @Override
    public Map<String, Float> getHistoricalForMonth() {
        Historical historical = mApiClient.getHistoricalForMonth();
        if (historical != null) {
            return getRatesPerPeriod(historical);
        }

        return null;
    }

    @Nullable
    @Override
    public Map<String, Float> getHistoricalForYear() {
        Historical historical = mApiClient.getHistoricalForYear();
        if (historical != null) {
            return getRatesPerPeriod(historical);
        }

        return null;
    }

    @Nullable
    @Override
    public Map<CurrencyCode, Currency> getCurrentPrice() throws IOException {
        Map<CurrencyCode, Currency> data = new HashMap<>();

        CurrentPrice currentPrice = mApiClient.getCurrentPrice();
        if (currentPrice != null) {
            data.putAll(getCurrentBpi(currentPrice));
        }

        CurrentPrice currentPriceForKzt = mApiClient.getCurrentPriceForCurrency(CurrencyCode.KZT);
        if (currentPriceForKzt != null) {
            data.putAll(getCurrentBpi(currentPriceForKzt));
        }

        return data;
    }

    private Map<CurrencyCode, Currency> getCurrentBpi(CurrentPrice currentPrice){
        return currentPrice.getBpi();
    }

    private Map<String, Float> getRatesPerPeriod(Historical historical) {
        return historical.getBpi();
    }
}
