package com.dmdev.loftcoin.ui.rates

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dmdev.loftcoin.BuildConfig
import com.dmdev.loftcoin.data.FakeCoin
import com.dmdev.loftcoin.data.FakeCoinsRepo
import com.dmdev.loftcoin.data.FakeCurrencyRepo
import com.dmdev.loftcoin.data.repository.CoinsRepo
import com.dmdev.loftcoin.data.repository.CurrencyRepo
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RatesViewModelTest {

    private lateinit var viewModel: RatesViewModel
    private lateinit var coinsRepo: FakeCoinsRepo
    private lateinit var currencyRepo: FakeCurrencyRepo

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val sharePreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
        coinsRepo = FakeCoinsRepo()
        currencyRepo = FakeCurrencyRepo(context, sharePreferences)
        viewModel = RatesViewModel(coinsRepo, currencyRepo)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun coins() {
        val coinTests = viewModel.getCoins().test()
//        viewModel.isLoading().test().assertValue(true)
        val coins = listOf(FakeCoin(), FakeCoin())
        coinsRepo.listings.onNext(coins)
//        viewModel.isLoading().test().assertValue(false)
        coinTests.assertValue(coins)
    }
}