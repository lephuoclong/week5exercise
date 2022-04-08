package com.example.secondexercise.features.security

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.secondexercise.R

import android.content.Intent
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.secondexercise.features.home.RestaurantActivity
import com.example.secondexercise.viewmodels.LoginViewModel

class Login : AppCompatActivity() {
//    private var btnGoBackWelcome:ImageButton?=null
//    private var txtUsername:EditText?=null
//    private var txtPassword:EditText?=null
//    private var btnLogin:Button?=null
//    private var containerLayout: ConstraintLayout?=null
//    private var btnGotoSignUp:Button?=null

    private lateinit var binding: com.example.secondexercise.databinding.ActivityLoginBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        //setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        //initializeWidget()

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.imgBtnComeback.setOnClickListener{
            this.finish()
        }

        binding.btnGotoSignup.setOnClickListener{
            var intent = Intent(this@Login, Register::class.java)
            this.finish()
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener(){
            handleLogin()
        }

        binding.containerLayout.setOnTouchListener { v, event ->
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            true
        }

    }

    private fun handleLogin() {
        val username = binding.txtUsername.text.toString().trim()
        val password = binding.txtPassword.text.toString().trim()
        val success = viewModel.doLogin(username, password)
        if(success) {
            Toast.makeText(this@Login, "Login Success!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Login, RestaurantActivity::class.java)
            val bundle = Bundle()
            bundle.putString("username", username)
            bundle.putString("password", password)
            intent.putExtras(bundle)
            startActivity(intent)
        }else{
            Toast.makeText(this@Login, "Username or password incorrect!", Toast.LENGTH_SHORT).show()
        }
    }

//    private fun initializeWidget() {
//        btnGoBackWelcome = findViewById(R.id.imgBtnComeback)
//        txtUsername = findViewById(R.id.txtUsername)
//        txtPassword = findViewById(R.id.txtPassword)
//        btnLogin = findViewById(R.id.btnLogin)
//        btnGotoSignUp = findViewById(R.id.btnGotoSignup)
//        containerLayout = findViewById(R.id.containerLayout)
//    }
}