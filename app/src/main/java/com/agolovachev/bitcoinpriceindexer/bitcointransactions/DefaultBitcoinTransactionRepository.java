package com.agolovachev.bitcoinpriceindexer.bitcointransactions;

import android.support.annotation.Nullable;

import com.agolovachev.bitcoinpriceindexer.api.ApiClient;
import com.agolovachev.bitcoinpriceindexer.api.DefaultApiClient;
import com.agolovachev.bitcoinpriceindexer.model.BitcoinTransaction;

import java.util.List;

public class DefaultBitcoinTransactionRepository implements BitcoinTransactionsRepository {
    private ApiClient mApiClient;

    public DefaultBitcoinTransactionRepository() {
        mApiClient = DefaultApiClient.getInstance();
    }

    @Nullable
    @Override
    public List<BitcoinTransaction> getLastTransactions() {
        return mApiClient.getLastTransactions();
    }
}
