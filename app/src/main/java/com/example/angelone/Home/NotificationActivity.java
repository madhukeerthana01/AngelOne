package com.example.angelone.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.angelone.Watchlist.NotificationFragment;
import com.example.angelone.R;
import com.google.android.material.tabs.TabLayout;

public class NotificationActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tab_layout);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab_layout);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new NotificationFragment();
                } else if (position == 1) {
                    return new AlertFragment();
                } else {
                    return new AnnouncementFragment();
                }

            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Notification");

        tabLayout.getTabAt(1).setText("Alert");

        tabLayout.getTabAt(2).setText("Announcement");


    }
}