package com.ekezet.cardsdemo.cards.api

import com.ekezet.cardsdemo.cards.api.models.ApiCard
import retrofit2.http.GET

/**
 * @author kiri
 */
internal interface CardsV1ApiInterface {
    @GET("cards.json")
    suspend fun getCards(): List<ApiCard>
}
