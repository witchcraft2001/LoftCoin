package com.dmdev.loftcoin.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dmdev.loftcoin.data.models.RoomCoin

@Database(entities = [RoomCoin::class], version = 1)
abstract class LoftCoinDatabase : RoomDatabase() {
    abstract fun coins(): CoinsDao
}