package com.test.gambit.gameremote;

import com.test.gambit.model.Games;

public interface GamesClientCallback {
    void onSuccess(Games games);

    void onError(String errorMessage);
}
