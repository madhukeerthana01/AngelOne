package com.example.angelone.Home;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.angelone.R;
import com.example.angelone.Watchlist.Model;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RecyclerAdapterSL extends RecyclerView.Adapter<RecyclerAdapterSL.ViewHolder> {
    private ArrayList<Model.categories> arrayList;
    private SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "watchlist_shared_pref";
    private static final String TAB_NAMES_KEY = "tab_names";
    private RadioGroup radioGroupTabs;
    private RadioButton selectedRadioButton;

    public RecyclerAdapterSL(ArrayList<Model.categories> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.stockslist_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model.categories categories = arrayList.get(position);
        holder.name.setText(categories.strCategory);
        holder.title.setText(categories.idCategory);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, title;
        ImageView favStar;
        Button addtowl;

        private int[] images = {R.drawable.baseline_star_outline_24, R.drawable.star};
        private int currentImage = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name1);
            title = itemView.findViewById(R.id.message3);
            favStar = itemView.findViewById(R.id.FavStar);
            addtowl = itemView.findViewById(R.id.addtowl);
            radioGroupTabs = itemView.findViewById(R.id.radioGroupTabs); // Initialize the radioGroupTabs
            favStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentImage = (currentImage + 1) % images.length;
                    favStar.setImageResource(images[currentImage]);

                    showBottomSheet(v.getContext(), ViewHolder.this, getAdapterPosition());
                }
            });
        }
    }
    private void showBottomSheet(Context context, ViewHolder viewHolder, int position) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_favstar, null);
        bottomSheetDialog.setContentView(bottomSheetView);
        radioGroupTabs = bottomSheetView.findViewById(R.id.radioGroupTabs);
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Set<String> tabNamesSet = sharedPreferences.getStringSet(TAB_NAMES_KEY, new HashSet<>());
        for (String tabName : tabNamesSet) {
            addRadioButtonToGroup(context, radioGroupTabs, tabName);
        }
        Button addToWatchlistButton = bottomSheetView.findViewById(R.id.addtowl);
        addToWatchlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = radioGroupTabs.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    selectedRadioButton = radioGroupTabs.findViewById(selectedRadioButtonId);
                    String selectedTabName = selectedRadioButton.getText().toString();
                    saveDataToTab(selectedTabName, position);
                }
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    private void addRadioButtonToGroup(Context context, RadioGroup radioGroup, String tabName) {
        RadioButton radioButton = new RadioButton(context);
        radioButton.setText(tabName);
        radioGroup.addView(radioButton);
    }

    private void saveDataToTab(String selectedTabName, int position) {
        String key = "tab_" + selectedTabName;
        String existingData = sharedPreferences.getString(key, "");
        Model.categories category = arrayList.get(position);
        String newData = existingData + category.strCategory + "\n";
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, newData);
        editor.apply();
    }
}

