package com.agolovachev.bitcoinpriceindexer.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Historical {
    @SerializedName("bpi")
    private Map<String, Float> mBpi;
    @SerializedName("disclaimer")
    private String mDisclaimer;
    @SerializedName("time")
    private Time mTime;

    public Historical() {
        mBpi = new HashMap<>();
    }

    public Map<String, Float> getBpi() {
        return mBpi;
    }

    public void setBpi(Map<String, Float> bpi) {
        mBpi = bpi;
    }

    public String getDisclaimer() {
        return mDisclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        mDisclaimer = disclaimer;
    }

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time time) {
        mTime = time;
    }
}
