package com.dmdev.loftcoin.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.dmdev.loftcoin.databinding.ActivityWelcomeBinding
import com.dmdev.loftcoin.ui.main.MainActivity
import com.dmdev.loftcoin.ui.views.PointIndicator

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
            it.setHasFixedSize(true)
            it.addItemDecoration(PointIndicator(this))
            helper.attachToRecyclerView(it)
        }
        binding.button.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(KEY_SHOW_WELCOME, false).apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        helper.attachToRecyclerView(null)
        binding.recycler.adapter = null
        super.onDestroy()
    }
}