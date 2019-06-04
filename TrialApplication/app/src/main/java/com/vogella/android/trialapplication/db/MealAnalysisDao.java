package com.vogella.android.trialapplication.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MealAnalysisDao {

    @Insert(onConflict = REPLACE)
    public void save(MealAnalysis mealAnalysis);
}
