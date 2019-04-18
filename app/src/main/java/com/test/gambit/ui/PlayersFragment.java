package com.test.gambit.ui;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.gambit.R;
import com.test.gambit.model.PlayerData;
import com.test.gambit.model.PlayerTeam;
import com.test.gambit.model.Players;
import com.test.gambit.utils.Global;
import com.test.gambit.viewmodel.PlayersFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment {
    PlayersAdapter adapter;
    @BindView(R.id.myRecyclerView)
    RecyclerView recyclerView;
    Players playersList;
    List<PlayerData> playerDataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PlayersFragmentViewModel model = ViewModelProviders.of(this).get(PlayersFragmentViewModel.class);
        model.getPlayers().observe(this, new Observer<List<PlayerData>>() {
            @Override
            public void onChanged(@Nullable List<PlayerData> playerData) {
                playerDataList = playerData;

                Toast.makeText(getActivity(), String.valueOf(playerDataList.size()), Toast.LENGTH_SHORT).show();
                setRecyclerview(playerDataList);
            }
        });

        /*Call<PlayerTeam> call = Global.apiService.getTeam();
        call.enqueue(new Callback<PlayerTeam>() {
            @Override
            public void onResponse(Call<PlayerTeam> call, Response<PlayerTeam> response) {
                PlayerTeam playerTeam = new PlayerTeam();
                playerTeam = response.body();
                Log.i("PlayersFragment", playerTeam.getName() + " ");
            }

            @Override
            public void onFailure(Call<PlayerTeam> call, Throwable t) {
                Log.i("PlayersFragment", "");
            }
        });*/
    }

    public void setRecyclerview(List<PlayerData> list) {
        adapter = new PlayersAdapter(getActivity(), list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
