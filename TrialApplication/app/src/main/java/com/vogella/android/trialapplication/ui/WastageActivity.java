package com.vogella.android.trialapplication.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.viewmodels.ZoloFoodsVM;

import java.util.ArrayList;

public class WastageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wastage);

        Spinner spinner = findViewById(R.id.itemsSpinner);

        String city = getIntent().getStringExtra("city");
        String property = getIntent().getStringExtra("property");
        String typeOfMeal = getIntent().getStringExtra("typeOfMeal");

        ZoloFoodsVM model = new ViewModelProvider.AndroidViewModelFactory(App.getInstance()).create(ZoloFoodsVM.class);

        ArrayList<String> items = model.getItemsByData(city, property, typeOfMeal);

        ArrayAdapter<String> mealsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(mealsAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
                Log.d("TAG", "submit wastage: " + edtvWastage.getText().toString());

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
