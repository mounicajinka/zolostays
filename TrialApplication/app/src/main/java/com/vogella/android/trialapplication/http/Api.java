package com.vogella.android.trialapplication.http;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {

    @GET("test.php")
    Call<JsonArray> getData();

    /*@POST("http://localhost:3000/meal_analysis/insert ")*/
    /*Call<JsonArray> postData();*/
/*
*/

}

