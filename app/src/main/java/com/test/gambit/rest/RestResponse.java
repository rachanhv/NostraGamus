package com.test.gambit.rest;

import android.util.Log;

import com.test.gambit.utils.Constants;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestResponse<T extends AppResponse> implements Callback<T> {

    private AppRestCallback<T> mCallback;
    private boolean isServerError;
    private AppErrorResponse error;
    private boolean isClientError;

    public RestResponse(AppRestCallback<T> callback) {
        this.mCallback = callback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null) {
            Log.d("errorCode", ""+response.code());
            if (response.isSuccessful() && response.body() != null && response.code() == Constants.HttpStatus.SUCCESS) {
                mCallback.onAppSuccessResponse(call, response.body());
            } else if(response.isSuccessful() && response.body()== null && response.code() == Constants.HttpStatus.SUCCESS){
                mCallback.onAppSuccessResponse(call, response.body());
            } else if (!response.isSuccessful() && response.code() <= Constants.HttpStatus.BAD_REQUEST && response.code() > Constants.HttpStatus.SERVER_ERROR) {
                mCallback.onAppNullResponse(call, response.code());
            } else if (!response.isSuccessful() && response.code() <= Constants.HttpStatus.SERVER_ERROR) {
                error = new AppErrorResponse();
                Log.i("response.code()", String.valueOf(response.code()));
                error.setMessage("Server failed, Please try later");
                mCallback.onAppFailureResponse(call, error);
                mCallback.onAppFailureResponseWithErrorMessage(call, response.body());
            }
        }
    }


    @Override
    public void onFailure(Call<T> call, Throwable error) {
        if (error instanceof IOException) {
            mCallback.onServerFailed(call, String.valueOf(error.getMessage()));
        }
    }
}

