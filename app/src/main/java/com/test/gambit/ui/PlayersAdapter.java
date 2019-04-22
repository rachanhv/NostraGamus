package com.test.gambit.ui;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.gambit.R;
import com.test.gambit.databinding.PlayerAdapterBinding;
import com.test.gambit.model.PlayerData;
import com.test.gambit.model.Players;
import com.test.gambit.utils.Global;
import com.test.gambit.utils.load.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    List<PlayerData> playersArrayList = new ArrayList<>();
    Context mContext;
    private Activity activity;

    private PlayerAdapterBinding adapterPlayersFragmentBinding;
    private LayoutInflater layoutInflater;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading = false;
    private boolean scrollSearch = true;

    private OnLoadMoreListener onLoadMoreListener;

    public PlayersAdapter(@NonNull RecyclerView recyclerView, @NonNull Activity activity) {
        this.activity = activity;


        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (scrollSearch) {
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }

            }
        });
    }

    public void loadPlayerData(@NonNull List<PlayerData> getAllPlayerInfo) {
        this.playersArrayList = getAllPlayerInfo;
    }

    public void setLazyLoading(boolean scrollSearch) {
        this.scrollSearch = scrollSearch;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public void setLoaded() {
        isLoading = false;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(activity);
        adapterPlayersFragmentBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.player_adapter, viewGroup, false);

        return new PlayersAdapter.ViewHolder(adapterPlayersFragmentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.adapterPlayersFragmentBinding.playerFirstName.setText(playersArrayList.get(i).getFirstName());
        viewHolder.adapterPlayersFragmentBinding.playerLastName.setText(playersArrayList.get(i).getLastName());
        viewHolder.adapterPlayersFragmentBinding.teamName.setText(playersArrayList.get(i).getTeam().getName());
        viewHolder.adapterPlayersFragmentBinding.teamId.setText("#" + playersArrayList.get(i).getTeam().getId());
        viewHolder.adapterPlayersFragmentBinding.position.setText("Position - " + playersArrayList.get(i).getPosition());
        viewHolder.adapterPlayersFragmentBinding.teamFullName.setText("" + playersArrayList.get(i).getTeam().getFullName());
        viewHolder.adapterPlayersFragmentBinding.teamConference.setText(playersArrayList.get(i).getTeam().getConference() + " / " + playersArrayList.get(i).getTeam().getDivision());
    }

    @Override
    public int getItemCount() {
        return playersArrayList == null ? 0 : playersArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private PlayerAdapterBinding adapterPlayersFragmentBinding;

        public ViewHolder(@NonNull PlayerAdapterBinding itemView) {
            super(itemView.getRoot());
            this.adapterPlayersFragmentBinding = itemView;
        }
    }
}
