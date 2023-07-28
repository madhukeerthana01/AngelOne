package com.example.angelone.Orders;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angelone.Orders.GTTFragment;
import com.example.angelone.Orders.OpenOrdersFragment;
import com.example.angelone.Orders.PositionsFragment;
import com.example.angelone.Orders.StockSIPFragment;
import com.example.angelone.R;
import com.google.android.material.tabs.TabLayout;

public class OrdersFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new OpenOrdersFragment();
                } else if (position == 1) {
                    return new PositionsFragment();
                }else if (position == 2) {
                    return new StockSIPFragment();
                }  else {
                    return new GTTFragment();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Open Orders");
        tabLayout.getTabAt(1).setText("Positions");
        tabLayout.getTabAt(2).setText("Stock SIP");
        tabLayout.getTabAt(3).setText("GTT");

        return view;
    }
}