package com.example.secondexercise.features.security

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.secondexercise.R
import com.example.secondexercise.databinding.FragmentLoginBinding
import com.example.secondexercise.features.home.RestaurantActivity
import com.example.secondexercise.utils.DataStore
import com.example.secondexercise.viewmodels.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.imgBtnComeback.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }

        binding.btnGotoSignup.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener(){
            handleLogin()
        }

        binding.containerLayout.setOnTouchListener { v, event ->
            val imm: InputMethodManager =
                requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
            true
        }
    }
    private fun handleLogin() {
        val username = binding.txtUsername.text.toString().trim()
        val password = binding.txtPassword.text.toString().trim()
        val success = viewModel.doLogin(username, password)
        if(success!=null) {
            Toast.makeText(requireActivity(), "Login Success!", Toast.LENGTH_SHORT).show()
            DataStore.initSharedPref(requireContext())
            DataStore.writeUserDataToSharedPref(success)
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }else{
            Toast.makeText(requireActivity(), "Username or password incorrect!", Toast.LENGTH_SHORT).show()
        }
    }
}