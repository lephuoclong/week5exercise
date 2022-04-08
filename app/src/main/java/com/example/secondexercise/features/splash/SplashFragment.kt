package com.example.secondexercise.features.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.secondexercise.R
import com.example.secondexercise.databinding.FragmentSplashBinding
import com.example.secondexercise.features.onBoarding.OnboardingOne
import kotlinx.coroutines.*
import java.lang.Runnable

class SplashFragment : Fragment() {
    private lateinit var binding : FragmentSplashBinding
    private var i = 0
    private var handler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
//        Thread(Runnable {
//            while (i < 100) {
//                i += 1
//                handler.post(Runnable {
//                    binding.loadingIntoApp.progress=i
//                })
//                try {
//                    Thread.sleep(100)
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//            }
//            binding.loadingIntoApp.visibility = View.INVISIBLE
//
//        }).start()
//        findNavController().navigate(R.id.action_splashFragment_to_onboardingOneFragment2)
    }
}