package com.ekezet.cardsdemo.di

import com.ekezet.base.di.BaseModule
import com.ekezet.cardsdemo.App
import com.ekezet.cardsdemo.cards.di.CardsModule
import com.ekezet.cardsdemo.navigation.di.NavigationModule
import com.ekezet.cardsdemo.parts.home.di.HomeModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author kiri
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BaseModule::class,
        CardsModule::class,
        HomeModule::class,
        NavigationModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}
