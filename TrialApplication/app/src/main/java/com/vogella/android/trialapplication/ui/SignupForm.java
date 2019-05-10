package com.vogella.android.trialapplication.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vogella.android.trialapplication.R;

public class SignupForm extends AppCompatActivity {

    EditText txtFullName,txtEmailID,txtPassword,txtConfirmPassword;
    Button btn_register;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup_form);

        getSupportActionBar().setTitle("Signup Form");

        txtFullName = findViewById(R.id.txt_fullname);

        txtEmailID = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtConfirmPassword = findViewById(R.id.txt_confirm_password);
        btn_register =  findViewById(R.id.buttonRegister);
        progressBar = findViewById(R.id.progressBar);


        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname =txtFullName.getText().toString().trim();
                String email=txtEmailID.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmpassword = txtConfirmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(fullname)){
                    Toast.makeText(getApplicationContext(), "Please Enter Your Full Name", Toast.LENGTH_SHORT).show();
                    return;

                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;

                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;

                }

                if(TextUtils.isEmpty(confirmpassword)){
                    Toast.makeText(getApplicationContext(), "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;

                }

                if(password.length()<8){

                    Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_SHORT).show();

                }

                progressBar.setVisibility(View.VISIBLE);

                if (password.equals(confirmpassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupForm.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);


                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(getApplicationContext(), "Registration Completed", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });

                }






            }
        });





    }
}
