package com.agolovachev.bitcoinpriceindexer.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class CurrentPriceLoader extends AsyncTaskLoader {

    public CurrentPriceLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }
}
