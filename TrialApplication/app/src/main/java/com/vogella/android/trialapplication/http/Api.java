package com.vogella.android.trialapplication.http;

import com.google.gson.JsonObject;
import com.vogella.android.trialapplication.model.KitchenMenu;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Api {

    @GET("Kitchen_menu/alldata")
    Call<List<KitchenMenu>> getData();

    @POST("meal_analysis/insert")
    Call<JsonObject> sendData(@Body JSONObject jsonObject);

    //Call<User> createUser(@Body User user);
}

