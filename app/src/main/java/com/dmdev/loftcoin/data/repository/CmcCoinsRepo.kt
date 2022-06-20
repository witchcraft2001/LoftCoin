package com.dmdev.loftcoin.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dmdev.loftcoin.data.api.CmcApi
import com.dmdev.loftcoin.data.models.CmcCoin
import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.models.CoinsQuery
import com.dmdev.loftcoin.data.models.RoomCoin
import com.dmdev.loftcoin.data.room.LoftCoinDatabase
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.ExecutorService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CmcCoinsRepo @Inject constructor(
    private val api: CmcApi,
    private val db: LoftCoinDatabase,
    private val executor: ExecutorService
) : CoinsRepo {

    override fun listings(currency: String): List<CmcCoin> {
        val result = api.listings(currency).execute()
        if (result.isSuccessful) {
            val items = result.body()
            return items?.data ?: throw IllegalStateException("Unknown server response")
        } else {
            val error = result.errorBody()
            throw IOException(error?.string() ?: "Unknown server response")
        }
    }

    override fun listings(query: CoinsQuery): LiveData<List<Coin>> {
        val refresh = MutableLiveData<Boolean>()
        executor.submit {
            refresh.postValue(query.forceUpdate || db.coins().coinsCount() == 0)
        }

        return Transformations.switchMap(refresh) {
            if (refresh.value == true) {
                fetchFromNetwork(query)
            } else {
                fetchFromDb(query)
            }
        }
    }

    private fun fetchFromDb(query: CoinsQuery): LiveData<List<Coin>> {
        return Transformations.map(db.coins().fetchAll()) { it }
    }

    private fun fetchFromNetwork(query: CoinsQuery): LiveData<List<Coin>> {
        val liveData = MutableLiveData<List<Coin>>()
        executor.submit {
            try {
                val result = api.listings(query.currency).execute()
                if (result.isSuccessful) {
                    val items = result.body()
                    if (items?.data != null) {
                        val coins = items.data
                        saveCoinsIntoDb(coins)
                        liveData.postValue(coins)
                    } else {
                        throw IllegalStateException("Unknown server response")
                    }
                } else {
                    val error = result.errorBody()
                    throw IOException(error?.string() ?: "Unknown server response")
                }
            } catch (e: Throwable) {
                Timber.e(e)
            }
        }
        return liveData
    }

    private fun saveCoinsIntoDb(coins: List<Coin>) {
        val roomCoins = coins.map { RoomCoin(it.id, it.name, it.symbol, it.rank, it.price, it.change24h) }
        db.coins().insert(roomCoins)
    }
}