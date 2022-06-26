package com.dmdev.loftcoin.data.repository

import com.dmdev.loftcoin.data.models.Currency
import com.dmdev.loftcoin.data.models.Transaction
import com.dmdev.loftcoin.data.models.Wallet
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletsRepoImpl @Inject constructor(): WalletsRepo {
    override fun wallets(currency: Currency): Observable<List<Wallet>> {
        TODO("Not yet implemented")
    }

    override fun transactions(wallet: Wallet): Observable<List<Transaction>> {
        TODO("Not yet implemented")
    }
}