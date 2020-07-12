package com.ekezet.cardsdemo.di

import android.content.Context
import com.ekezet.base.di.APP
import com.ekezet.cardsdemo.App
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author kiri
 */
@Module
object AppModule {
    @Provides @Named(APP) fun provideAppContext(app: App): Context = app.applicationContext
}
