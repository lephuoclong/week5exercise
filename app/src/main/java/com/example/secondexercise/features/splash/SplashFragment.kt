package com.example.secondexercise.features.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.secondexercise.R
import com.example.secondexercise.databinding.FragmentSplashBinding
import com.example.secondexercise.utils.DataStore
import kotlinx.coroutines.*

class SplashFragment : Fragment() {
    private lateinit var binding : FragmentSplashBinding
    private var i = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataStore.initSharedPref(requireContext())
        val isFirstTime = DataStore.getFirstTimeToggle()
        if(isFirstTime){
            DataStore.writeDataIsFirstTimeToggle()
        }else{
            findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
        }

        binding.loadingIntoApp.visibility =View.VISIBLE;
        binding.loadingIntoApp.max = 100;
        i = binding.loadingIntoApp.progress;

        lifecycleScope.launch {
            while (i<100){
                i+=1
                binding.loadingIntoApp.progress=i
                delay(100)
            }
            binding.loadingIntoApp.visibility = View.INVISIBLE
            findNavController().navigate(R.id.action_splashFragment_to_onboardingOneFragment2)
        }
    }
}