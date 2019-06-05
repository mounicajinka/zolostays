package com.vogella.android.trialapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.vogella.android.trialapplication.db.ZoloFoods;
import com.vogella.android.trialapplication.db.ZoloFoodsDao;

@Database(entities = {ZoloFoods.class}, version = 1,exportSchema = false)

@TypeConverters({ZoloTypeConverters.class})

public abstract class AppDataBase extends RoomDatabase  {

    public abstract ZoloFoodsDao zoloFoodsDao();


}



