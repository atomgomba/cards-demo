package com.ekezet.cardsdemo.cards.data

import android.os.Parcelable
import com.ekezet.cardsdemo.cards.api.models.ApiAccountDetails
import kotlinx.android.parcel.Parcelize

/**
 * @author kiri
 */
@Parcelize
data class AccountDetails(
    val accountLimit: Int,
    val accountNumber: String
) : Parcelable {
    companion object {
        internal fun from(other: ApiAccountDetails) = with(other) {
            AccountDetails(
                accountLimit = accountLimit,
                accountNumber = accountNumber
            )
        }
    }
}
