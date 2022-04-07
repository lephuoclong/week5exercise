package com.example.secondexercise.features.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.secondexercise.R
import android.content.Intent
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.secondexercise.features.security.Login
import com.example.secondexercise.features.security.Register

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();
        setContentView(R.layout.activity_welcome)
        val signIn  = findViewById<TextView>(R.id.welcome_signin)
        val mSignIn = signIn.text
        val uSignIn = SpannableString(mSignIn)
        uSignIn.setSpan(UnderlineSpan(), 0, uSignIn.length, 0 )
        signIn.text = uSignIn
        signIn.setOnClickListener {
            val intentLoginActivity = Intent(this, Login::class.java)
            startActivity(intentLoginActivity)
        }


        val signUpFaceBook = findViewById<Button>(R.id.welcome_btn_facebook)
        signUpFaceBook.setOnClickListener{
            val intentSignUpActivity = Intent(this, Register::class.java)
            startActivity(intentSignUpActivity)
        }

        val signUpGoogle = findViewById<Button>(R.id.welcome_btn_google)
        signUpGoogle.setOnClickListener{
            val intentSignUpActivity = Intent(this, Register::class.java)
            startActivity(intentSignUpActivity)
        }


    }
//    click to login -> redirect to LoginLayout
//    click to signin -> redirect to SigninLayout
}