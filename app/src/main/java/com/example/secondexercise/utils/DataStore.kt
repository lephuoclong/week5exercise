package com.example.secondexercise.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.secondexercise.models.*
import com.google.gson.Gson
import org.json.JSONArray
import java.io.InputStream
import kotlin.coroutines.coroutineContext

object DataStore {
    val listUser: ArrayList<User> = ArrayList()
    val listRestaurant: ArrayList<Restaurant> = ArrayList()
    lateinit var sharedPreferences: SharedPreferences

    fun initSharedPref(context: Context) {
        sharedPreferences = context.getSharedPreferences(Constants.KEY_SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    fun writeDataIsFirstTimeToggle(){
        sharedPreferences.edit().putBoolean(Constants.KEY_FIRST_TIME_TOGGLE, false).apply()
    }

    fun getFirstTimeToggle():Boolean{
        return sharedPreferences.getBoolean(Constants.KEY_FIRST_TIME_TOGGLE, true)
    }

    fun writeUserDataToSharedPref(user: User){
        val gson = Gson()
        val json = gson.toJson(user)
        sharedPreferences.edit().putString(Constants.KEY_USER_PREF, json).apply()
    }

    fun getUserFromSharedPref() :User?{
        val gson = Gson()
        val json = sharedPreferences.getString(Constants.KEY_USER_PREF, "")
        return gson.fromJson(json, User::class.java)
    }

    fun removeUserDataFromSharedPref(){
        sharedPreferences.edit()
            .remove(Constants.KEY_USER_PREF)
            .apply()
    }

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