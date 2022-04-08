package com.example.secondexercise.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.secondexercise.models.Restaurant
import com.example.secondexercise.utils.DataStore


class RestaurantViewModel : ViewModel() {
    private var _listRestaurant :MutableLiveData<ArrayList<Restaurant>> = MutableLiveData()
    private var _menuOptions: MutableLiveData<Boolean> = MutableLiveData(false)
    var LISTVIEW_OPTION = 1
    var GRIDVIEW_OPTION = 2
    var CURRENT_OPTION = LISTVIEW_OPTION
    val menuOptions:LiveData<Boolean>
    get() = _menuOptions

    fun setValueToCurrentOption(value:Int){
        CURRENT_OPTION = value
    }

    val listRestaurant : LiveData<ArrayList<Restaurant>>
    get() = _listRestaurant

    fun getListRestaurant(context: Context) {
        DataStore.readJson(context)
        _listRestaurant.postValue(DataStore.listRestaurant)
    }

    fun onChangeMenu(value:Boolean){
        _menuOptions.postValue(value)
    }
    fun removeRestaurantItem(restaurant: Restaurant){
        _listRestaurant.value?.remove(restaurant)
    }
}