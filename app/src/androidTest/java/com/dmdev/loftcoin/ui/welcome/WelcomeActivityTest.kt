package com.dmdev.loftcoin.ui.welcome

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dmdev.loftcoin.R
import com.dmdev.loftcoin.ui.main.MainActivity
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeActivityTest {

    @Test
    fun open_main_if_button_being_pressed() {
        launchActivity<WelcomeActivity>()
        Intents.init()
        onView(withId(R.id.button)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(MainActivity::class.qualifiedName))
    }

    @After
    fun tearDown() {
        Intents.release()
    }
}