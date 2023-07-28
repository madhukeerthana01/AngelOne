package com.example.angelone.Watchlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelone.Home.ListItemData;
import com.example.angelone.Home.RecyclerAdapterSL;
import com.example.angelone.R;

import java.util.List;

public class RecyclerAdapterTab extends RecyclerView.Adapter<RecyclerAdapterTab.ViewHolder> {
    private List<ListItemData> dataList;

    public RecyclerAdapterTab(List<ListItemData> dataList) {
        this.dataList = dataList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       ListItemData itemData = dataList.get(position);
        holder.bindData(itemData);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;
        TextView itemTitleTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.name);
            itemTitleTextView = itemView.findViewById(R.id.message2);
        }

        public void bindData(ListItemData itemData) {
            itemNameTextView.setText(itemData.getItemName());
            itemTitleTextView.setText(itemData.getItemTitle());
        }
    }
}

