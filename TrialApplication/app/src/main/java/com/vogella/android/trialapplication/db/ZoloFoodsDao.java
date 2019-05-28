package com.vogella.android.trialapplication.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface ZoloFoodsDao {

    @Query("SELECT * FROM zolo_foods where city =:city")
    List<ZoloFoods> getDataByCity(String city);

    @Query("SELECT city FROM zolo_foods ")
    LiveData<List<String>> getAllCities();

    @Query("SELECT property FROM zolo_foods  WHERE city =:city")
    List<String> getProperties(String city);

    @Insert(onConflict = REPLACE)
    void save(ZoloFoods zoloFoods);

    @Query("SELECT * FROM zolo_foods  WHERE isSubmited =:isSubmited")
    List<ZoloFoods> getDataBySubmited(Boolean isSubmited);

    @Query("SELECT * FROM zolo_foods  WHERE city=:city AND property=:property")
    List<ZoloFoods> getData(String city, String property);

    @Query("DELETE FROM zolo_foods")
    void deleteAll();
}