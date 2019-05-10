package com.vogella.android.trialapplication;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

public class App extends Application {

    private AppDataBase db;

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        db = getDB();

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
