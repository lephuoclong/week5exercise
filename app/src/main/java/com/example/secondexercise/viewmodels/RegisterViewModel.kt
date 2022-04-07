package com.example.secondexercise.viewmodels

import androidx.core.content.res.TypedArrayUtils.getText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.secondexercise.models.User
import com.example.secondexercise.utils.DataStore
import java.util.regex.Pattern

class RegisterViewModel : ViewModel() {

    private var _showPass :MutableLiveData<Boolean> = MutableLiveData(false)
    private var _errorToast:MutableLiveData<String> = MutableLiveData("Vui lòng nhập đủ thông tin")

    val errorToast:LiveData<String>
    get() = _errorToast


    val showPass :LiveData<Boolean>
    get() = _showPass

    fun doSignUp(user: User):Boolean{
        if(user.fullname.isEmpty()){
            _errorToast.postValue("Fullname không được để trống")
            return false
        }
        if (user.password.isEmpty()){
            _errorToast.postValue("Password không được để trống")
            return false
        }
        if(user.email.isEmpty()){
            _errorToast.postValue("Email không được để trống")
            return false
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()){
            _errorToast.postValue("Email không đúng định dạng")
            return false
        }

        if(!isValidPassword(user.password.toString())){
            _errorToast.postValue("Password không đúng định dạng")
            return false
        }

        DataStore.listUser.add(user)
        return true
    }

    fun doShowHidePassword(){
        if(_showPass.value == false){
            _showPass.postValue(true)
        }else{
            _showPass.postValue(false)
        }
    }


    private fun isValidPassword(password:String):Boolean{
        var valid = true

        //Check length password
        if(password.length < 8) valid = false

        //Check capital letter
        var exp = ".*[A-Z].*"
        var pattern = Pattern.compile(exp)
        var matcher = pattern.matcher(password)

        if(!matcher.matches()){
            valid = false
        }

        //Check number

        exp = ".*[0-9].*"
        pattern = Pattern.compile(exp,Pattern.CASE_INSENSITIVE)
        matcher = pattern.matcher(password)

        if(!matcher.matches()){
            valid = false
        }

        //Check small letter

        exp = ".*[a-z].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(password)

        if(!matcher.matches()){
            valid = false
        }


        //Check special character

        exp = ".*[!@#$%^&*()].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(password)

        if(!matcher.matches()){
            valid = false
        }

        return valid


    }
}