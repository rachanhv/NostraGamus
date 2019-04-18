package com.test.gambit.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.gambit.R;
import com.test.gambit.model.PlayerData;
import com.test.gambit.model.Players;
import com.test.gambit.utils.Global;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    List<PlayerData> playersArrayList = new ArrayList<>();
    Context mContext;


    public PlayersAdapter(Context context, List<PlayerData> playersList) {
        this.mContext = context;
        this.playersArrayList = playersList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.player_adapter, viewGroup, false);

        return new PlayersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        PlayerData players = playersArrayList.get(i);

        viewHolder.mPlayerFirstName.setText(players.getFirstName());
        viewHolder.mPlayerLastName.setText(players.getLastName());
        viewHolder.mTeamName.setText(players.getTeam().getName());
        viewHolder.mTeamId.setText("#" + players.getTeam().getId());
        viewHolder.mPosition.setText(players.getPosition());
        viewHolder.mTeamFullName.setText(players.getTeam().getFullName());
        viewHolder.mTeamConference.setText(players.getTeam().getConference());
        viewHolder.mTeamDivision.setText(players.getTeam().getDivision());
    }

    @Override
    public int getItemCount() {
        return playersArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.player_first_name)
        TextView mPlayerFirstName;
        @BindView(R.id.player_last_name)
        TextView mPlayerLastName;
        @BindView(R.id.team_name)
        TextView mTeamName;
        @BindView(R.id.team_id)
        TextView mTeamId;
        @BindView(R.id.position)
        TextView mPosition;
        @BindView(R.id.team_full_name)
        TextView mTeamFullName;
        @BindView(R.id.team_conference)
        TextView mTeamConference;
        @BindView(R.id.team_division)
        TextView mTeamDivision;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
