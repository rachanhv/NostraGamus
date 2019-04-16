package com.test.gambit.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppResponse {

    @Expose(deserialize = false, serialize = false)
    private Throwable throwable;

    @SerializedName("message")
    private String message;

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getMessage() {
        return message;
    }

}