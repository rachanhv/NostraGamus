package com.test.gambit.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.gambit.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository<T> {

    private OkHttpClient.Builder httpClient = null;
    private Request original = null;
    private Request request = null;
    private File cacheDirectory = null;
    private Cache cache = null;
    CacheControl cacheControl;


    public T createApiService(Class<T> remoteDomain, File cacheDirectory) {
        this.cacheDirectory = cacheDirectory;
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor(BuildConfig.DEBUG));
        return makeRemoteService(okHttpClient, makeGson(), remoteDomain);
    }

    private T makeRemoteService(OkHttpClient okHttpClient, Gson gson, Class<T> remoteDomain) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(remoteDomain);
    }
    private OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        try {
            cache = new Cache(cacheDirectory, 10 * 1024 * 1024);
            cacheControl = new CacheControl.Builder()
                    .maxAge(1, TimeUnit.MINUTES)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        httpClient = new OkHttpClient.Builder();
        httpClient.cache(cache);
        httpClient.addInterceptor(httpLoggingInterceptor);

        httpClient.addInterceptor(chain -> {
            if (original == null) {
                original = chain.request();
            }

            if (request == null) {
                request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Cache-Control", cacheControl.toString())
                        .method(original.method(), original.body())
                        .build();
            }

            return chain.proceed(request);
        });


        httpClient.connectTimeout(20, TimeUnit.SECONDS);
        httpClient.readTimeout(20, TimeUnit.SECONDS);
        httpClient.writeTimeout(20, TimeUnit.SECONDS);
        return httpClient.build();

    }

    private Gson makeGson() {
        return new GsonBuilder()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    private HttpLoggingInterceptor makeLoggingInterceptor(boolean isDebug) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(isDebug ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

}
