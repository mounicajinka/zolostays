package com.vogella.android.trialapplication.http;

import com.vogella.android.trialapplication.model.KitchenMenu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {

    @GET("http://localhost:3000/Kitchen_menu/userdata/")
    Call<List<KitchenMenu>> getData();

    /*@POST(" ")
    Call<List<MealAnalysis> postData;*/


}

