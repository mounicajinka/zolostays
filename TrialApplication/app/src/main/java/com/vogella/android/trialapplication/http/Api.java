package com.vogella.android.trialapplication.http;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("http://haematogenous-mista.000webhostapp.com/test.php")
    Call<JsonArray> getData();
}

