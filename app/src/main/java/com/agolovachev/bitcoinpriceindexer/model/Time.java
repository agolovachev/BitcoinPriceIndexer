package com.agolovachev.bitcoinpriceindexer.model;

import com.google.gson.annotations.SerializedName;

public class Time {
    @SerializedName("updated")
    private String updated;
    @SerializedName("updatedISO")
    private String updatedISO;
    @SerializedName("updateduk")
    private String updateduk;

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdatedISO() {
        return updatedISO;
    }

    public void setUpdatedISO(String updatedISO) {
        this.updatedISO = updatedISO;
    }

    public String getUpdateduk() {
        return updateduk;
    }

    public void setUpdateduk(String updateduk) {
        this.updateduk = updateduk;
    }
}
