package com.agolovachev.bitcoinpriceindexer.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.agolovachev.bitcoinpriceindexer.bitcointransactions.BitcoinTransactionsRepository;
import com.agolovachev.bitcoinpriceindexer.model.BitcoinTransaction;

import java.util.List;

public class BitcoinTransactionsLoader extends AsyncTaskLoader<List<BitcoinTransaction>> {
    private BitcoinTransactionsRepository mRepository;

    public BitcoinTransactionsLoader(Context context, BitcoinTransactionsRepository repository) {
        super(context);
        mRepository = repository;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        super.onStartLoading();
    }

    @Override
    public List<BitcoinTransaction> loadInBackground() {
        return mRepository.getLastTransactions();
    }
}
