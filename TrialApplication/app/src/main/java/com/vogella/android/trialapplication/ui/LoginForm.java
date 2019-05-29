package com.vogella.android.trialapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.Meals;
import com.vogella.android.trialapplication.db.ZoloFoods;
import com.vogella.android.trialapplication.db.ZoloFoodsVM;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginForm extends AppCompatActivity {

    private static final String TAG = "LoginForm";

    EditText txtEmailID,txtPassword;
    Button btn_login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_form);

        try {
            getSupportActionBar().setTitle("LoginForm");
        }catch (Exception ex) {
            Log.d("Error", "Exception: "+ex.getStackTrace());
        }

        txtEmailID = findViewById(R.id.txt_email);

        txtPassword = findViewById(R.id.txt_password);

        btn_login = findViewById(R.id.buttonLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmailID.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginForm.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginForm.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginForm.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
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
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            }else {
                                                Log.d(TAG, "failure response: "+response.isSuccessful());
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<JsonArray> call, Throwable t) {
                                            Log.d(TAG, "httpCallBack: "+t.getStackTrace());
                                        }
                                    };
                                    App.getInstance().api.getData().enqueue(getNetworkData);
                                    SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences("AppPreference", Context.MODE_PRIVATE);
                                    sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();
                                } else {
                                    Toast.makeText(LoginForm.this, "Login Failed or User Not Available", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void btn_signup_Form(View view) {
        startActivity(new Intent(getApplicationContext(), SignupForm.class));
    }


}