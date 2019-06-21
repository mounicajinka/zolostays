package com.vogella.android.trialapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.model.MealAnalysis;

import java.util.ArrayList;

public class ReviewScreen extends AppCompatActivity {

    ArrayList<MealAnalysis> mealAnalysis;
    MealAnalysisRecyclerViewAdapter mealAnalysisRecyclerViewAdapter;
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);

        rvList = findViewById(R.id.rvList);

        Log.d("CheckKarenge", "Open Activity");

        mealAnalysis = (ArrayList<MealAnalysis>) getIntent().getSerializableExtra("blah");

        if (mealAnalysis == null) {
            Log.d("CheckKarenge", "nULL");
            return;
        }

        mealAnalysisRecyclerViewAdapter = new MealAnalysisRecyclerViewAdapter(mealAnalysis);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setAdapter(mealAnalysisRecyclerViewAdapter);

        Log.d("CheckKarenge", "Got Size "+mealAnalysis.size());

        for (int i=0; i<mealAnalysis.size(); i++) {
            Log.d("CheckKarenge", "size "+mealAnalysis.size());
            Log.d("CheckKarenge", (i+1)+" - "+mealAnalysis.get(i).getVesselid()+", "+mealAnalysis.get(i).getItem()+", "+mealAnalysis.get(i).getWastage()+", size "+mealAnalysis.size());
        }
    }
}
