package com.agolovachev.bitcoinpriceindexer.bitcointransactions;

import android.support.annotation.Nullable;

import com.agolovachev.bitcoinpriceindexer.api.RetrofitApiClient;
import com.agolovachev.bitcoinpriceindexer.model.BitcoinTransaction;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DefaultBitcoinTransactionRepository implements BitcoinTransactionsRepository {
    private RetrofitApiClient mApiClient;

    public DefaultBitcoinTransactionRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        mApiClient = retrofit.create(RetrofitApiClient.class);
    }

    @Nullable
    @Override
    public List<BitcoinTransaction> getLastTransactions() {
        Response<List<BitcoinTransaction>> response = null;
        try {
            response = mApiClient.getLastTransactions().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response == null) {
            return null;
        }

        return response.body();
    }
}
