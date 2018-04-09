package com.agolovachev.bitcoinpriceindexer.bitcointransactions;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.agolovachev.bitcoinpriceindexer.R;
import com.agolovachev.bitcoinpriceindexer.model.BitcoinTransaction;

public class BitcoinTransactionsViewHolder extends RecyclerView.ViewHolder {
    private TextView mPriceTextView;
    private TextView mAmountTextView;

    public BitcoinTransactionsViewHolder(View itemView) {
        super(itemView);
        mPriceTextView = itemView.findViewById(R.id.item_bitcoin_transaction_price_text_view);
        mAmountTextView = itemView.findViewById(R.id.item_bitcoin_transaction_amount_text_view);
    }

    public void bind(BitcoinTransaction transaction, Resources resources) {
        String price = resources.getString(R.string.activity_bitcoin_transaction_price_text) + transaction.getPrice();
        String amount = resources.getString(R.string.activity_bitcoin_transaction_amount_text) + transaction.getAmount();

        mPriceTextView.setText(price);
        mAmountTextView.setText(amount);
    }
}
