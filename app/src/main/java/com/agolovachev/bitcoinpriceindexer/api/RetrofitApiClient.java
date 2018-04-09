package com.agolovachev.bitcoinpriceindexer.api;

import com.agolovachev.bitcoinpriceindexer.model.BitcoinTransaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiClient {
    String BASE_URL = "https://www.bitstamp.net/api/";

    @GET("transactions")
    Call<List<BitcoinTransaction>> getLastTransactions();
}
