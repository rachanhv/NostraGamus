package com.test.gambit.rest;

import android.arch.lifecycle.LiveData;

import com.test.gambit.model.Players;

import retrofit2.http.GET;

public interface ApiInterface {

    //Players
    @GET("players")
    LiveData<Players> getPlayers();
    //dfsadfs

}
