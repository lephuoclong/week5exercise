package com.example.secondexercise.features.profile

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.secondexercise.R
import com.example.secondexercise.databinding.ActivityProfileBinding
import com.example.secondexercise.features.security.Login
import com.example.secondexercise.models.User
import com.example.secondexercise.viewmodels.ProfileViewModel

class Profile : AppCompatActivity() {


    private lateinit var binding:ActivityProfileBinding
    private lateinit var viewModel:ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        //setContentView(R.layout.activity_profile)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        getUser()
        //initializeWidget()

        viewModel.user.observe(this, Observer {
            bindDataUserToView(it)
        })

        binding.btnFullnameProfile.setOnClickListener {
            showDialogProfile()
        }
        binding.btnEmailProfile.setOnClickListener {
            showDialogProfile()
        }
        binding.btnPhoneNumberProfile.setOnClickListener {
            showDialogProfile()
        }
        binding.cardViewBack.setOnClickListener {
            back()
        }
    }

    private fun getUser(){
        val bundle = intent?.extras
        val username = bundle?.getString("username")
        val password = bundle?.getString("password")
        viewModel.getUser(username!!, password!!)
    }

    private fun bindDataUserToView(user: User){
        binding.btnFullnameProfile.text=user.fullname
        binding.btnEmailProfile.text=user.email
        binding.btnPhoneNumberProfile.text=user.phone
        binding.textViewFullnameProfile.text = user.fullname
    }


    private fun back() {
        val intent = Intent(this@Profile, Login::class.java)
        this.finish()
        startActivity(intent)
    }

    private fun showDialogProfile() {
        val dialog = Dialog(this@Profile).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_profile)
            val edtFullname = findViewById<EditText>(R.id.editTextFullnameProfileDialog)
            val edtEmail= findViewById<EditText>(R.id.editTextEmailProfileDialog)
            val edtPhone = findViewById<EditText>(R.id.editTextPhoneNumberProfileDialog)
            val buttonOk = findViewById<Button>(R.id.buttonOkProfileDialog)

            edtFullname.setText(binding.btnFullnameProfile.text.toString().trim())
            edtEmail.setText(binding.btnEmailProfile.text.toString().trim())
            edtPhone.setText(binding.btnPhoneNumberProfile.text.toString().trim())

            buttonOk.setOnClickListener {
                val fullname=edtFullname.text.toString().trim()
                val email = edtEmail.text.toString().trim()
                val phone = edtPhone.text.toString().trim()
                if(fullname.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()){
                    viewModel.editUser(fullname, email, phone)
                }
                cancel()
            }
        }
        dialog.show()
    }

}