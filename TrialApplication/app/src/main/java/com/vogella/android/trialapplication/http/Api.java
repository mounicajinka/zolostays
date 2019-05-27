package com.vogella.android.trialapplication.http;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    //@GET("Path")
    //    Call<JsonObject> updateProfile(@Header("token") String token, @Body JsonObject jsonObject);
    //
    @GET("http://haematogenous-mista.000webhostapp.com/test.php")
    Call<JsonArray> getData();
}
