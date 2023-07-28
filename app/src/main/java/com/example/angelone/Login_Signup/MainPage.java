package com.example.angelone.Login_Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.angelone.Account.AccountFragment;
import com.example.angelone.Home.HomeFragment;
import com.example.angelone.Orders.OrdersFragment;
import com.example.angelone.Portfolio.PortfolioFragment;
import com.example.angelone.R;
import com.example.angelone.Watchlist.WatchlistFragment;
import com.google.android.material.tabs.TabLayout;

public class MainPage extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab_layout);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new HomeFragment();
                } else if (position == 1) {
                    return new WatchlistFragment();
                } else if (position == 2) {
                    return new PortfolioFragment();
                } else if (position == 3) {
                    return new OrdersFragment();
                } else {
                    return new AccountFragment();
                }

            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_home_24);
        tabLayout.getTabAt(0).setText("Home");

        tabLayout.getTabAt(1).setIcon(R.drawable.baseline_playlist_add_24);
        tabLayout.getTabAt(1).setText("WatchList");

        tabLayout.getTabAt(2).setIcon(R.drawable.baseline_folder_open_24);
        tabLayout.getTabAt(2).setText("Portfolio");

        tabLayout.getTabAt(3).setIcon(R.drawable.baseline_event_note_24);
        tabLayout.getTabAt(3).setText("Orders");

        tabLayout.getTabAt(4).setIcon(R.drawable.baseline_account_circle_24);
        tabLayout.getTabAt(4).setText("Account");

    }

    public void navigateToAccountFragment(){
        viewPager.setCurrentItem(4);
    }
    public void navigateToStocksListFragment() {
    }
}