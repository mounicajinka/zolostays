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
import android.widget.Toast;
import java.util.Calendar;
import java.text.SimpleDateFormat;


import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.ZoloFoodsVM;
import com.vogella.android.trialapplication.http.Api;
import com.vogella.android.trialapplication.http.ApiClient;
import com.vogella.android.trialapplication.model.AdapterData;
import com.vogella.android.trialapplication.model.AnalysisData;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wastage);

        Spinner spinner = findViewById(R.id.itemsSpinner);

        rvList = findViewById(R.id.rvList);

        final String city = getIntent().getStringExtra("city");
        final String property = getIntent().getStringExtra("property");
        final String typeOfMeal = getIntent().getStringExtra("typeOfMeal");
        final String serviceType = getIntent().getStringExtra("serviceType");
        final String date = formattedDate;
        final String kitchen_name = " saksham";

        items = ZoloFoodsVM.getItemsByData(city, property, typeOfMeal);

        recyclerViewAdapter = new RecyclerViewAdapter(items);
        rvList.setLayoutManager(new LinearLayoutManager(WastageActivity.this, LinearLayoutManager.VERTICAL, false));
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setAdapter(recyclerViewAdapter);

        ArrayAdapter<String> mealsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
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


                List<AdapterData> adapterDataList = recyclerViewAdapter.getAdapterData();
                for (int i = 0; i < adapterDataList.size(); i++) {
                    Log.d("CheckKarenge", "ItemName " + adapterDataList.get(i).getItemname() + ", vessel " + adapterDataList.get(i).getVessel_id() + ", weight " + adapterDataList.get(i).getWeight());



                    Api apiService =
                            ApiClient.getClient().create(Api.class);
                    Call<AnalysisData> call = apiService.saveData(adapterDataList.get(i).getItemname(), adapterDataList.get(i).getVessel_id(), "" + adapterDataList.get(i).getWeight(),kitchen_name,property,serviceType,typeOfMeal,city,date);
                    System.out.println("retrofit URL " + call.request());
                    call.enqueue(new Callback<AnalysisData>() {
                        @Override
                        public void onResponse(Call<AnalysisData> call, Response<AnalysisData> response) {
                            System.out.println("hh success retrofit URL "+response.body());

                            if (response.body() != null) {
                                Toast.makeText(WastageActivity.this, "Data saved successfully. Your new Id is "+response.body().getInsertId(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(WastageActivity.this, "Something wwent wrong", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AnalysisData> call, Throwable t) {
                            // Log error here since request failed.
                            System.out.println("hh failure retrofit URL " + t.getMessage());
                        }
                    });


                }
            }
        });

    }

}