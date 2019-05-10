package com.vogella.android.trialapplication.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.AppDataBase;

import java.util.ArrayList;

public class ZoloFoodsVM extends AndroidViewModel {

    public ZoloFoodsVM(@NonNull Application application) {
        super(application);
    }


    public static ArrayList<ZoloFoods> getDataByUserId() {
        return new ArrayList<>();
    }

    public static ArrayList<String> getAllCities() {

        Log.d("Check", "AppContext: "+App.getInstance());

        return new ArrayList<>(App.getInstance().getDB().zoloFoodsDao().getAllCities());
    }

    public static ArrayList<String> getPropertiesByCity(String city) {
        return new ArrayList<>(App.getInstance().getDB().zoloFoodsDao().getProperties(city));
    }

    public static ArrayList<String> getItemsByData(String city, String property, String typeOfMeal) {
        ArrayList<ZoloFoods> data = new ArrayList<>(App.getInstance().getDB().zoloFoodsDao().getData(city, property));
        ArrayList<String> items = new ArrayList<>();

        for (int i=0; i<data.size(); i++) {
            if (data.get(i).getMeals().getType().equalsIgnoreCase(typeOfMeal)) {
                items.addAll(data.get(i).getMeals().getItems());
            }
        }
        return items;
    }

    public static void saveData(ZoloFoods zoloFoods) {
        App.getInstance().getDB().zoloFoodsDao().save(zoloFoods);
    }

}
