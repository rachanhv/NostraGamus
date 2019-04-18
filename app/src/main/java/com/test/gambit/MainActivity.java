package com.test.gambit;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.test.gambit.model.PlayerTeam;
import com.test.gambit.model.Players;
import com.test.gambit.rest.ApiClient;
import com.test.gambit.rest.ApiInterface;
import com.test.gambit.ui.FragmentPagerAdapter;
import com.test.gambit.utils.Global;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Butterknife to the view
        ButterKnife.bind(this);

        // Create an adapter that knows which fragment should be shown on each page
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        pager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        tabLayout.setupWithViewPager(pager);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);*/

       /* Call<PlayerTeam> call = Global.apiService.getTeam();
        call.enqueue(new Callback<PlayerTeam>() {
            @Override
            public void onResponse(Call<PlayerTeam> call, Response<PlayerTeam> response) {
                PlayerTeam playerTeam = new PlayerTeam();
                playerTeam = response.body();
                Log.i("PlayersFragment",playerTeam.getName());
                Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PlayerTeam> call, Throwable t) {
                Log.i("PlayersFragment","");
            }
        });*/
    }
}
