package com.example.angelone.Watchlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.angelone.R;
import com.example.angelone.Home.ViewPagerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

public class WatchlistFragment extends Fragment implements View.OnClickListener {

    private ImageView filter, add;
    private TextView watchList, options;
    private ViewPager viewPager;
    private TabLayout tabLayout1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watchlist, container, false);
        filter = view.findViewById(R.id.filter);
        filter.setOnClickListener(this);
        watchList = view.findViewById(R.id.watchList);
        options = view.findViewById(R.id.options);
        watchList.setOnClickListener(this);
        options.setOnClickListener(this);
        viewPager = view.findViewById(R.id.viewPager);
        setUpViewPager(viewPager);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.filter) {
            showBottomSheetDialog();
        } else if (v.getId() == R.id.watchList) {
            viewPager.setCurrentItem(0);
        } else if (v.getId() == R.id.options) {
            viewPager.setCurrentItem(1);
        }
    }

        private void showBottomSheetDialog () {
            View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet, null);

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
            bottomSheetDialog.setContentView(bottomSheetView);

            TabLayout tabLayout = bottomSheetView.findViewById(R.id.tab_layout);
            tabLayout.setupWithViewPager(viewPager);

            bottomSheetDialog.show();
        }

        private void setUpViewPager (ViewPager viewPager){
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
            viewPagerAdapter.addFragment(new WishlistMiniFragment(), "Wishlist");
            viewPagerAdapter.addFragment(new OptionsFragment(), "Options");
            viewPager.setAdapter(viewPagerAdapter);
        }
    }

