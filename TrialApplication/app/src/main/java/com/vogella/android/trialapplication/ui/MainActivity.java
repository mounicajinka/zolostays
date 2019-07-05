package com.vogella.android.trialapplication.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.ZoloFoods;
import com.vogella.android.trialapplication.db.ZoloFoodsVM;
import com.vogella.android.trialapplication.model.KitchenMenu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> allCities, propertiesList;

    private static String TAG = "MainActivity";

    Spinner spinnerCities, spinnerKitchen, spinnerMeals, spinnerServices;

    DatePicker picker;

    Button refresh;
    String selectedCity = "", selectedKitchen = "", selectedTypeOfMeal = "", selectedServiceType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getData();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }

    private void init() {
        spinnerCities = findViewById(R.id.citySpinner);
        spinnerKitchen = findViewById(R.id.propertySpinner);
        spinnerMeals = findViewById(R.id.mealsSpinner);
        spinnerServices = findViewById(R.id.serviceSpinner);
        refresh = findViewById(R.id.btnRefresh);
    }


    private void getData() {
        final Callback<List<KitchenMenu>> data = new Callback<List<KitchenMenu>>() {
            @Override
            public void onResponse(Call<List<KitchenMenu>> call, Response<List<KitchenMenu>> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG", "httpCallBack: " + response.body());
                    try {
                        List<KitchenMenu> KitchenData = response.body();
                        for (int i = 0; i < KitchenData.size(); i++) {

                            String manager = KitchenData.get(i).getManager();
                            int id = KitchenData.get(i).getId();
                            String city = KitchenData.get(i).getCity();
                            System.out.println("hhhhhc " + city);
                            String property = KitchenData.get(i).getProperty();
                            System.out.println("hhhhhpc  " + property);
                            String date = KitchenData.get(i).getDate();
                            String mealType = KitchenData.get(i).getMealtype();
                            String itemName = KitchenData.get(i).getItem();
                            System.out.println("hhhhh " + mealType);
                            ZoloFoods zoloFoods = new ZoloFoods(id, manager, city, property, date, mealType, itemName, false);
                            ZoloFoodsVM.saveData(zoloFoods);
                        }
                        setUpFieldsWithdata();
                    } catch (Exception e) {
                        Log.d("onResponse", "There is an error");
                        e.printStackTrace();
                    }
                } else {
                    Log.d("TAG", "failure response: " + response.isSuccessful());

                }
            }

            @Override
            public void onFailure(Call<List<KitchenMenu>> call, Throwable t) {
                Log.d("TAG", "httpCallBack: " + t.getMessage());

            }
        };
        App.getInstance().api.getData().enqueue(data);
    }

    private void setUpFieldsWithdata() {
        allCities = ZoloFoodsVM.getAllCities();

        final ArrayList<String> mealsList = new ArrayList<>();
        mealsList.add("Breakfast");
        mealsList.add("Lunch");
        mealsList.add("Dinner");

        final ArrayList<String> serviceTypeList = new ArrayList<>();
        serviceTypeList.add("Dispatch");
        serviceTypeList.add("Receive");
        serviceTypeList.add("Wastage");

        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, serviceTypeList);
        spinnerServices.setAdapter(serviceAdapter);

        ArrayAdapter<String> mealsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mealsList);
        spinnerMeals.setAdapter(mealsAdapter);

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, allCities);
        spinnerCities.setAdapter(citiesAdapter);
//        spinnerCities.setSelection(postion);

        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    Log.d(TAG, "Selected City: " + allCities.get(position));

                    selectedCity = allCities.get(position);

                    propertiesList = ZoloFoodsVM.getPropertiesByCity(selectedCity);

                    ArrayAdapter<String> kitchenAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, propertiesList);

                    spinnerKitchen.setAdapter(kitchenAdapter);
                    spinnerKitchen.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerKitchen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedKitchen = propertiesList.get(position);
                Log.d(TAG, "Selected Property: " + selectedKitchen);
                spinnerMeals.setVisibility(View.VISIBLE);

                //getAllPropertes(selectedKitchen);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerMeals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTypeOfMeal = mealsList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerServices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedServiceType = serviceTypeList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(selectedCity.isEmpty() && selectedKitchen.isEmpty())) {
                    Log.d(TAG, "Good to launch another screen: " + selectedCity + " " + selectedKitchen);

                    Intent wastageIntent = new Intent(getApplicationContext(), WastageActivity.class);
                    wastageIntent.putExtra("city", selectedCity);
                    wastageIntent.putExtra("property", selectedKitchen);
                    wastageIntent.putExtra("typeOfMeal", selectedTypeOfMeal);
                    wastageIntent.putExtra("serviceType", selectedServiceType);

                    startActivity(wastageIntent);
                }
            }
        });
    }

   /* private void getAllPropertes(String selectedKitchen){


    }*/
}