package com.vogella.android.trialapplication.ui;

import android.content.Intent;
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
import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.db.Meals;
import com.vogella.android.trialapplication.db.ZoloFoods;
import com.vogella.android.trialapplication.db.ZoloFoodsVM;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginForm extends AppCompatActivity {

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

//                                    TODO: do network calls here
//                                    GET@(database url)


//
//               JSONObject jsonObject = new JSONObject();
//
//                                    try {
//                                        String property = jsonObject.getString("property");
//
//                                        String man
//
//
//                                        ZoloFoods zoloFoods = new ZoloFoods( "", "", property, new Meals("", new ArrayList<String>(), 0), false);
//                                        ZoloFoodsVM.saveData(zoloFoods);

                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }

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
