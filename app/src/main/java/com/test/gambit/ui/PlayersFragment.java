package com.test.gambit.ui;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.gambit.MainActivity;
import com.test.gambit.R;
import com.test.gambit.databinding.FragmentPlayersBinding;
import com.test.gambit.model.PlayerData;
import com.test.gambit.model.Players;
import com.test.gambit.playerremote.PlayersApiInterface;
import com.test.gambit.playerremote.PlayersClientCallback;
import com.test.gambit.playerremote.PlayersSearchCallback;
import com.test.gambit.playerremote.PlayersFragmentService;
import com.test.gambit.playerremote.PlayersFragmentRepository;
import com.test.gambit.rest.Repository;
import com.test.gambit.utils.load.OnLoadMoreListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment {
    PlayersAdapter adapter;
    @BindView(R.id.myRecyclerView)
    RecyclerView recyclerView;
    Players playersList;
    List<PlayerData> playerDataList = new ArrayList<>();
    private FragmentPlayersBinding playersBinding;
    private LinearLayoutManager linearLayoutManager;
    private PlayersApiInterface playersApiInterface;
    private PlayersFragmentService playersFragmentService;

    private int totalPages = 0;
    private int currentPage = 0;
    private int nextPage = 0;
    private int totalCount = 0;
    private int pageNumber = 0;
    private int itemPerPage = 20;
    private File cacheFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        playersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_players, container, false);
        View view = playersBinding.getRoot();

        playersBinding.playersSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() > 2) {
                    cacheFile = new File(getActivity().getCacheDir(), "responses");
                    playersApiInterface = new Repository<PlayersApiInterface>().createApiService(PlayersApiInterface.class, cacheFile);
                    playersFragmentService = new PlayersFragmentRepository();
                    playersFragmentService.getAllSearchPlayers(s, playersApiInterface, new PlayersSearchCallback() {
                        @Override
                        public void onResponse(Players getAllPlayersResponseModel) {
                            if (getAllPlayersResponseModel.getData().size() > 0) {
                                if (getActivity() != null) {
                                    ((MainActivity) Objects.requireNonNull(getActivity())).updateTabText(0, String.valueOf(getAllPlayersResponseModel.getData().size()));
                                    adapter.notifyDataSetChanged();
                                    adapter.loadPlayerData(getAllPlayersResponseModel.getData());
                                    adapter.setLazyLoading(false);
                                    playersBinding.myRecyclerView.setAdapter(adapter);
                                }
                            }
                        }

                        @Override
                        public void onError(String errorMessage) {

                        }

                    });
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPlayers();

    }


    @Override
    public void onResume() {
        super.onResume();
        playersBinding.playersSearchView.onActionViewExpanded();
        playersBinding.playersSearchView.clearFocus();
        playersBinding.myRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        playersBinding.myRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PlayersAdapter(
                playersBinding.myRecyclerView,
                Objects.requireNonNull(getActivity()));

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (playerDataList != null && playerDataList.size() > 0) {
                    if (totalPages == currentPage) {

                    } else {
                        cacheFile = new File(getActivity().getCacheDir(), "responses");
                        playersApiInterface = new Repository<PlayersApiInterface>().createApiService(PlayersApiInterface.class, cacheFile);
                        playersFragmentService = new PlayersFragmentRepository();
                        playersFragmentService.getAllPlayers(
                                nextPage,
                                itemPerPage,
                                playersApiInterface,
                                new PlayersClientCallback() {
                                    @Override
                                    public void onResponse(Players getAllPlayersResponseModel) {
                                        if (getAllPlayersResponseModel.getData().size() > 0) {
                                            if (getActivity() != null) {
                                                totalPages = getAllPlayersResponseModel.getMeta().getTotalPages();
                                                currentPage = getAllPlayersResponseModel.getMeta().getCurrentPage();
                                                nextPage = getAllPlayersResponseModel.getMeta().getNextPage();
                                                itemPerPage = getAllPlayersResponseModel.getMeta().getPerPage();
                                                totalCount = getAllPlayersResponseModel.getMeta().getTotalCount();
                                                playerDataList.addAll(getAllPlayersResponseModel.getData());
                                                ((MainActivity) Objects.requireNonNull(getActivity())).updateTabText(0, String.valueOf(playerDataList.size()));
                                                adapter.notifyDataSetChanged();
                                                adapter.setLoaded();
                                                                                            }
                                        }
                                    }

                                    @Override
                                    public void onError(String errorMessage) {
                                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }

                }
            }
        });

    }

    private void getPlayers() {
        if (getActivity() != null)
        cacheFile = new File(getActivity().getCacheDir(), "responses");
        playersApiInterface = new Repository<PlayersApiInterface>().createApiService(PlayersApiInterface.class, cacheFile);
        playersFragmentService = new PlayersFragmentRepository();
        playersFragmentService.getAllPlayers(
                pageNumber,
                itemPerPage,
                playersApiInterface,
                new PlayersClientCallback() {
                    @Override
                    public void onResponse(Players players) {
                        if (getActivity() != null) {
                            totalPages = players.getMeta().getTotalPages();
                            currentPage = players.getMeta().getCurrentPage();
                            nextPage = players.getMeta().getNextPage();
                            itemPerPage = players.getMeta().getPerPage();
                            totalCount = players.getMeta().getTotalCount();

                            if (players.getData().size() > 0) {
                                playerDataList = players.getData();
                                adapter.loadPlayerData(players.getData());
                                adapter.setLazyLoading(true);
                                ((MainActivity) Objects.requireNonNull(getActivity())).updateTabText(0, String.valueOf(players.getData().size()));
                                playersBinding.myRecyclerView.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
}
