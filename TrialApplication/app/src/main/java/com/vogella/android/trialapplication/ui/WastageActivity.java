package com.vogella.android.trialapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.Meals;
import com.vogella.android.trialapplication.db.ZoloFoods;
import com.vogella.android.trialapplication.db.ZoloFoodsVM;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WastageActivity extends AppCompatActivity {

    String selectedTypeOfMeal = "";
    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wastage);

        Spinner spinner = findViewById(R.id.itemsSpinner);

        String city = getIntent().getStringExtra("city");
        String property = getIntent().getStringExtra("property");
        String typeOfMeal = getIntent().getStringExtra("typeOfMeal");

        items = ZoloFoodsVM.getItemsByData(city, property, typeOfMeal);

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

                Toast.makeText(WastageActivity.this, "wastage: "+edtvWastage.getText()+" for typeOfMeal: "+selectedTypeOfMeal, Toast.LENGTH_LONG).show();

                Log.d("TAG", "submit wastage: "+edtvWastage.getText().toString());






               /* if (task.isSuccessful()) {
                    final Callback<JsonArray> getNetworkData = new Callback<JsonArray>() {
                        @Override
                        public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                            if (response.isSuccessful()) {
                                Log.d(TAG, "httpCallBack: "+response.body());
                                for (int i = 0; i < response.body().size(); i++) {

                                    JsonObject jsonObject = response.body().get(i).getAsJsonObject();

                                    String city = jsonObject.get("usercity").getAsString();
                                    String property = jsonObject.get("userhotel").getAsString();
                                    String date = jsonObject.get("daily_date").getAsString();
                                    String mealType = jsonObject.get("meal_type").getAsString();
                                    String itemName = jsonObject.get("item_name").getAsString();
                                    String manager = jsonObject.get("username").getAsString();

                                    String serviceType = "";
                                    String vesselId = "";
                                    int vesselWeight = 0;
                                    int wastage = 0;
                                    if (jsonObject.has("service_type")) {
                                        serviceType = jsonObject.get("service_type").getAsString();
                                    }
                                    if (jsonObject.has("vessel_id")) {
                                        vesselId = jsonObject.get("vessel_id").getAsString();
                                    }
                                    if (jsonObject.has("vessel_wastage")) {
                                        vesselWeight = jsonObject.get("vessel_wastage").getAsInt();
                                    }
                                    if (jsonObject.has("wastage")) {
                                        wastage = jsonObject.get("wastage").getAsInt();
                                    }

                                    ZoloFoods zoloFoods = new ZoloFoods(manager, city, property, date, new Meals(mealType, itemName, serviceType, vesselId, vesselWeight, wastage), false);
                                    ZoloFoodsVM.saveData(zoloFoods);
                                }
*/



                //TODO: Network call, response, write in to mobile DB\
//



                //                                    do network calls here
                //                                    like POST URL after writing into database

                //                                    JSONObject jsonObject = new JSONObject();
//
//
//                                    try {
//                                        String property = jsonObject.getString("property");
//
//                                        String man
//
//
//                                        ZoloFoods zoloFoods = new ZoloFoods( "", "", property, new Meals("", new ArrayList<String>(), 0), false);
//                                        ZoloFoodsVM.saveData(zoloFoods);

//
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }


            }
        });

    }

}
