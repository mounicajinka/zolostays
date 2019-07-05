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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.ZoloFoods;
import com.vogella.android.trialapplication.db.ZoloFoodsVM;
import com.vogella.android.trialapplication.model.KitchenMenu;

import java.util.List;

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

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginForm.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    final Callback<List<KitchenMenu>> data = new Callback<List<KitchenMenu>>() {
                                        @Override
                                        public void onResponse(Call<List<KitchenMenu>> call, Response<List<KitchenMenu>> response) {
                                            if (response.isSuccessful()) {
                                                Log.d(TAG, "httpCallBack: " + response.body());
                                                try{
                                                    List<KitchenMenu> KitchenData = response.body();
                                                    for (int i = 0; i < KitchenData.size(); i++) {

                                                        String manager = KitchenData.get(i).getManager();
                                                        int id = KitchenData.get(i).getId();
                                                        String city  = KitchenData.get(i).getCity();
                                                        System.out.println("hhhhhc "+city);
                                                        String property = KitchenData.get(i).getProperty();
                                                        System.out.println("hhhhhpc  "+property);
                                                        String date = KitchenData.get(i).getDate();
                                                        String mealType = KitchenData.get(i).getMealtype();
                                                        String itemName = KitchenData.get(i).getItem();
                                                        System.out.println("hhhhh "+mealType);
                                                        ZoloFoods zoloFoods =new ZoloFoods(id,manager,city,property,date,mealType,itemName, false);
                                                        ZoloFoodsVM.saveData(zoloFoods);

                                                    }
                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                    SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences("AppPreference", Context.MODE_PRIVATE);
                                                    sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();
                                                    finish();

                                                }catch (Exception e){
                                                    Log.d("onResponse", "There is an error");
                                                    e.printStackTrace();
                                                }
                                            }
                                        else{ Log.d(TAG, "failure response: "+response.isSuccessful());

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<KitchenMenu>> call, Throwable t ) {
                                            Log.d(TAG, "httpCallBack: "+t.getMessage());

                                        }
                                    };
                                    App.getInstance().api.getData().enqueue(data);
                                }
                                else {
                                    Toast.makeText(LoginForm.this, "Login Failed or User Not Available", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }



    public void btn_signup_Form(View view) {
        startActivity(new Intent(getApplicationContext(), SignupForm.class));
        finish();
    }


}



