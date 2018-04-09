package com.agolovachev.bitcoinpriceindexer.bitcointransactions;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agolovachev.bitcoinpriceindexer.R;
import com.agolovachev.bitcoinpriceindexer.model.BitcoinTransaction;

import java.time.Instant;

/**
 * A simple {@link Fragment} subclass.
 */
public class BitcoinTransactionFragment extends Fragment {
    private static final String BITCOIN_TRANSACTION = "Bitcoin transaction";
    private BitcoinTransaction mBitcoinTransaction;

    TextView mDate;
    TextView mTid;
    TextView mPrice;
    TextView mType;
    TextView mAmount;

    public BitcoinTransactionFragment() {
        // Required empty public constructor
    }

    public static BitcoinTransactionFragment newInstance(Parcelable bitcoinTransaction) {
        BitcoinTransactionFragment fragment = new BitcoinTransactionFragment();
        Bundle args = new Bundle();
        args.putParcelable(BITCOIN_TRANSACTION, bitcoinTransaction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mBitcoinTransaction = getArguments().getParcelable(BITCOIN_TRANSACTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bitcoin_transaction, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mDate = view.findViewById(R.id.fragment_bitcoin_transaction_date);
        mTid = view.findViewById(R.id.fragment_bitcoin_transaction_tid);
        mPrice = view.findViewById(R.id.fragment_bitcoin_transaction_price);
        mType = view.findViewById(R.id.fragment_bitcoin_transaction_type);
        mAmount = view.findViewById(R.id.fragment_bitcoin_transaction_amount);

        if (mBitcoinTransaction != null) {
            bindBitcoinTransaction();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void bindBitcoinTransaction() {
        String date = getResources().getString(R.string.fragment_bitcoin_transaction_date_text) +
                Instant.ofEpochMilli(mBitcoinTransaction.getDate());
        mDate.setText(date);

        String tid = getResources().getString(R.string.fragment_bitcoin_transaction_tid_text) +
                mBitcoinTransaction.getTid();
        mTid.setText(tid);

        String price = getResources().getString(R.string.fragment_bitcoin_transaction_price_text) +
                mBitcoinTransaction.getPrice();
        mPrice.setText(price);

        String type = getResources().getString(R.string.fragment_bitcoin_transaction_type_text) +
                (mBitcoinTransaction.getType() == 1 ? "sell" : "buy");
        mType.setText(type);

        String amount = getResources().getString(R.string.fragment_bitcoin_transaction_amount_text) +
                mBitcoinTransaction.getAmount();
        mAmount.setText(amount);
    }
}
