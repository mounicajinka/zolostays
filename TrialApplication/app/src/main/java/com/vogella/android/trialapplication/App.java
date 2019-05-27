package com.vogella.android.trialapplication;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.vogella.android.trialapplication.http.Api;
import com.vogella.android.trialapplication.http.ApiClient;

import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private AppDataBase db;

    private static Context mContext;

    public Api api;


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        Log.d("Check", "AppContext: "+getApplicationContext());

        db = getDB();
        api = ApiClient.getClient().create(Api.class);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        mContext = getApplicationContext();
    }

    public AppDataBase getDB() {
        if (db == null) {
            db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "ZOLO_FOODS").allowMainThreadQueries().build();
        }
        return db;
    }

    public static App getInstance() {
        return (App) mContext;
    }
}
