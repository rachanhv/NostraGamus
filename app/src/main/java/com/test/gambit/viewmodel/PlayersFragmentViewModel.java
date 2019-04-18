package com.test.gambit.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.test.gambit.model.PlayerData;
import com.test.gambit.model.Players;
import com.test.gambit.rest.AppErrorResponse;
import com.test.gambit.rest.AppRestCallback;
import com.test.gambit.rest.RestResponse;
import com.test.gambit.utils.Global;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayersFragmentViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<PlayerData>> playerList;
    Players players;

    //we will call this method to get the data
    public LiveData<List<PlayerData>> getPlayers() {
        //if the list is null
        if (playerList == null) {
            playerList = new MutableLiveData<List<PlayerData>>();
            //we will load it asynchronously from server in this method
            loadPlayersData();
        }

        //finally we will return the list
        return playerList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadPlayersData() {

        Call<Players> call = Global.apiService.getPlayers();
        call.enqueue(new RestResponse(new AppRestCallback<Players>() {
            @Override
            public void onAppSuccessResponse(Call<Players> call, @NonNull Players response) {
                players = new Players();
                players = response;
                playerList.postValue(players.getData());
            }

            @Override
            public void onAppFailureResponse(Call<Players> call, AppErrorResponse response) {

            }

            @Override
            public void onAppFailureResponseWithErrorMessage(Call<Players> call, @NonNull Players response) {

            }

            @Override
            public void onAppNullResponse(Call<Players> call, int response) {

            }

            @Override
            public void onServerFailed(Call<Players> call, String message) {

            }
        }));
    }
}
