package com.test.gambit.playerremote;

import com.test.gambit.model.Players;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlayersApiInterface {

    @GET("players")
    Observable<Response<Players>> getAllPlayers(@Query("page") int page, @Query("per_page") int perPage);

    @GET("players")
    Observable<Response<Players>> getAllSearchPlayers(@Query("search") String search);
}
