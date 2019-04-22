package com.test.gambit.playerremote;

import com.test.gambit.model.Players;

public interface PlayersClientCallback {
    void onResponse(Players players);
    void onError(String errorMessage);
}
