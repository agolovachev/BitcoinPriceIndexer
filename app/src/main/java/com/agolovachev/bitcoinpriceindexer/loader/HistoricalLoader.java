package com.agolovachev.bitcoinpriceindexer.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.agolovachev.bitcoinpriceindexer.Historical.HistoricalRepository;

import java.io.IOException;
import java.util.Map;

public class HistoricalLoader extends AsyncTaskLoader<Map<String, Float>> {
    private HistoricalRepository mRepository;

    public HistoricalLoader(Context context, HistoricalRepository repository) {
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
            switch (getId()) {
                case 0:
                    return mRepository.getHistoricalForWeek();
                case 1:
                    return mRepository.getHistoricalForMonth();
                case 2:
                    return mRepository.getHistoricalForYear();
                default:
                        return null;
            }
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }
}
