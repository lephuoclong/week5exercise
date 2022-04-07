package com.example.secondexercise.features.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.Window
import android.widget.Button
import com.example.secondexercise.R


class OnboardingTwo : AppCompatActivity() {
    private var btnNext2: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();
        setContentView(R.layout.activity_onboarding_two)

        init()

        btnNext2?.setOnClickListener {
            goToNextActivity()
        }

    }

    private fun init(){
        btnNext2 = findViewById(R.id.btn_next_2)
    }

    private fun goToNextActivity(){
        var intent = Intent(this, OnboardingThree::class.java)
        startActivity(intent)
    }

}