package com.test.gambit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Games {

    @SerializedName("data")
    @Expose
    private List<GameData> data = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<GameData> getData() {
        return data;
    }

    public void setData(List<GameData> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}