package com.ekezet.cardsdemo.cards.di

import com.ekezet.cardsdemo.cards.BuildConfig.CARDS_API_V1_BASE_URL
import com.ekezet.cardsdemo.cards.api.CardsV1ApiInterface
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author kiri
 */
@Module
object CardsModule {
    @Provides @Singleton internal fun provideCardsApiV1(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): CardsV1ApiInterface =
        Retrofit.Builder()
            .baseUrl(CARDS_API_V1_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CardsV1ApiInterface::class.java)

    @Provides @Singleton fun provideSelectedCardBroadcastChannel() = SelectedCard()
}
