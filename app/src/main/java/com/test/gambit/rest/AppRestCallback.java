package com.test.gambit.rest;

import android.support.annotation.NonNull;

import retrofit2.Call;

public interface AppRestCallback<T> {

    void onAppSuccessResponse(Call<T> call, @NonNull T response);

    void onAppFailureResponse(Call<T> call, AppErrorResponse response);

    void onAppFailureResponseWithErrorMessage(Call<T> call, @NonNull T response);

    void onAppNullResponse(Call<T> call, int response);

    void onServerFailed(Call<T> call, String message);

}

