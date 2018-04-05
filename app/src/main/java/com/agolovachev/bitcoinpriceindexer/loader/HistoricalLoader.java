package com.agolovachev.bitcoinpriceindexer.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.agolovachev.bitcoinpriceindexer.Historical.HistoricalRepository;

import java.io.IOException;
import java.util.Map;

public class HistoricalLoader extends AsyncTaskLoader<Map<String, Float>> {
    HistoricalRepository mRepository;

    public HistoricalLoader(Context context,HistoricalRepository repository) {
        super(context);
        mRepository = repository;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Map<String, Float> loadInBackground() {
        try {
            return mRepository.getHistoricalForWeek();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
