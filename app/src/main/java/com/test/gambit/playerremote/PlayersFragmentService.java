package com.test.gambit.playerremote;

public interface PlayersFragmentService {

    void getAllPlayers(
            final int pageNumber,
            final int perPage,
            final PlayersApiInterface playersApiInterface,
            final PlayersClientCallback playersClientCallback);


    void getAllSearchPlayers(
            final String search,
            final PlayersApiInterface playersApiInterface,
            final PlayersSearchCallback playersSearchCallback);

}
