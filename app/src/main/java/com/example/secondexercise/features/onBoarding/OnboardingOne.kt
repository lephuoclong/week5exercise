package com.example.secondexercise.features.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import com.example.secondexercise.R

class OnboardingOne : AppCompatActivity() {
    private var btnNext1:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();
        setContentView(R.layout.activity_onboarding_one)

        init()

        btnNext1?.setOnClickListener {
            goToNextActivity()
        }
    }

    private fun init(){
        btnNext1 = findViewById(R.id.btn_next_1)
    }

    private fun goToNextActivity(){
        var intent = Intent(this, OnboardingTwo::class.java)
        startActivity(intent)
    }


}