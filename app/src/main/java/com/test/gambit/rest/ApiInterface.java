package com.test.gambit.rest;


import com.test.gambit.model.PlayerTeam;
import com.test.gambit.model.Players;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    //Players
    @GET("players?")
    Call<Players> getPlayers();

    //Team
    @GET("teams/14")
    Call<PlayerTeam> getTeam();

}
