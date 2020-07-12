package com.ekezet.base.di

import com.ekezet.base.data.DateTimeAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

/**
 * @author kiri
 */
@Module
object BaseModule {
    @Provides @Singleton fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = BODY
            })
            .build()

    @Provides @Singleton fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(DateTimeAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
}
