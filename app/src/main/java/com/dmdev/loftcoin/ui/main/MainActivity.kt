package com.dmdev.loftcoin.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dmdev.loftcoin.R
import com.dmdev.loftcoin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        //        findNavController()
        with(binding) {
//            bottomNavigationView.setupWithNavController(
//                findNavController(R.id.fragmentContainerView)
//            )
            bottomNavigationView.setupWithNavController(fragmentContainerView.findNavController())
        }
    }
}