package com.ekezet.cardsdemo.parts.home.parts.bottomMenu.di

import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuInteractor
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuPart
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuPresenter
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuSpec.Interactor
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuSpec.View
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.di.BottomMenuModule.Binder
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.views.BottomMenuView
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * @author kiri
 */
@Module(
    includes = [
        Binder::class
    ]
)
object BottomMenuModule {
    @Module
    abstract class Binder {
        @Binds @ActivityScope abstract fun bindView(view: BottomMenuView): View
        @Binds @ActivityScope abstract fun bindPresenter(presenter: BottomMenuPresenter): View.Presenter
        @Binds @ActivityScope abstract fun bindInteractor(interactor: BottomMenuInteractor): Interactor
    }

    @Provides @ActivityScope fun providePart(
        view: View,
        interactor: Interactor,
        presenter: View.Presenter
    ) =
        BottomMenuPart(view, interactor, presenter, null)
}
