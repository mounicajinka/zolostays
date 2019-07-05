package com.vogella.android.trialapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.text.SimpleDateFormat;


import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.ZoloFoodsVM;
import com.vogella.android.trialapplication.http.Api;
import com.vogella.android.trialapplication.http.ApiClient;
import com.vogella.android.trialapplication.model.AdapterData;
import com.vogella.android.trialapplication.model.AnalysisData;
import com.vogella.android.trialapplication.model.MealAnalysis;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WastageActivity extends AppCompatActivity {


    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("EEEE dd/MM/yyyy");
    String formattedDate = df.format(c);


    String selectedTypeOfMeal = "";
    ArrayList<String> items = new ArrayList<>();
    RecyclerView rvList;
    RecyclerViewAdapter recyclerViewAdapter;
    TextView errorMessage;
    ArrayList<MealAnalysis> mealAnalysis;
    private int checkCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wastage);

        Spinner spinner = findViewById(R.id.itemsSpinner);

        rvList = findViewById(R.id.rvList);
        errorMessage = findViewById(R.id.errorMessage);

        final String city = getIntent().getStringExtra("city");
        final String typeOfMeal = getIntent().getStringExtra("typeOfMeal");
        final String serviceType = getIntent().getStringExtra("serviceType");
        final String date = formattedDate;
        final String kitchen_name = "saksham";
        System.out.println("the current date is " + formattedDate);
        final String property = getIntent().getStringExtra("property");

        items = ZoloFoodsVM.getItemsByData(city, property, typeOfMeal, date);

        if (items.size() > 0) {
            recyclerViewAdapter = new RecyclerViewAdapter(items);
            rvList.setLayoutManager(new LinearLayoutManager(WastageActivity.this, LinearLayoutManager.VERTICAL, false));
            rvList.setItemAnimator(new DefaultItemAnimator());
            rvList.setAdapter(recyclerViewAdapter);
            errorMessage.setVisibility(View.GONE);
        } else {
            errorMessage.setVisibility(View.VISIBLE);
        }


        final ArrayAdapter<String> mealsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(mealsAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTypeOfMeal = items.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final EditText edtvWastage = findViewById(R.id.edtvWastage);

        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mealAnalysis = new ArrayList<>();
                final List<AdapterData> adapterDataList = recyclerViewAdapter.getAdapterData();
                for (int i = 0; i < adapterDataList.size(); i++) {
                    final String itemName = adapterDataList.get(i).getItemname();
                    final String vessalId = adapterDataList.get(i).getVessel_id();
                    final Double wastage = adapterDataList.get(i).getWeight();
                    Log.d("CheckKarenge", "ItemName " + itemName + ", vessel " + vessalId + ", weight " + wastage);
                    Api apiService =
                            ApiClient.getClient().create(Api.class);
                    Call<AnalysisData> call = apiService.saveData(adapterDataList.get(i).getItemname(), adapterDataList.get(i).getVessel_id(), "" + adapterDataList.get(i).getWeight(), kitchen_name, property, serviceType, typeOfMeal, city, date);
                    System.out.println("retrofit URL " + call.request());
                    call.enqueue(new Callback<AnalysisData>() {
                        @Override
                        public void onResponse(Call<AnalysisData> call, Response<AnalysisData> response) {
                            System.out.println("hh success retrofit URL " + response.body());

                            if (response.body() != null) {
                                checkCount = checkCount + 1;
                                MealAnalysis analysis = new MealAnalysis();
                                analysis.setItem(itemName);
                                analysis.setVesselid(vessalId);
                                analysis.setWastage(wastage);
                                mealAnalysis.add(analysis);
                                if (checkCount == adapterDataList.size()) {
                                    printArrayList(mealAnalysis);
                                }

                                Log.d("CheckKarenge", "Response Size "+mealAnalysis.size());

                                if (mealAnalysis.size() == adapterDataList.size()) {
                                    Intent intent = new Intent(WastageActivity.this, ReviewScreen.class);
                                    intent.putExtra("blah", mealAnalysis);
                                    startActivity(intent);
                                }

                                Toast.makeText(WastageActivity.this, "Data saved successfully. Your new Id is " + response.body().getInsertId(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(WastageActivity.this, "Something wwent wrong", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AnalysisData> call, Throwable t) {
                            System.out.println("hh failure retrofit URL " + t.getMessage());
                        }
                    });

                }

                Log.d("CheckKarenge", "Sent Size "+mealAnalysis.size());
            }
        });

    }

    private void printArrayList(ArrayList<MealAnalysis> mealAnalysis) {
        for (MealAnalysis mealAnalysis1 : mealAnalysis) {

        }
    }

}