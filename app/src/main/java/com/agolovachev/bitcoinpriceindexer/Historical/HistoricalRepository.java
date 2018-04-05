package com.agolovachev.bitcoinpriceindexer.Historical;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.Map;

public interface HistoricalRepository {
    @Nullable
    Map<String, Float> getHistoricalForWeek() throws IOException;
}
