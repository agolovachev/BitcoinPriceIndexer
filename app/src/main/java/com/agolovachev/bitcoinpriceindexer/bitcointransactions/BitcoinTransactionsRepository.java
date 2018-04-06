package com.agolovachev.bitcoinpriceindexer.bitcointransactions;

import android.support.annotation.Nullable;

import com.agolovachev.bitcoinpriceindexer.model.BitcoinTransaction;

import java.util.List;

public interface BitcoinTransactionsRepository {
    @Nullable
    List<BitcoinTransaction> getLastTransactions();
}
