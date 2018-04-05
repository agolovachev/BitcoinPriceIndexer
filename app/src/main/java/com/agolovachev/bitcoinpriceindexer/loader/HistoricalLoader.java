package com.agolovachev.bitcoinpriceindexer.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HistoricalLoader extends AsyncTaskLoader<Response> {
    private OkHttpClient mClient;
    private Request mRequest;

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();

    }

    public HistoricalLoader(Context context, OkHttpClient client, Request request) {
        super(context);
        mClient = client;
        mRequest = request;
    }

    @Override
    public Response loadInBackground() {
        try {
            Response response = mClient.newCall(mRequest).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
