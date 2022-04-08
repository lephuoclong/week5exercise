package com.example.secondexercise.features.welcome

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.secondexercise.R
import com.example.secondexercise.databinding.FragmentWelcomeBinding
import com.example.secondexercise.features.security.Login
import com.example.secondexercise.features.security.Register

class WelcomeFragment : Fragment() {

    private lateinit var binding:FragmentWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mSignIn = binding.welcomeSignin.text
        val uSignIn = SpannableString(mSignIn)
        uSignIn.setSpan(UnderlineSpan(), 0, uSignIn.length, 0 )
        binding.welcomeSignin.text = uSignIn
        binding.welcomeSignin.setOnClickListener {
            goToLoginPage()
        }

        binding.welcomeBtnFacebook.setOnClickListener{
            goToRegisterPage()
        }

        binding.welcomeBtnGoogle.setOnClickListener{
            goToRegisterPage()
        }
    }

    private fun goToLoginPage(){
        findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
    }
    private fun goToRegisterPage(){
        findNavController().navigate(R.id.action_welcomeFragment_to_registerFragment)
    }
}