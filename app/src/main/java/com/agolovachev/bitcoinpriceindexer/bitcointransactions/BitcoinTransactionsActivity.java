package com.agolovachev.bitcoinpriceindexer.bitcointransactions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.agolovachev.bitcoinpriceindexer.R;
import com.agolovachev.bitcoinpriceindexer.loader.BitcoinTransactionsLoader;
import com.agolovachev.bitcoinpriceindexer.model.BitcoinTransaction;

import java.util.ArrayList;
import java.util.List;

public class BitcoinTransactionsActivity extends AppCompatActivity implements RecyclerTouchListener.OnClickListener {
    private static final int TRANSACTION_LOADER_ID = 100;

    BitcoinTransactionsRepository mRepository;
    RecyclerView mRecyclerView;
    BitcoinTransactionsLoader mTransactionsLoader;
    BitcoinTransactionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin_transactions);
        mRepository = new DefaultBitcoinTransactionRepository();
        mTransactionsLoader = new BitcoinTransactionsLoader(this, mRepository);
        getSupportLoaderManager().initLoader(TRANSACTION_LOADER_ID, null, transactionsCallback);
        mRecyclerView = findViewById(R.id.activity_bitcoin_transactions_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BitcoinTransactionAdapter(new ArrayList<BitcoinTransaction>());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, this));
    }

    private LoaderManager.LoaderCallbacks<List<BitcoinTransaction>> transactionsCallback =
            new LoaderManager.LoaderCallbacks<List<BitcoinTransaction>>() {
                @Override
                public Loader<List<BitcoinTransaction>> onCreateLoader(int i, Bundle bundle) {
                    return mTransactionsLoader;
                }

                @Override
                public void onLoadFinished(Loader<List<BitcoinTransaction>> loader, @Nullable List<BitcoinTransaction> bitcoinTransactions) {
                    getSupportLoaderManager().destroyLoader(TRANSACTION_LOADER_ID);
                    if (bitcoinTransactions == null) {
                        return;
                    }

                    mAdapter.update(bitcoinTransactions);
                }

                @Override
                public void onLoaderReset(Loader<List<BitcoinTransaction>> loader) { }
            };

    @Override
    public void onClick(View view, int position) {
        Fragment fragment = BitcoinTransactionFragment.newInstance(mAdapter.getBitcoinTransaction(position));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_bitcoin_transaction_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
