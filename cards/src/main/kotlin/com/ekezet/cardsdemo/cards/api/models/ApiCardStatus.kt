package com.ekezet.cardsdemo.cards.api.models

import com.squareup.moshi.Json

/**
 * @author kiri
 */
enum class ApiCardStatus {
    @Json(name = "ACTIVE") ACTIVE,
    @Json(name = "BLOCKED") BLOCKED,
}
