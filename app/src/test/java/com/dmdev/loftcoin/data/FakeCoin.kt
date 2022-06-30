package com.dmdev.loftcoin.data

import com.dmdev.loftcoin.data.models.Coin

class FakeCoin: Coin {
    override val id: Long = 0
    override val name: String = ""
    override val symbol: String = ""
    override val rank: Int = 0
    override val price: Double = 0.0
    override val change24h: Double = 0.0
}