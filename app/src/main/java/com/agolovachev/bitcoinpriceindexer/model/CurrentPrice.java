package com.agolovachev.bitcoinpriceindexer.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CurrentPrice {
    @SerializedName("time")
    private Time time;
    @SerializedName("disclaimer")
    private String disclaimer;
    @SerializedName("chartName")
    private String chartName;
    @SerializedName("bpi")
    private Map<CurrencyCode, Currency> bpi;
    
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public Map<CurrencyCode, Currency> getBpi() {
        return bpi;
    }

    public void setBpi(Map<CurrencyCode, Currency> bpi) {
        this.bpi = bpi;
    }
}
