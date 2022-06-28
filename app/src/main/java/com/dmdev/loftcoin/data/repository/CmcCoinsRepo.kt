package com.dmdev.loftcoin.data.repository

import com.dmdev.loftcoin.data.api.CmcApi
import com.dmdev.loftcoin.data.models.*
import com.dmdev.loftcoin.data.models.mappers.CoinsMapper
import com.dmdev.loftcoin.data.room.LoftCoinDatabase
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CmcCoinsRepo @Inject constructor(
    private val api: CmcApi,
    private val db: LoftCoinDatabase,
) : CoinsRepo {

    override fun listings(query: CoinsQuery): Observable<List<Coin>> {
        return Observable
            .fromCallable { query.forceUpdate || db.coins().coinsCount() == 0 }
            .switchMap { f -> if (f) api.listings(query.currency) else Observable.empty() }
            .map { listings -> CoinsMapper.listCoinsToListRoomCoins(listings) }
            .doOnNext { db.coins().insert(it) }
            .switchMap { fetchFromDb(query) }
            .switchIfEmpty { fetchFromDb(query) }
            .subscribeOn(Schedulers.io())
            .map { it }
    }

    override fun coin(currency: Currency, id: Long): Single<Coin> =
        listings(CoinsQuery(currency = currency.code, forceUpdate = true))
            .switchMapSingle { db.coins().fetchOne(id) }
            .firstOrError()
            .map { it }

    private fun fetchFromDb(query: CoinsQuery): Observable<List<RoomCoin>> =
        if (query.sortBy == SortBy.RANK) {
            db.coins().fetchAllOrderedByRank()
        } else {
            db.coins().fetchAllOrderedByPrice()
        }
}