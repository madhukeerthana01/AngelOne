package com.example.angelone.Watchlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelone.R;

import java.util.ArrayList;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.ViewHolder> {
    private ArrayList<String> watchlists;
    private OnClickListener onClickListener;

    public WatchlistAdapter(ArrayList<String> watchlists) {
        this.watchlists = watchlists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.watchlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String watchlist = watchlists.get(position);
        holder.bind(watchlist);
    }

    @Override
    public int getItemCount() {
        return watchlists.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView watchlistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            watchlistName = itemView.findViewById(R.id.watchlistName);
            itemView.setOnClickListener(this);
        }

        public void bind(String watchlist) {
            watchlistName.setText(watchlist);
        }

        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onClickListener.onClick(position, watchlists.get(position));
                }
            }
        }
    }

    public interface OnClickListener {
        void onClick(int position, String watchlist);
    }
}
