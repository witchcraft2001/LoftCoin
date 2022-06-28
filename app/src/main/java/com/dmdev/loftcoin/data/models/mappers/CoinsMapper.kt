package com.dmdev.loftcoin.data.models.mappers

import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.models.Listings
import com.dmdev.loftcoin.data.models.RoomCoin

object CoinsMapper {
    fun coinToRoomCoin(coin: Coin): RoomCoin =
        RoomCoin(coin.id, coin.name, coin.symbol, coin.rank, coin.price, coin.change24h)

    fun listCoinsToListRoomCoins(coins: Listings): List<RoomCoin> =
        coins.data.map { coinToRoomCoin(it) }
}