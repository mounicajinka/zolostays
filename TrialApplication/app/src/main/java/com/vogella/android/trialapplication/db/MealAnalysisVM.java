package com.vogella.android.trialapplication.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.vogella.android.trialapplication.App;

public class MealAnalysisVM  extends AndroidViewModel {

    public MealAnalysisVM(@NonNull Application application) {
        super(application);
    }
    public static void saveData(MealAnalysis mealAnalysis) {
        App.getInstance().getDB().MealAnalysisDao().save(mealAnalysis);
    }

    public static void clearData(MealAnalysis mealAnalysis) {
        mealAnalysis = null;
    }


}
