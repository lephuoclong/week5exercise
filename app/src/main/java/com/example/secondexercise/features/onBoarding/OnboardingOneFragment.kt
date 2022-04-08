package com.example.secondexercise.features.onBoarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.secondexercise.R
import com.example.secondexercise.databinding.FragmentOnboardingOneBinding

class OnboardingOneFragment : Fragment() {
    private lateinit var binding:FragmentOnboardingOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext1.setOnClickListener {
            goToNextPage()
        }
    }

    private fun goToNextPage(){
       findNavController().navigate(R.id.action_onboardingOneFragment_to_onboardingTwoFragment)
    }
}