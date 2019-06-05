package com.vogella.android.trialapplication.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface ZoloFoodsDao {

    @Query("SELECT * FROM zolo_foods where city =:city")
    public List<ZoloFoods> getDataByCity(String city);

    @Query("SELECT city FROM zolo_foods ")
    public List<String> getAllCities();

    @Query("SELECT property FROM zolo_foods  WHERE city =:city")
    public List<String> getProperties(String city);

    @Insert(onConflict = REPLACE)
    public void save(ZoloFoods zoloFoods);

    @Query("SELECT * FROM zolo_foods  WHERE isSubmited =:isSubmited")
    public List<ZoloFoods> getDataBySubmited(Boolean isSubmited);

    @Query("SELECT * FROM zolo_foods  WHERE city=:city AND property=:property")
    public List<ZoloFoods> getData(String city, String property);




}