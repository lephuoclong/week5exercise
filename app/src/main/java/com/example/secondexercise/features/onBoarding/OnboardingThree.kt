package com.example.secondexercise.features.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import com.example.secondexercise.R
import com.example.secondexercise.features.welcome.Welcome

class OnboardingThree : AppCompatActivity() {
    private var btnNext3: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();
        setContentView(R.layout.activity_onboarding_three)

        init()

        btnNext3?.setOnClickListener {
            goToNextActivity()
        }
    }

    private fun init(){
        btnNext3 = findViewById(R.id.btn_next_3)
    }

    private fun goToNextActivity(){
        var intent = Intent(this, Welcome::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}