package com.test.gambit.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.test.gambit.R;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    public FragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PlayersFragment();
        } else {
            return new GamesFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.players_tab);
            case 1:
                return mContext.getString(R.string.games_tab);
            default:
                return null;
        }
    }
}
