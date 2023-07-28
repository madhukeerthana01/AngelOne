package com.example.angelone.Watchlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.angelone.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class WishlistMiniFragment extends Fragment implements View.OnClickListener {

    private List<String> tabNames; // List to store tab names

    private static final String TAG = "WatchlistMiniFragment";
    private TabLayout tabLayout1;
    private ImageView add;
    private ViewPager viewPager;
    private SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "watchlist_shared_pref";
    private static final String TAB_NAMES_KEY = "tab_names";

    public static WishlistMiniFragment newInstance() {
        return new WishlistMiniFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the tabNames list here to retain its values across view recreations
        tabNames = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist_mini, container, false);

        add = view.findViewById(R.id.add);
        add.setOnClickListener(this);
        viewPager = view.findViewById(R.id.viewPagerwlm);
        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add = view.findViewById(R.id.add);
        add.setOnClickListener(this);


        // Initialize the TabLayout with the existing tab names from SharedPreferences
        tabLayout1 = view.findViewById(R.id.tabLayout2);
        Set<String> tabNamesSet = sharedPreferences.getStringSet(TAB_NAMES_KEY, new HashSet<>());
        for (String tabName : tabNamesSet) {
            TabLayout.Tab newTab = tabLayout1.newTab();
            newTab.setText(tabName);
            tabLayout1.addTab(newTab);
            viewPager = view.findViewById(R.id.viewPagerwlm);
            DynamicFragmentAdapter pagerAdapter = new DynamicFragmentAdapter(getChildFragmentManager(), tabNamesSet);
            viewPager.setAdapter(pagerAdapter);
            tabLayout1.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add) {
            bottomSheet();
        }
    }
    private void bottomSheet() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheet_add, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);
        EditText editText = bottomSheetView.findViewById(R.id.watchlist);
        Button addButton = bottomSheetView.findViewById(R.id.create);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(itemName)) {
                    addNewTab(itemName);
                    bottomSheetDialog.dismiss();
                }
            }
        });
        try {
            bottomSheetDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "Error showing BottomSheetDialog: " + e.getMessage());
        }
    }

    private void addNewTab(String itemName) {
        Set<String> currentTabNames = sharedPreferences.getStringSet(TAB_NAMES_KEY, new HashSet<>());
        currentTabNames.add(itemName);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(TAB_NAMES_KEY, currentTabNames);
        editor.apply();
        TabLayout.Tab newTab = tabLayout1.newTab();
        newTab.setText(itemName);
        tabLayout1.addTab(newTab);
        tabNames.add(itemName);
        if (viewPager.getAdapter() != null) {
            viewPager.getAdapter().notifyDataSetChanged();
        }
    }
}