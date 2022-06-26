package com.dmdev.loftcoin.data.models

data class Wallet(
    val uid: String,
    val coin: Coin,
    val balance: Double
)
