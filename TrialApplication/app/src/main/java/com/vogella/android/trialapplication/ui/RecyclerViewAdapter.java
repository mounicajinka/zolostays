package com.vogella.android.trialapplication.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vogella.android.trialapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<String> listOfItems = new ArrayList<>();

    public RecyclerViewAdapter(List<String> listOfItems) {
        this.listOfItems = listOfItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        return new RecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemName.setText(listOfItems.get(viewHolder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        EditText etWeight, vesselId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            etWeight = itemView.findViewById(R.id.etWeight);
            vesselId = itemView.findViewById(R.id.vesselId);
        }
    }
}
