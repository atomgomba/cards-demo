package com.ekezet.cardsdemo.parts.home.di

import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.parts.home.views.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author kiri
 */
@Module
abstract class HomeModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun contributeMainActivityInjector(): HomeActivity
}
