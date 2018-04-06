package com.agolovachev.bitcoinpriceindexer.api;

import android.support.annotation.Nullable;

import com.agolovachev.bitcoinpriceindexer.model.CurrencyCode;
import com.agolovachev.bitcoinpriceindexer.model.CurrentPrice;
import com.agolovachev.bitcoinpriceindexer.model.Historical;

import java.io.IOException;

public interface ApiClient {
    @Nullable
    Historical getHistoricalForWeek();

    @Nullable
    Historical getHistoricalForMonth();

    @Nullable
    Historical getHistoricalForYear();

    @Nullable
    CurrentPrice getCurrentPrice() throws IOException;

    @Nullable
    CurrentPrice getCurrentPriceForCurrency(CurrencyCode neededCurrency);
}
