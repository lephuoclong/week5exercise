package com.example.secondexercise.viewmodels

import androidx.lifecycle.ViewModel
import com.example.secondexercise.utils.DataStore

class LoginViewModel : ViewModel() {

    fun doLogin(email:String, password:String):Boolean{
        for (user in DataStore.listUser){
            if(email == user.email && password == user.password){
                return true
            }
        }
        return false
    }
}