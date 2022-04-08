package com.example.secondexercise.features.security

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.secondexercise.R
import com.example.secondexercise.databinding.FragmentRegisterBinding
import com.example.secondexercise.models.User
import com.example.secondexercise.viewmodels.RegisterViewModel

class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private var error:String=""
    private var showPass:Boolean=false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        viewModel.errorToast.observe(viewLifecycleOwner, Observer  {
            error = it
        })
        viewModel.showPass.observe(viewLifecycleOwner, Observer {
            showPass = it
        })

        binding.imageViewShowHidePassword.setOnClickListener {
            onClickShowPass()
        }

        binding.buttonSignUp.setOnClickListener {
            goToLoginPage()
        }

        binding.btnComeBackWelcome.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


        binding.containerLayout.setOnTouchListener { _, _ ->
            val imm: InputMethodManager =
                requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
            true
        }
    }

    private fun goToLoginPage(){
        val fullName = binding.editTextFullnameSignup.text.toString().trim()
        val email = binding.editTextEmailSignup.text.toString().trim()
        val password = binding.editTextPasswordSignUp.text.toString().trim()
        val user = User(fullName, email, password)
        val success = viewModel.doSignUp(user)
        if(success){
            Toast.makeText(requireContext(), "Sign up success!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }else{
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }
    private fun onClickShowPass() {
        viewModel.doShowHidePassword()
        if(showPass){
            binding.editTextPasswordSignUp.transformationMethod= PasswordTransformationMethod.getInstance()
            binding.imageViewShowHidePassword.setImageResource(R.drawable.ic_visibility)
        }else{
            binding.editTextPasswordSignUp.transformationMethod= HideReturnsTransformationMethod.getInstance()
            binding.imageViewShowHidePassword.setImageResource(R.drawable.ic_visibility_off)
        }
    }
}