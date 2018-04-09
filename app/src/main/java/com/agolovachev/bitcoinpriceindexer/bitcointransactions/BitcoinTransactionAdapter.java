package com.agolovachev.bitcoinpriceindexer.bitcointransactions;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agolovachev.bitcoinpriceindexer.R;
import com.agolovachev.bitcoinpriceindexer.model.BitcoinTransaction;

import java.util.List;

public class BitcoinTransactionAdapter extends RecyclerView.Adapter<BitcoinTransactionsViewHolder> {
    private List<BitcoinTransaction> mList;
    private Resources mResources;

    public BitcoinTransactionAdapter(List<BitcoinTransaction> data) {
        mList = data;
    }

    @Override
    public BitcoinTransactionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_bitcoin_transaction, parent, false);

        mResources = parent.getResources();
        return new BitcoinTransactionsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BitcoinTransactionsViewHolder holder, int position) {
        holder.bind(mList.get(position), mResources);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void update(List<BitcoinTransaction> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public BitcoinTransaction getBitcoinTransaction(int position) {
        return mList.get(position);
    }
}
