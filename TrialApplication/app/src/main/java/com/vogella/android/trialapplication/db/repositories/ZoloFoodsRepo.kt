package com.vogella.android.trialapplication.db.repositories

import android.arch.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import com.vogella.android.trialapplication.App
import com.vogella.android.trialapplication.db.Meals
import com.vogella.android.trialapplication.db.ZoloFoods
import com.vogella.android.trialapplication.http.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ZoloFoodsRepo {

    private val db by lazy { App.getInstance().db.zoloFoodsDao() }

    fun getDataFromAPI() {
        val call = ApiClient.createApiClient().data
        call.enqueue(object : Callback<JsonArray> {
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {

            }

            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                val list = parseApiData(response.body()) ?: return
                convertListAndSaveTODB(list)
            }
        })
    }

    private fun parseApiData(array: JsonArray?): ArrayList<Temp>? {
        return Gson().fromJson<ArrayList<Temp>>(array, object : TypeToken<ArrayList<Temp>>() {}.type)
    }

    private fun convertListAndSaveTODB(list: ArrayList<Temp>) {
        for (data in list) {
            val meal = Meals(data.meal_type, arrayListOf(data.item_name), null)
            val zoloFood = ZoloFoods(data.username, data.usercity, data.userhotel, data.daily_date, meal, null)
            db.save(zoloFood)
        }
    }

    fun getAllCities(): LiveData<List<String>>? {
        return db.allCities
    }

    fun getPropertiesByCity(city: String): List<String> {
        return db.getProperties(city)
    }

    fun getData(city: String, property: String): List<ZoloFoods> {
        return db.getData(city, property)
    }

    data class Temp(
            val daily_date: String,
            val id: String,
            val item_name: String,
            val meal_type: String,
            val usercity: String,
            val userhotel: String,
            val username: String
    )
}