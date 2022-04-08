package com.example.secondexercise.features.onBoarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.secondexercise.R
import com.example.secondexercise.databinding.FragmentOnboardingThreeBinding
import com.example.secondexercise.features.welcome.Welcome

class OnboardingThreeFragment : Fragment() {
    private lateinit var binding:FragmentOnboardingThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext3.setOnClickListener {
            goToNextPage()
        }
    }

    private fun goToNextPage(){
       findNavController().navigate(R.id.action_onboardingThreeFragment_to_welcomeFragment)
    }
}