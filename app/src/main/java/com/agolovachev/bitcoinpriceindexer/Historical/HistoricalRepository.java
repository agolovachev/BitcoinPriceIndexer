package com.agolovachev.bitcoinpriceindexer.Historical;

import android.support.annotation.Nullable;

import com.agolovachev.bitcoinpriceindexer.model.Currency;
import com.agolovachev.bitcoinpriceindexer.model.CurrencyCode;

import java.io.IOException;
import java.util.Map;

public interface HistoricalRepository {
    @Nullable
    Map<String, Float> getHistoricalForWeek() throws IOException;

    @Nullable
    Map<CurrencyCode, Currency> getCurrentPrice() throws IOException;
}
