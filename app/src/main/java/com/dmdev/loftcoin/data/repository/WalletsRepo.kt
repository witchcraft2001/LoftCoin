package com.dmdev.loftcoin.data.repository

import com.dmdev.loftcoin.data.models.Currency
import com.dmdev.loftcoin.data.models.Transaction
import com.dmdev.loftcoin.data.models.Wallet
import io.reactivex.Observable

interface WalletsRepo {
    fun wallets(currency: Currency) : Observable<List<Wallet>>
    fun transactions(wallet: Wallet) : Observable<List<Transaction>>
}