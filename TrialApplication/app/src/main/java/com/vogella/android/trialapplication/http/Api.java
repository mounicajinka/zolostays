package com.vogella.android.trialapplication.http;

import com.vogella.android.trialapplication.model.KitchenMenu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {

    @GET("test.php")
    Call<List<KitchenMenu>> getData();

    /*@POST(" ")
    Call<List<MealAnalysis> postData;*/


}

