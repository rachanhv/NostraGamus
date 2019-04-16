package com.test.gambit;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.gambit.model.Players;
import com.test.gambit.viewmodel.PlayersFragmentViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment {
    PlayersAdapter adapter;
    @BindView(R.id.myRecyclerView)
    RecyclerView recyclerView;

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

        model.getPlayers().observe(this, new Observer<List<Players>>() {
            @Override
            public void onChanged(@Nullable List<Players> heroList) {
                Toast.makeText(getActivity(), heroList.size(), Toast.LENGTH_SHORT).show();
                setRecyclerview(heroList);
            }
        });
    }

    public void setRecyclerview(List<Players> list) {
        adapter = new PlayersAdapter(getActivity(), list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
