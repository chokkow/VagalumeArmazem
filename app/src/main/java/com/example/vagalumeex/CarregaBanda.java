package com.example.vagalumeex;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class CarregaBanda extends AsyncTaskLoader<String> {

    private String mQueryString;
    CarregaBanda (Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return Service.BuscaBandas(mQueryString);
    }
}

