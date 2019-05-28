package com.vogella.android.trialapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences("AppPreference", Context.MODE_PRIVATE);

        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }else {
            startActivity(new Intent(getApplicationContext(), LoginForm.class));
        }
    }
}
