package com.test.gambit.playerremote;

import com.test.gambit.model.Players;

import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PlayersFragmentRepository implements PlayersFragmentService {

    @Override
    public void getAllPlayers(int pageNumber, int perPage, PlayersApiInterface playersApiInterface, PlayersClientCallback playersClientCallback) {
        playersApiInterface.getAllPlayers(pageNumber, perPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<Players>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<Players> getAllPlayersResponseModelResponse) {
                        if (getAllPlayersResponseModelResponse.raw().networkResponse() != null &&
                                getAllPlayersResponseModelResponse.isSuccessful()||
                                getAllPlayersResponseModelResponse.code() == HttpsURLConnection.HTTP_OK ||
                                getAllPlayersResponseModelResponse.code() == HttpURLConnection.HTTP_NOT_MODIFIED) {
                            playersClientCallback.onResponse(getAllPlayersResponseModelResponse.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        playersClientCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getAllSearchPlayers(String search, PlayersApiInterface playersApiInterface, PlayersSearchCallback playersSearchCallback) {

        playersApiInterface.getAllSearchPlayers(search)

                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .debounce(5, TimeUnit.SECONDS)
                .distinctUntilChanged()
                .subscribe(new Observer<Response<Players>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<Players> getAllPlayersResponseModelResponse) {

                        if (getAllPlayersResponseModelResponse.raw().networkResponse()!=null &&
                                getAllPlayersResponseModelResponse.raw().networkResponse().isSuccessful() ||
                                getAllPlayersResponseModelResponse.code() == HttpURLConnection.HTTP_OK ||
                                getAllPlayersResponseModelResponse.code() == HttpURLConnection.HTTP_NOT_MODIFIED) {
                            playersSearchCallback.onResponse(getAllPlayersResponseModelResponse.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        playersSearchCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
