package com.ekezet.cardsdemo.cards.api.models

import org.joda.time.DateTime

/**
 * @author kiri
 */
internal data class ApiCard(
    val cardId: Int,
    val issuer: String,
    val cardNumber: String,
    val expirationDate: String,
    val cardHolderName: String,
    val friendlyName: String,
    val currency: String,
    val cvv: Int,
    val availableBalance: Float,
    val currentBalance: Float,
    val minPayment: Float,
    val dueDate: DateTime,
    val reservations: Float,
    val balanceCarriedOverFromLastStatement: Float,
    val spendingsSinceLastStatement: Float,
    val yourLastRepayment: DateTime,
    val accountDetails: ApiAccountDetails,
    val status: ApiCardStatus,
    val cardImage: Int
)
