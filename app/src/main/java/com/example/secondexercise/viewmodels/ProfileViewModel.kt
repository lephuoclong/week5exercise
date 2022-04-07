package com.example.secondexercise.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.secondexercise.models.User
import com.example.secondexercise.utils.DataStore

class ProfileViewModel : ViewModel() {

    private var _user : MutableLiveData<User> = MutableLiveData()

    val user : LiveData<User>
    get() = _user

    fun editUser(fullname:String, email: String, phone:String){
        val user = User(fullname, email, _user.value?.password!!, phone)
        _user.postValue(user)
    }

    fun getUser(email:String, password:String){
        for (user in DataStore.listUser){
            if (user.email == email && user.password == password){
                _user.postValue(user)
            }
        }
    }
}