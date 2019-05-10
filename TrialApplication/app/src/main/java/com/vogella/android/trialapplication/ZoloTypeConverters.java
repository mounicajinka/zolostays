package com.vogella.android.trialapplication;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public final class ZoloTypeConverters {

    @TypeConverter
    public static ArrayList<String> getListFromString(String data) {

        Type listType = new TypeToken<ArrayList<String>>() {

        }.getType();
        return new Gson().fromJson(data, listType);
    }

    @TypeConverter
    public static String getStringFromList(ArrayList<String> data) {

        Gson gson = new Gson();
        return gson.toJson(data);
    }


}
