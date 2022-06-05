package com.dmdev.loftcoin.ui.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.dmdev.loftcoin.R
import com.dmdev.loftcoin.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    companion object {
        const val KEY_SHOW_WELCOME = "show_welcome"
    }

    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var helper: SnapHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        helper = PagerSnapHelper()
        binding.recycler.also {
            it.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL, false)
            it.adapter = WelcomeAdapter()
            helper.attachToRecyclerView(it)
        }
    }

    override fun onDestroy() {
        helper.attachToRecyclerView(null)
        binding.recycler.adapter = null
        super.onDestroy()
    }
}