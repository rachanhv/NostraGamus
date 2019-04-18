package com.test.gambit.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.test.gambit.rest.ApiClient;
import com.test.gambit.rest.ApiInterface;

public class Global {
    public static Context mContext;

    public Global(Context context) {
        mContext = context;
    }

    public static ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);

    //Load image using Picassa
    public static void loadPicasa(String imageUrl, ImageView imageView) {
        Picasso.with(mContext)
                .load(imageUrl)
                .noPlaceholder()
                .into(imageView);
    }
}
