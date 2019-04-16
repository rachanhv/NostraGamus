package com.test.gambit.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.test.gambit.model.Players;
import com.test.gambit.rest.AppErrorResponse;
import com.test.gambit.rest.AppRestCallback;
import com.test.gambit.rest.RestResponse;
import com.test.gambit.utils.Global;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayersFragmentViewModel extends AndroidViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Players>>  playerList;

    public PlayersFragmentViewModel(@NonNull Application application) {
        super(application);

    }


    //we will call this method to get the data
    public LiveData<List<Players>> getPlayers() {
        //if the list is null
        if (playerList == null) {
            playerList = new MutableLiveData<List<Players>>();
            //we will load it asynchronously from server in this method
            loadPlayers();
        }

        //finally we will return the list
        return playerList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadPlayers() {

        Call<Players> call = Global.apiService.getPlayers();
        call.enqueue(new Callback<Players>() {
            @Override
            public void onResponse(Call<Players> call, Response<Players> response) {
                Log.i("PlayersFragment",response.toString());
            }

            @Override
            public void onFailure(Call<Players> call, Throwable t) {

            }
        });
      /*  call.enqueue(new RestResponse(new AppRestCallback<List<Players>>() {
            @Override
            public void onAppSuccessResponse(Call<List<Players>> call, @NonNull List<Players> response) {
                playerList.setValue(response);
                Log.i("PlayersFragment",response.toString());
            }

            @Override
            public void onAppFailureResponse(Call<List<Players>> call, AppErrorResponse response) {

            }

            @Override
            public void onAppFailureResponseWithErrorMessage(Call<List<Players>> call, @NonNull List<Players> response) {

            }

            @Override
            public void onAppNullResponse(Call<List<Players>> call, int response) {

            }

            @Override
            public void onServerFailed(Call<List<Players>> call, String message) {

            }
        }));*/


    }



}
