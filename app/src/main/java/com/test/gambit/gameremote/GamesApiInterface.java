package com.test.gambit.gameremote;

import com.test.gambit.model.Games;
import com.test.gambit.model.Players;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GamesApiInterface {

    @GET("games")
    Observable<Response<Games>> getAllGames();
}
