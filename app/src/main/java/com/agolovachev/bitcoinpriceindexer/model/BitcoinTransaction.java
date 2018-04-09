package com.agolovachev.bitcoinpriceindexer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BitcoinTransaction implements Parcelable {
    @SerializedName("date")
    private long mDate;
    @SerializedName("tid")
    private long mTid;
    @SerializedName("price")
    private float mPrice;
    @SerializedName("type")
    private int mType;
    @SerializedName("amount")
    private double mAmount;

    protected BitcoinTransaction(Parcel in) {
        mDate = in.readLong();
        mTid = in.readLong();
        mPrice = in.readFloat();
        mType = in.readInt();
        mAmount = in.readDouble();
    }

    public static final Creator<BitcoinTransaction> CREATOR = new Creator<BitcoinTransaction>() {
        @Override
        public BitcoinTransaction createFromParcel(Parcel in) {
            return new BitcoinTransaction(in);
        }

        @Override
        public BitcoinTransaction[] newArray(int size) {
            return new BitcoinTransaction[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mDate);
        parcel.writeLong(mTid);
        parcel.writeFloat(mPrice);
        parcel.writeInt(mType);
        parcel.writeDouble(mAmount);
    }

    public long getDate() {
        return mDate;
    }

    public long getTid() {
        return mTid;
    }

    public float getPrice() {
        return mPrice;
    }

    public int getType() {
        return mType;
    }

    public double getAmount() {
        return mAmount;
    }
}
