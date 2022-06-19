package com.dmdev.loftcoin.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCoin(
    @PrimaryKey
    override val id: Long,
    override val name: String,
    override val symbol: String,
    override val rank: Int,
    override val price: Double,
    override val change24h: Double
): Coin