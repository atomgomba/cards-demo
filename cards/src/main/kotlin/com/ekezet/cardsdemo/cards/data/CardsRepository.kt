package com.ekezet.cardsdemo.cards.data

import com.ekezet.base.api.ApiResponse
import com.ekezet.base.api.callOnChannel
import com.ekezet.cardsdemo.cards.api.CardsV1ApiInterface
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.coroutineScope

/**
 * @author kiri
 */
@Singleton
class CardsRepository internal constructor(
    private val apiInterface: CardsV1ApiInterface,
    dispatcher: CoroutineDispatcher
) : CoroutineScope {
    @Inject internal constructor(
        apiInterface: CardsV1ApiInterface
    ) : this(apiInterface, Dispatchers.IO)

    override val coroutineContext = dispatcher

    val cardsChannel = ConflatedBroadcastChannel<ApiResponse<List<Card>>>()

    val numCards: Int
        get() = cardsChannel.valueOrNull?.data?.size ?: 0

    private var deferredResponse: Deferred<List<Card>?>? = null

    suspend fun getCards() = coroutineScope {
        if (deferredResponse?.isActive == true) {
            deferredResponse?.await()
            return@coroutineScope
        }
        deferredResponse = callOnChannel(cardsChannel) {
            return@callOnChannel apiInterface.getCards()
                .sortedBy { it.cardId }
                .map { Card.from(it) }
        }
    }

    fun findIndex(card: Card): Int? {
        val cards = cardsChannel.valueOrNull?.data ?: return null
        return cards.indexOf(card)
    }

    fun findByIndex(index: Int) = cardsChannel.valueOrNull?.data?.get(index)
}
