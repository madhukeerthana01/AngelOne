package com.example.angelone.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelone.R;

import java.util.List;

public class AvailableTabsAdapter extends RecyclerView.Adapter<AvailableTabsAdapter.ViewHolder> {
    private List<String> availableTabs;

    public AvailableTabsAdapter(List<String> availableTabs) {
        this.availableTabs = availableTabs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_available_tab, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String tabName = availableTabs.get(position);
        holder.tvTabName.setText(tabName);
    }

    @Override
    public int getItemCount() {
        return availableTabs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTabName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTabName = itemView.findViewById(R.id.tvTabName);
        }
    }
}
