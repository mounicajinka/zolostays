package com.vogella.android.trialapplication.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.vogella.android.trialapplication.App;

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


    public static ArrayList<String> getItemsByData(String city, String property, String typeOfMeal,String date) {
        ArrayList<ZoloFoods> data = new ArrayList<>(App.getInstance().getDB().zoloFoodsDao().getData(city, property));
        ArrayList<String> items = new ArrayList<>();

        for (int i=0; i<data.size(); i++) {
            if (data.get(i).getMeal_type().equalsIgnoreCase(typeOfMeal) && data.get(i).getDate().equals(date)) {
                items.add(data.get(i).getItem());
            }
        }

        Set<String> itemsList = new HashSet<>(items);

        return new ArrayList<>(itemsList);
    }


    public static void saveData(ZoloFoods zoloFoods) {
        App.getInstance().getDB().zoloFoodsDao().save(zoloFoods);
    }

   // public static void clearData(ZoloFoods zoloFoods) {
        //zoloFoods = null;
    //}


}
