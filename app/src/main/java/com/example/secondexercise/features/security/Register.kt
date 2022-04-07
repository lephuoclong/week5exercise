package com.example.secondexercise.features.security

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.secondexercise.R
import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.secondexercise.databinding.ActivityRegisterBinding
import com.example.secondexercise.features.profile.Profile
import com.example.secondexercise.models.User
import com.example.secondexercise.viewmodels.RegisterViewModel
import kotlin.properties.Delegates


class Register : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var viewModel:RegisterViewModel
    private var error:String=""
    private var showPass:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        //setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)


        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        //initializeWidget()

        viewModel.errorToast.observe(this, Observer {
            error = it
        })
        viewModel.showPass.observe(this, Observer {
            showPass = it
        })

        binding.imageViewShowHidePassword.setOnClickListener {
            onClickShowPass()
        }

        binding.buttonSignUp.setOnClickListener {
            goToVerifyActivity()
        }

        binding.btnComeBackWelcome.setOnClickListener {
            finishActivity()
        }

        binding.containerLayout.setOnTouchListener { v, event ->
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            true
        }
    }

    private fun finishActivity() {
        val intent = Intent(this@Register, Login::class.java)
        this.finish()
        startActivity(intent)
    }

    private fun goToVerifyActivity(){
        val fullName = binding.editTextFullnameSignup.text.toString().trim()
        val email = binding.editTextEmailSignup.text.toString().trim()
        val password = binding.editTextPasswordSignUp.text.toString().trim()
        val user = User(fullName, email, password)
        val success = viewModel.doSignUp(user)
        if(success){
            Toast.makeText(this@Register, "Sign up success!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Register, Login::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
    private fun onClickShowPass() {
        viewModel.doShowHidePassword()
        if(showPass){
            binding.editTextPasswordSignUp.transformationMethod=PasswordTransformationMethod.getInstance()
            binding.imageViewShowHidePassword.setImageResource(R.drawable.ic_visibility)
        }else{
            binding.editTextPasswordSignUp.transformationMethod=HideReturnsTransformationMethod.getInstance()
            binding.imageViewShowHidePassword.setImageResource(R.drawable.ic_visibility_off)
        }
    }

}


