package com.dmdev.loftcoin.ui.wallets

import androidx.lifecycle.ViewModel
import com.dmdev.loftcoin.data.models.Wallet
import com.dmdev.loftcoin.data.repository.CurrencyRepo
import com.dmdev.loftcoin.data.repository.WalletsRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

class WalletsViewModel @Inject constructor(
    walletsRepo: WalletsRepo,
    currencyRepo: CurrencyRepo
) : ViewModel() {

    private val wallets = currencyRepo.currency().switchMap { walletsRepo.wallets(it) }.doOnEach {
        Timber.d(it.toString())
    }

    fun getWallets(): Observable<List<Wallet>> = wallets.observeOn(AndroidSchedulers.mainThread())

}