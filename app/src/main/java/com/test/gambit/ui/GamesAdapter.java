package com.test.gambit.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.gambit.R;
import com.test.gambit.databinding.GamesAdapterBinding;
import com.test.gambit.model.GameData;

import java.util.ArrayList;
import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {
    LayoutInflater layoutInflater;
    GamesAdapterBinding gamesAdapterBinding;
    List<GameData> gamesList = new ArrayList<>();
    Context mContext;

    public GamesAdapter(Context context, List<GameData> list) {
        this.mContext = context;
        this.gamesList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(mContext);
        gamesAdapterBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.games_adapter, viewGroup, false);

        return new GamesAdapter.ViewHolder(gamesAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.adapterBinding.gamesAdapterHomeAbbreviation.setText(gamesList.get(i).getHomeTeam().getAbbreviation());
        viewHolder.adapterBinding.gamesAdapterHomeFullName.setText(gamesList.get(i).getHomeTeam().getFullName());
        viewHolder.adapterBinding.gamesAdapterVisitorAbbreviation.setText(gamesList.get(i).getVisitorTeam().getAbbreviation());
        viewHolder.adapterBinding.gamesAdapterVisitorFullName.setText(gamesList.get(i).getVisitorTeam().getFullName());
    }

    @Override
    public int getItemCount() {
        return gamesList == null ? 0 : gamesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        GamesAdapterBinding adapterBinding;

        public ViewHolder(@NonNull GamesAdapterBinding adapterBinding) {
            super(adapterBinding.getRoot());
            this.adapterBinding = adapterBinding;
        }
    }
}
