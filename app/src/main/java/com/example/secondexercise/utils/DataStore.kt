package com.example.secondexercise.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.secondexercise.models.*
import com.google.gson.Gson
import org.json.JSONArray
import java.io.InputStream

object DataStore {
    val listUser: ArrayList<User> = ArrayList()
    val listRestaurant: ArrayList<Restaurant> = ArrayList()

    fun readJson(context: Context){
        listRestaurant.clear()
        var json: String? = null
        try {
            val inputStream: InputStream = context.assets.open("data_restaurants.json")
            json = inputStream.bufferedReader().use { it.readText() }
            Toast.makeText(context, "json $json", Toast.LENGTH_LONG).show()
            val gson = Gson()
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val restaurant = gson.fromJson(jsonObject.toString(), Restaurant::class.java)
                listRestaurant.add(restaurant)
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
        }
    }
}