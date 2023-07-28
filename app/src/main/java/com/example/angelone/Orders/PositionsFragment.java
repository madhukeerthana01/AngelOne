package com.example.angelone.Orders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.angelone.R;
import java.util.HashSet;
import java.util.Set;

public class PositionsFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "watchlist_shared_pref";
    private static final String TAB_NAMES_KEY = "tab_names";
    private RadioGroup radioGroupPositions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_positions, container, false);

        radioGroupPositions = view.findViewById(R.id.radioGroupPositions);

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // Get the current tab names from SharedPreferences
        Set<String> tabNamesSet = sharedPreferences.getStringSet(TAB_NAMES_KEY, new HashSet<>());

        // Add RadioButtons to the RadioGroup for each tab
        for (String tabName : tabNamesSet) {
            addRadioButton(tabName);
        }

        return view;
    }

    private void addRadioButton(String tabName) {
        RadioButton radioButton = new RadioButton(requireContext());
        radioButton.setText(tabName);
        radioGroupPositions.addView(radioButton);
    }
}

