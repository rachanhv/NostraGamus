package com.test.gambit.utils;

import android.content.Context;

import com.test.gambit.rest.ApiClient;
import com.test.gambit.rest.ApiInterface;

public class Global {
    public static Context mContext;

    public Global(Context context) {
        mContext = context;
    }

    public static ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);

}
