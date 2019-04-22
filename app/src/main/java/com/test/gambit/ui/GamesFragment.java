package com.test.gambit.ui;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.gambit.MainActivity;
import com.test.gambit.R;
import com.test.gambit.databinding.FragmentGamesBinding;
import com.test.gambit.gameremote.GamesApiInterface;
import com.test.gambit.gameremote.GamesClientCallback;
import com.test.gambit.gameremote.GamesFragmentService;
import com.test.gambit.gameremote.GamesFragmentRepository;
import com.test.gambit.model.Games;
import com.test.gambit.rest.Repository;

import java.io.File;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class GamesFragment extends Fragment {
    FragmentGamesBinding gamesBinding;
    File cacheFile;
    GamesAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    GamesFragmentService gamesFragmentService;
    GamesApiInterface gamesApiInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        gamesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_games, container, false);
        View view = gamesBinding.getRoot();
        getGames();
        return view;
    }

    private void getGames() {
        cacheFile = new File(getActivity().getCacheDir(), "responses");
        gamesApiInterface = new Repository<GamesApiInterface>().createApiService(GamesApiInterface.class, cacheFile);
        gamesFragmentService = new GamesFragmentRepository();

        gamesFragmentService.getAllGames(gamesApiInterface,
                new GamesClientCallback() {
                    @Override
                    public void onSuccess(Games getAllGamesResponseModel) {

                        if (getActivity() != null) {
                            gamesBinding.gamesRecyclerView.setHasFixedSize(true);
                            linearLayoutManager = new LinearLayoutManager(getActivity());
                            gamesBinding.gamesRecyclerView.setLayoutManager(linearLayoutManager);
                            adapter = new GamesAdapter(
                                    Objects.requireNonNull(getActivity()),
                                    getAllGamesResponseModel.getData());
                            gamesBinding.gamesRecyclerView.setAdapter(adapter);
                            ((MainActivity) Objects.requireNonNull(getActivity())).updateTabText(1, String.valueOf(getAllGamesResponseModel.getData().size()));

                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
