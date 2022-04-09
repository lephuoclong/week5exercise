package com.example.secondexercise.features.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.secondexercise.R
import com.example.secondexercise.databinding.FragmentProfileBinding
import com.example.secondexercise.features.security.Login
import com.example.secondexercise.models.User
import com.example.secondexercise.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
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
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
    }

    private fun getUser(){
        val bundle = arguments
        if(bundle!=null){
            val username = bundle.getString("username")
            val password = bundle.getString("password")
            viewModel.getUser(username!!, password!!)
        }
    }

    private fun bindDataUserToView(user: User){
        binding.btnFullnameProfile.text=user.fullname
        binding.btnEmailProfile.text=user.email
        binding.btnPhoneNumberProfile.text=user.phone
        binding.textViewFullnameProfile.text = user.fullname
    }

    private fun showDialogProfile() {
        val dialog = Dialog(requireContext()).apply {
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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.btnlogout.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_profileFragment_to_welcomeFragment)
        }
    }
}