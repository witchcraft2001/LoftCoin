package com.dmdev.loftcoin.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.dmdev.loftcoin.R

class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        runnable = if (sharedPreferences.getBoolean(WelcomeActivity.KEY_SHOW_WELCOME, true)) {
            Runnable {
                startActivity(Intent(this, WelcomeActivity::class.java))
            }
        } else {
            Runnable { startActivity(Intent(this, MainActivity::class.java)) }
        }
        handler.postDelayed(runnable, 1500)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}