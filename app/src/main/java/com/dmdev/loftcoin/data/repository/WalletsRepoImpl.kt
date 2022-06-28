package com.dmdev.loftcoin.data.repository

import com.dmdev.loftcoin.data.models.Currency
import com.dmdev.loftcoin.data.models.Transaction
import com.dmdev.loftcoin.data.models.Wallet
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletsRepoImpl @Inject constructor(
    private val coinsRepo: CoinsRepo
) : WalletsRepo {
    companion object {
        private const val WALLETS_COLLECTION_PATH = "wallets"
        private const val WALLETS_COIN_ID_FIELDNAME = "coinId"
        private const val WALLETS_BALANCE_FIELDNAME = "balance"
    }

    private val firestore = FirebaseFirestore.getInstance()

    override fun wallets(currency: Currency): Observable<List<Wallet>> {
        return Observable.create<QuerySnapshot> { emitter ->
            val registration = firestore.collection(WALLETS_COLLECTION_PATH).addSnapshotListener { snapshot, e ->
                if (!emitter.isDisposed) {
                    if (snapshot != null) {
                        emitter.onNext(snapshot)
                    } else {
                        emitter.tryOnError(e?.cause ?: Exception("Something was wrong!"))
                    }
                }
            }
            emitter.setCancellable { registration.remove() }
        }
            .map { snapshot -> snapshot.documents }
            .switchMapSingle { docs ->
                Observable.fromIterable(docs)
                    .switchMapSingle { doc ->
                        coinsRepo
                            .coin(
                                currency,
                                doc.getLong(WALLETS_COIN_ID_FIELDNAME)
                                    ?: throw IllegalArgumentException("Expected $WALLETS_COIN_ID_FIELDNAME field")
                            )
                            .map { coin ->
                                Wallet(
                                    doc.id,
                                    coin,
                                    doc.getDouble(WALLETS_BALANCE_FIELDNAME)
                                        ?: throw IllegalArgumentException("Expected $WALLETS_BALANCE_FIELDNAME field")
                                )
                            }
                    }
                    .toList()
            }
    }

    override fun transactions(wallet: Wallet): Observable<List<Transaction>> {
        TODO("Not yet implemented")
    }
}