package com.dmdev.loftcoin.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dmdev.loftcoin.LoftCoinApp
import com.dmdev.loftcoin.databinding.ActivityMainBinding
import com.dmdev.loftcoin.di.components.DaggerMainComponent
import com.dmdev.loftcoin.di.components.MainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var component: MainComponent

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        component = DaggerMainComponent.builder()
            .baseComponent((newBase.applicationContext as LoftCoinApp).component)
            .build()
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        with(binding) {
            bottomNavigationView.setupWithNavController(fragmentContainerView.findNavController())
        }
    }
}