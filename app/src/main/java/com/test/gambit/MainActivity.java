package com.test.gambit;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.test.gambit.databinding.ActivityMainBinding;
import com.test.gambit.ui.FragmentPagerAdapter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.style_tab)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager pager;
    private View playersCount, gamesCount;
    private TextView playersCountView, gamesCountView;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.getRoot();

        //activityMainBinding.styleTab.addTab(activityMainBinding.styleTab.newTab().setText("Players"));
       // activityMainBinding.styleTab.addTab(activityMainBinding.styleTab.newTab().setText("Games"));

        //Bind Butterknife to the view
        ButterKnife.bind(this);

        // Create an adapter that knows which fragment should be shown on each page
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        pager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        tabLayout.setupWithViewPager(pager);

        /*playersCount = LayoutInflater.from(this).inflate(R.layout.player_count_view, null);
        playersCountView = playersCount.findViewById(R.id.count_player_text_view);

        gamesCount = LayoutInflater.from(this).inflate(R.layout.games_count_view,null);
        gamesCountView = gamesCount.findViewById(R.id.count_games_text_view);

        playersCountView.setText("10");
        Objects.requireNonNull(Objects.requireNonNull(activityMainBinding.styleTab.getTabAt(0)).setText(R.string.players_tab)).setCustomView(playersCountView);
        gamesCountView.setText("5");
        Objects.requireNonNull(Objects.requireNonNull(activityMainBinding.styleTab.getTabAt(1)).setText(R.string.games_tab)).setCustomView(gamesCountView);*/
    }
}
