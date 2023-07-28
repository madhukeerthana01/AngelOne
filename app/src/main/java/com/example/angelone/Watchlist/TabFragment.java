package com.example.angelone.Watchlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelone.Home.RecyclerAdapterSL;
import com.example.angelone.R;

public class TabFragment extends Fragment {

    private static final String ARG_TAB_NAME = "arg_tab_name";

    private String tabName;

    public TabFragment() {
        // Required empty public constructor
    }

    public static TabFragment newInstance(String tabName) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TAB_NAME, tabName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabName = getArguments().getString(ARG_TAB_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        TextView textView = view.findViewById(R.id.tabContentTextView);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(RecyclerAdapterSL.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String key = "tab_" + tabName; // Use the same key used for saving the data
        String data = sharedPreferences.getString(key, "");
        textView.setText(data);

        return view;
    }
}
