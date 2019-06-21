package com.vogella.android.trialapplication.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.model.MealAnalysis;

import java.util.ArrayList;

public class MealAnalysisRecyclerViewAdapter extends RecyclerView.Adapter<MealAnalysisRecyclerViewAdapter.ViewHolder> {

    ArrayList<MealAnalysis> mealAnalysis;

    public MealAnalysisRecyclerViewAdapter(ArrayList<MealAnalysis> mealAnalysis) {
        this.mealAnalysis = mealAnalysis;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meal_analysis_item, viewGroup, false);
        return new MealAnalysisRecyclerViewAdapter.ViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d("CheckKarenge", "aDAPTER "+(i+1)+" - "+mealAnalysis.get(i).getVesselid()+", "+mealAnalysis.get(i).getItem()+", "+mealAnalysis.get(i).getWastage()+", size "+mealAnalysis.size());
        viewHolder.tvVesselId.setText("Vessele Id : "+mealAnalysis.get(i).getVesselid());
        viewHolder.tvItemName.setText("Item : "+mealAnalysis.get(i).getItem());
        viewHolder.tvWastage.setText("Wastage : "+mealAnalysis.get(i).getWastage());
    }

    @Override
    public int getItemCount() {
        return mealAnalysis.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVesselId, tvItemName, tvWastage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVesselId = itemView.findViewById(R.id.tvVesselId);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvWastage = itemView.findViewById(R.id.tvWastage);
        }
    }
}
