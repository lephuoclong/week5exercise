package com.example.secondexercise.viewmodels

import androidx.lifecycle.ViewModel
import com.example.secondexercise.models.User
import com.example.secondexercise.utils.DataStore

class LoginViewModel : ViewModel() {


    fun doLogin(email:String, password:String):User?{
        for (user in DataStore.listUser){
            if(email == user.email && password == user.password){
                return user
            }
        }
        return null
    }
}