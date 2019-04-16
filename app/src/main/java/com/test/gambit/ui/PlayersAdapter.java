package com.test.gambit.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.gambit.R;
import com.test.gambit.model.Players;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    List<Players> playersArrayList = new ArrayList<>();
    Context mContext;
    @BindView(R.id.player_name)
    public TextView playerName;

    public PlayersAdapter(Context context, List<Players> playersList) {
        this.mContext = context;
        this.playersArrayList = playersList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.player_adapter, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Players players = playersArrayList.get(i);
        playerName.setText(players.getData().get(i).getFirstName());
    }

    @Override
    public int getItemCount() {
        return playersArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(mContext, itemView);
        }
    }
}
