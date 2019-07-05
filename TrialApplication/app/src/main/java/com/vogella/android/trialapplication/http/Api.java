package com.vogella.android.trialapplication.http;

import com.google.gson.JsonObject;
import com.vogella.android.trialapplication.model.AnalysisData;
import com.vogella.android.trialapplication.model.KitchenMenu;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface Api {

    @GET("Kitchen_menu/alldata")
    Call<List<KitchenMenu>> getData();

    @POST("meal_analysis/insert")
    Call<JsonObject> sendData(@Body JSONObject  jsonObject);

    @FormUrlEncoded
    @POST("meal_analysis/insert")
    Call<AnalysisData> saveData(@Field("date") String date, @Field("form_type") String form_type, @Field("item_name") String item_name, @Field("kitchen_name") String kitchen_name,
                             @Field("meal_type") String meal_type, @Field("property_name") String property_name, @Field("type_of_entry") String type_of_entry,
                             @Field("vessel_id") String vessel_id, @Field("weight") String weight);
}

