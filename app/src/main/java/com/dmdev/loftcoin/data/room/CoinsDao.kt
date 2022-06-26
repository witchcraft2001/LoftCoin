package com.dmdev.loftcoin.data.room

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dmdev.loftcoin.data.models.RoomCoin
import io.reactivex.Observable

@Dao
interface CoinsDao {
    @Query("SELECT * from RoomCoin")
    fun fetchAll(): Observable<List<RoomCoin>>

    @Query("SELECT * from RoomCoin ORDER BY rank")
    fun fetchAllOrderedByRank(): Observable<List<RoomCoin>>

    @Query("SELECT * from RoomCoin ORDER BY price")
    fun fetchAllOrderedByPrice(): Observable<List<RoomCoin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coins: List<RoomCoin>)

    @WorkerThread
    @Query("SELECT COUNT(id) FROM RoomCoin")
    fun coinsCount() : Int
}