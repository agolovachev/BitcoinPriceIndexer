package com.agolovachev.bitcoinpriceindexer.bitcointransactions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agolovachev.bitcoinpriceindexer.R;
import com.agolovachev.bitcoinpriceindexer.model.BitcoinTransaction;

import java.util.List;

public class BitcoinTranasctionAdapter extends RecyclerView.Adapter<BitcoinTransactionsViewHolder> {
    private List<BitcoinTransaction> mList;

    public BitcoinTranasctionAdapter(List<BitcoinTransaction> data) {
        mList = data;
    }

    @Override
    public BitcoinTransactionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_bitcoin_transaction, parent, false);

        return new BitcoinTransactionsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BitcoinTransactionsViewHolder holder, int position) {
        holder.bind(mList.get(position));
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
}
