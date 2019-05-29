package com.vogella.android.trialapplication.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.AppDataBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ZoloFoodsVM extends AndroidViewModel {


    public ZoloFoodsVM(@NonNull Application application) {
        super(application);
    }

    public static ArrayList<ZoloFoods> getDataByUserId() {
        return new ArrayList<>();
    }

    public static ArrayList<String> getAllCities() {
        Set<String> cities = new HashSet<>(App.getInstance().getDB().zoloFoodsDao().getAllCities());
        return new ArrayList<>(cities);
    }

    public static ArrayList<String> getPropertiesByCity(String city) {
        Set<String> properties = new HashSet<>(App.getInstance().getDB().zoloFoodsDao().getProperties(city));
        return new ArrayList<>(properties);
    }

    public static ArrayList<String> getItemsByData(String city, String property, String typeOfMeal) {
        ArrayList<ZoloFoods> data = new ArrayList<>(App.getInstance().getDB().zoloFoodsDao().getData(city, property));
        ArrayList<String> items = new ArrayList<>();

        for (int i=0; i<data.size(); i++) {
            if (data.get(i).getMeals().getType().equalsIgnoreCase(typeOfMeal)) {
                items.add(data.get(i).getMeals().getItem());
            }
        }

        Set<String> itemsList = new HashSet<>(items);

        return new ArrayList<>(itemsList);
    }

    //whatever response we get from json format/response from user,which one do we need to save in our app database.Generally
    //info is stored in app database to help us in such cases when there is no internet anf the data cannot be sent to server
    //App database is like backup database to save data temporarily and then when internet works the data can be sent to server.

    public static void saveData(ZoloFoods zoloFoods) {
        App.getInstance().getDB().zoloFoodsDao().save(zoloFoods);
    }

}
