package com.agolovachev.bitcoinpriceindexer.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.agolovachev.bitcoinpriceindexer.Historical.HistoricalRepository;
import com.agolovachev.bitcoinpriceindexer.model.Currency;
import com.agolovachev.bitcoinpriceindexer.model.CurrencyCode;

import java.io.IOException;
import java.util.Map;

public class CurrentPriceLoader extends AsyncTaskLoader<Map<CurrencyCode, Currency>> {
    private HistoricalRepository mRepository;

    public CurrentPriceLoader(Context context, HistoricalRepository repository) {
        super(context);
        mRepository = repository;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        super.onStartLoading();
    }

    @Override
    public Map<CurrencyCode, Currency> loadInBackground() {
        try {
            return  mRepository.getCurrentPrice();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
