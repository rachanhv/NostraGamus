package com.test.gambit.rest;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class AppErrorResponse extends JSONObject {

    private Throwable throwable;

    @SerializedName("message")
    private String message;

    @SerializedName("ex")
    private String ex;

    @SerializedName("code")
    private String code;

    public String getErrorMessage() {
        return message;
    }

    public String getEx() {
        return ex;
    }

    public String getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

