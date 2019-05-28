package com.vogella.android.trialapplication.db.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.vogella.android.trialapplication.db.repositories.ZoloFoodsRepo
import java.util.*

class ZoloFoodsVM : ViewModel() {

    private val repo by lazy { ZoloFoodsRepo() }

    init {
        repo.getDataFromAPI()
    }

    fun getallCities(): LiveData<List<String>>? {
        return repo.getAllCities()
    }

    fun getPropertiesByCity(city: String): List<String> {
        return repo.getPropertiesByCity(city)
    }

    fun getItemsByData(city: String, property: String, typeOfMeal: String): ArrayList<String> {
        val data = repo.getData(city, property)
        val items = ArrayList<String>()

        for (i in data.indices) {
            if (data[i].meals.type.equals(typeOfMeal, ignoreCase = true)) {
                items.addAll(data[i].meals.items)
            }
        }
        return items
    }
}