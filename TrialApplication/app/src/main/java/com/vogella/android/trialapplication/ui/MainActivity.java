package com.vogella.android.trialapplication.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.Meals;
import com.vogella.android.trialapplication.db.ZoloFoods;
import com.vogella.android.trialapplication.db.ZoloFoodsVM;

public class MainActivity extends AppCompatActivity {


    private ArrayList<String> allCities, propertiesList;


    private static String TAG = "MainActivity";

    Spinner spinnerCities, spinnerProperty, spinnerMeals;

    String selectedCity = "", selectedProperty = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allCities = ZoloFoodsVM.getAllCities();

        spinnerCities = findViewById(R.id.citySpinner);
        spinnerProperty = findViewById(R.id.propertySpinner);
        spinnerMeals = findViewById(R.id.mealsSpinner);

        spinnerProperty.setVisibility(View.GONE);
        spinnerMeals.setVisibility(View.GONE);


        ArrayList<String> mealsList = new ArrayList<>();
        mealsList.add("Breakfast");
        mealsList.add("Lunch");
        mealsList.add("Dinner");

        ArrayAdapter<String> mealsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mealsList);
        spinnerMeals.setAdapter(mealsAdapter);

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, allCities);
        spinnerCities.setAdapter(citiesAdapter);

        spinnerCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    Log.d(TAG, "Selected City: "+allCities.get(position));

                    selectedCity = allCities.get(position);

                    propertiesList = ZoloFoodsVM.getPropertiesByCity(selectedCity);



                    ArrayAdapter<String> propertiesAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, propertiesList);

                    spinnerProperty.setAdapter(propertiesAdapter);
                    spinnerProperty.setVisibility(View.VISIBLE);
                }
            }
        });

        spinnerProperty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedProperty = propertiesList.get(position);
                Log.d(TAG, "Selected Property: "+selectedProperty);
                spinnerMeals.setVisibility(View.VISIBLE);
            }
        });

        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(selectedCity.isEmpty() && selectedProperty.isEmpty())) {
                    Log.d(TAG, "Good to launch another screen: "+selectedCity+" "+selectedProperty);
                }
            }
        });

        ZoloFoods zoloFoods = new ZoloFoods( "", "", "", new Meals("", new ArrayList<String>(), 0), false);
        ZoloFoodsVM.saveData(zoloFoods);
    }


}
