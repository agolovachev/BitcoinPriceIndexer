package com.agolovachev.bitcoinpriceindexer.model;

import com.google.gson.annotations.SerializedName;

public class BitcoinTransaction {
    @SerializedName("date")
    private long mDate;
    @SerializedName("tid")
    private long mData;
    @SerializedName("price")
    private float mPrice;
    @SerializedName("type")
    private int mIsSell;
    @SerializedName("amount")
    private double mAmount;

    public long getDate() {
        return mDate;
    }

    public long getData() {
        return mData;
    }

    public float getPrice() {
        return mPrice;
    }

    public boolean isSell() {
        return mIsSell == 1;
    }

    public double getAmount() {
        return mAmount;
    }
}
