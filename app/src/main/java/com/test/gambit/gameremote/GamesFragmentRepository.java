package com.test.gambit.gameremote;

import android.util.Log;

import com.test.gambit.model.Games;

import java.net.HttpURLConnection;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class GamesFragmentRepository implements GamesFragmentService {
    @Override
    public void getAllGames(GamesApiInterface gamesApiInterface, GamesClientCallback gamesClientCallback) {
        gamesApiInterface.getAllGames()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<Games>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<Games> playersResponse) {

                        if(playersResponse.raw().networkResponse()!=null &&
                                playersResponse.raw().networkResponse().isSuccessful() ||
                                playersResponse.code() == HttpURLConnection.HTTP_OK ||
                                playersResponse.code() == HttpURLConnection.HTTP_NOT_MODIFIED){

                            gamesClientCallback.onSuccess(playersResponse.body());
                        }
                        else if(playersResponse.raw().cacheResponse()!=null){
                            Log.d("#### " , "response from cache");
                            gamesClientCallback.onError("#### response from cache");
                            gamesClientCallback.onSuccess(playersResponse.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        gamesClientCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
