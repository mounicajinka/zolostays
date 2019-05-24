package com.vogella.android.trialapplication.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.StrictMode;

import android.os.Bundle;
import android.os.StrictMode;
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

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {


    private ArrayList<String> allCities, propertiesList;


    private static String TAG = "MainActivity";

    Spinner spinnerCities, spinnerProperty, spinnerMeals;

    String selectedCity = "", selectedProperty = "", selectedTypeOfMeal = "";

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


        final ArrayList<String> mealsList = new ArrayList<>();
        mealsList.add("Breakfast");
        mealsList.add("Lunch");
        mealsList.add("Dinner");

        ArrayAdapter<String> mealsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mealsList);
        spinnerMeals.setAdapter(mealsAdapter);

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, allCities);
        spinnerCities.setAdapter(citiesAdapter);

        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    Log.d(TAG, "Selected City: "+allCities.get(position));

                    selectedCity = allCities.get(position);

                    propertiesList = ZoloFoodsVM.getPropertiesByCity(selectedCity);

                    ArrayAdapter<String> propertiesAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, propertiesList);

                    spinnerProperty.setAdapter(propertiesAdapter);
                    spinnerProperty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinnerProperty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProperty = propertiesList.get(position);
                Log.d(TAG, "Selected Property: "+selectedProperty);
                spinnerMeals.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinnerMeals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTypeOfMeal = mealsList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
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

        try{
            String server =  "34.205.83.88";
            String user = "analytics_admin" ;
            String pass = "DpWBOfz871Sa";
            String db = "zolo_analytics_metabase";
            Log.d("TESTING","START");
            Connection connect = conn(user,pass,db,server);
            Log.d("TESTING",""+connect.getMetaData());
            PreparedStatement statement = connect.prepareStatement("EXEC SELECT * FROM Kitchen_menu");
            final ArrayList list = new ArrayList();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("meal_type"));
                Log.d("TESTING",rs.getString("meal_type"));
            }
        } catch (Exception e){
            Log.e("TESTING",""+e.getMessage());
        }


    }

    @SuppressLint("NewApi")
    private Connection conn(String _user, String _pass, String _DB, String _server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            ConnURL = "jdbc:jtds:sqlserver://" + _server + ";"
                    + "databaseName=" + _DB + ";user=" + _user + ";password="
                    + _pass + ";ssl=require";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}
