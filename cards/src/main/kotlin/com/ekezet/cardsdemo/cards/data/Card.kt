package com.ekezet.cardsdemo.cards.data

import android.os.Parcelable
import com.ekezet.cardsdemo.cards.api.models.ApiCard
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

/**
 * @author kiri
 */
@Parcelize
data class Card(
    val cardId: Int,
    val name: String,
    val cardImage: Int,
    val availableBalance: Float,
    val currentBalance: Float,
    val pendingBalance: Float,
    val minPayment: Float,
    val currency: String,
    val dueDate: DateTime,
    val balanceCarriedOver: Float
) : Parcelable {
    val totalBalance: Float
        get() = availableBalance + currentBalance

    companion object {
        internal fun from(other: ApiCard): Card = with(other) {
            Card(
                cardId = cardId,
                name = friendlyName,
                cardImage = cardImage,
                availableBalance = availableBalance,
                currentBalance = currentBalance,
                pendingBalance = reservations,
                minPayment = minPayment,
                currency = currency,
                dueDate = dueDate,
                balanceCarriedOver = balanceCarriedOverFromLastStatement
            )
        }
    }
}
