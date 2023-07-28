package com.example.angelone.Portfolio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angelone.R;
import com.google.android.material.tabs.TabLayout;

public class PortfolioFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ViewPager getViewPager() {
        return viewPager;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new AllFragment();
                } else if (position == 1) {
                    return new EquityFragment();
                } else if (position == 2) {
                    return new MutualFundsFragment();
                } else {
                    return new SGBFragment();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("All");
        tabLayout.getTabAt(1).setText("Equity");
        tabLayout.getTabAt(2).setText("Mutual Funds");
        tabLayout.getTabAt(3).setText("SGB");

        return view;
    }
}
