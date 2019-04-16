package com.test.gambit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Players {
    @SerializedName("data")
    @Expose
    private List<PlayerData> data = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<PlayerData> getData() {
        return data;
    }

    public void setData(List<PlayerData> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}