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

import com.google.gson.JsonObject;
import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.ZoloFoodsVM;
import com.vogella.android.trialapplication.http.Api;
import com.vogella.android.trialapplication.http.ApiClient;
import com.vogella.android.trialapplication.model.AdapterData;
import com.vogella.android.trialapplication.model.AnalysisData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WastageActivity extends AppCompatActivity {

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
        final String date = "date";
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

                //Toast.makeText(WastageActivity.this, "wastage: "+edtvWastage.getText()+" for typeOfMeal: "+selectedTypeOfMeal, Toast.LENGTH_LONG).show();

                //   Log.d("TAG", "submit wastage: "+edtvWastage.getText().toString());


                List<AdapterData> adapterDataList = recyclerViewAdapter.getAdapterData();
                for (int i = 0; i < adapterDataList.size(); i++) {
                    Log.d("CheckKarenge", "ItemName " + adapterDataList.get(i).getItemname() + ", vessel " + adapterDataList.get(i).getVessel_id() + ", weight " + adapterDataList.get(i).getWeight());

//                    Callback<JsonObject> callback = new Callback<JsonObject>() {
//                        @Override
//                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<JsonObject> call, Throwable throwable) {
//
//                        }
//                    };

//                    JSONObject jsonObject = new JSONObject();
//                    try {
//                        jsonObject.put("type_of_entry", city);
//                        System.out.println("hhhhhs "+city);
//                        jsonObject.put("Property_name", property);
//                        jsonObject.put("meal_type", typeOfMeal);
//                        jsonObject.put("form_type", serviceType);
//                        jsonObject.put("item_name", adapterDataList.get(i).getItemname());
//                        jsonObject.put("vessel_id", adapterDataList.get(i).getVessel_id());
//                        jsonObject.put("weight", adapterDataList.get(i).getWeight());
//                        jsonObject.put("date",date);
//                        jsonObject.put("kitchen_name",kitchen_name);
//
//                        Log.d("tag", jsonObject.toString(4));
//
//                        App.getInstance().api.sendData(jsonObject).enqueue(callback);
//                    } catch (Exception ex) {
//                        Log.e("JSONparsingException", "Exception "+ ex.getMessage());
//                    }


                    Api apiService =
                            ApiClient.getClient().create(Api.class);
                    Call<AnalysisData> call = apiService.saveData("Wednesday 05/06/2019", "serviceType", adapterDataList.get(i).getItemname(), kitchen_name, typeOfMeal, property, city, adapterDataList.get(i).getVessel_id(), "" + adapterDataList.get(i).getWeight());
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