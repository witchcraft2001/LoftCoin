package com.dmdev.loftcoin.data.models

import java.util.*

data class Transaction(
    val uid: String,
    val coin: Coin,
    val amount: Double,
    val createdAt: Date
)
