package com.agolovachev.bitcoinpriceindexer.model;

import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("code")
    private CurrencyCode mCode;
    @SerializedName("rate")
    private String mRate;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("rate_float")
    private float mRateFloat;

    public CurrencyCode getCode() {
        return mCode;
    }

    public void setCode(CurrencyCode code) {
        mCode = code;
    }

    public String getRate() {
        return mRate;
    }

    public void setRate(String rate) {
        mRate = rate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public float getRateFloat() {
        return mRateFloat;
    }

    public void setRateFloat(float rateFloat) {
        mRateFloat = rateFloat;
    }
}
