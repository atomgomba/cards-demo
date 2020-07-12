package com.ekezet.cardsdemo.navigation.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author kiri
 */
@Module
object NavigationModule {
    @Provides @Singleton fun provideNavigationChannel() = NavigationChannel()
}
