package com.example.angelone.Home;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angelone.Watchlist.ImagePageAdapter;
import com.example.angelone.Login_Signup.MainPage;
import com.example.angelone.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class HomeFragment extends Fragment {
    ImageButton screenpage;
    ImageView imageView, search, notification;
    ViewPager viewPager1, viewPager2;
    TextView textView1, textView2,viewMore;
    private int[] image = {R.drawable.screenshotone, R.drawable.screenshottwo,
            R.drawable.screenshotthree, R.drawable.screenshotfour};
    private int[] image1 = {R.drawable.screennextone, R.drawable.screennexttwo};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewMore=view.findViewById(R.id.view);
        imageView = view.findViewById(R.id.profile);
        search = view.findViewById(R.id.search);
        notification = view.findViewById(R.id.notification);

        viewPager1 = view.findViewById(R.id.viewPager);
        viewPager2=view.findViewById(R.id.viewPager1);
        screenpage=view.findViewById(R.id.screenpage);
        ImagePageAdapter imagePageAdapter = new ImagePageAdapter(requireContext(), image);
        viewPager1.setAdapter(imagePageAdapter);

       ImagePageAdapter imagePageAdapter1=new ImagePageAdapter(requireContext(),image1);
       viewPager2.setAdapter(imagePageAdapter1);

        textView1 = view.findViewById(R.id.reward);
        textView2 = view.findViewById(R.id.refer);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });

        screenpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExploreAOne.class);
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainPage mainPage = (MainPage) requireActivity();
                mainPage.navigateToAccountFragment();
            }
        });

        viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StocksListActivity.class);
                startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainPage mainpage = (MainPage) requireActivity();
                mainpage.navigateToAccountFragment();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    private void showBottomSheetDialog() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        bottomSheetDialog.show();

    }
}