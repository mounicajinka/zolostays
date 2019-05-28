package com.vogella.android.trialapplication.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.viewmodels.ZoloFoodsVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private ZoloFoodsVM model;
    private ArrayList<String> allCities, propertiesList;

    private static String TAG = "MainActivity";

    private Spinner spinnerCities, spinnerProperty, spinnerMeals;

    private String selectedCity = "", selectedProperty = "", selectedTypeOfMeal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        model = new ViewModelProvider.AndroidViewModelFactory(App.getInstance()).create(ZoloFoodsVM.class);

        Objects.requireNonNull(model.getallCities()).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                allCities = new ArrayList<>(strings);
                ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, strings);
                spinnerCities.setAdapter(citiesAdapter);
            }
        });

    }

    private void init() {

        spinnerCities = findViewById(R.id.citySpinner);
        spinnerProperty = findViewById(R.id.propertySpinner);
        spinnerMeals = findViewById(R.id.mealsSpinner);

        spinnerProperty.setVisibility(View.GONE);
        spinnerMeals.setVisibility(View.GONE);

        final ArrayList<String> mealsList = new ArrayList<>();
        mealsList.add("Breakfast");
        mealsList.add("Lunch");
        mealsList.add("Dinner");

        ArrayAdapter<String> mealsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mealsList);
        spinnerMeals.setAdapter(mealsAdapter);


        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    Log.d(TAG, "Selected City: " + allCities.get(position));

                    selectedCity = allCities.get(position);

                    propertiesList = new ArrayList<>(model.getPropertiesByCity(selectedCity));

                    ArrayAdapter<String> propertiesAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, propertiesList);

                    spinnerProperty.setAdapter(propertiesAdapter);
                    spinnerProperty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerProperty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProperty = propertiesList.get(position);
                Log.d(TAG, "Selected Property: " + selectedProperty);
                spinnerMeals.setVisibility(View.VISIBLE);
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

        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(selectedCity.isEmpty() && selectedProperty.isEmpty())) {
                    Log.d(TAG, "Good to launch another screen: " + selectedCity + " " + selectedProperty);
                }
            }
        });
    }
}