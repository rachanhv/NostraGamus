package com.test.gambit;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.test.gambit.databinding.ActivityMainBinding;
import com.test.gambit.ui.FragmentTabPagerAdapter;
import com.test.gambit.utils.load.TabLayoutUpdateListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements TabLayoutUpdateListener {

    private View playerView, gamesView;
    private TextView playerCountView, gameCountView;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.getRoot();

        activityMainBinding.styleTab.addTab(activityMainBinding.styleTab.newTab().setText(R.string.players_tab));
        activityMainBinding.styleTab.addTab(activityMainBinding.styleTab.newTab().setText(R.string.games_tab));

        FragmentTabPagerAdapter adapter = new FragmentTabPagerAdapter(getSupportFragmentManager());
        activityMainBinding.viewpager.setAdapter(adapter);
        activityMainBinding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(activityMainBinding.styleTab));
        activityMainBinding.styleTab.setupWithViewPager(activityMainBinding.viewpager);

        playerView = LayoutInflater.from(this).inflate(R.layout.player_count_view, null);
        playerCountView = playerView.findViewById(R.id.count_player_text_view);

        gamesView = LayoutInflater.from(this).inflate(R.layout.games_count_view, null);
        gameCountView = gamesView.findViewById(R.id.count_games_text_view);
    }

    @Override
    public void updateTabText(int position, String text) {
        if (position == 0) {
            playerCountView.setText(text);
            Objects.requireNonNull(Objects.requireNonNull(activityMainBinding.styleTab.getTabAt(position)).setText(R.string.players_tab)).setCustomView(playerView);
        } else if (position == 1) {
            gameCountView.setText(text);
            Objects.requireNonNull(Objects.requireNonNull(activityMainBinding.styleTab.getTabAt(position)).setText(R.string.games_tab)).setCustomView(gamesView);
        }
    }
}
