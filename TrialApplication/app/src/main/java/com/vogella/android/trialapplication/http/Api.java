package com.vogella.android.trialapplication.http;

import com.vogella.android.trialapplication.db.ZoloFoods;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    //@GET("Path")
    //    Call<JsonObject> updateProfile(@Header("token") String token, @Body JsonObject jsonObject);
    //
    @GET("/test.php")
    Call<List<ZoloFoods>> getFood();
}
