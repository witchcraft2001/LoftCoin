package com.dmdev.loftcoin.data.models

interface Coin {
    val id: Long
    val name: String
    val symbol: String
    val rank: Int
    val price: Double
    val change24h: Double
}