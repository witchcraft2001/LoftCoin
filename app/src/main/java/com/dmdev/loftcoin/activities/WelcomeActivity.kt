package com.dmdev.loftcoin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dmdev.loftcoin.R

class WelcomeActivity : AppCompatActivity() {
    companion object {
        const val KEY_SHOW_WELCOME = "show_welcome"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }
}