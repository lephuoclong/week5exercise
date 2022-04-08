package com.example.secondexercise.features.onBoarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.secondexercise.R
import com.example.secondexercise.databinding.FragmentOnboardingTwoBinding

class OnboardingTwoFragment : Fragment() {

    private lateinit var binding:FragmentOnboardingTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext2.setOnClickListener {
            goToNextPage()
        }
    }

    private fun goToNextPage(){
        findNavController().navigate(R.id.action_onboardingTwoFragment_to_onboardingThreeFragment)
    }
}