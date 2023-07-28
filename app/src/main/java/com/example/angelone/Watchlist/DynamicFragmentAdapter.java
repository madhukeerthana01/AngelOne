package com.example.angelone.Watchlist;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DynamicFragmentAdapter extends FragmentStatePagerAdapter {

    private final List<String> tabNames;

    public DynamicFragmentAdapter(@NonNull FragmentManager fragmentManager, Set<String> tabNamesSet) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabNames = new ArrayList<>(tabNamesSet);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        String tabName = tabNames.get(position);
        return TabFragment.newInstance(tabName);
    }

    @Override
    public int getCount() {
        return tabNames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }
}
