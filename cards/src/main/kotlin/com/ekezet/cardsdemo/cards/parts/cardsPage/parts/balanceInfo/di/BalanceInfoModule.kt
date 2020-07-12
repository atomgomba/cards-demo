package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.di

import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoInteractor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoPart
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoPresenter
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoRouter
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.Router
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.View
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.di.BalanceInfoModule.Binder
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.views.BalanceInfoView
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
object BalanceInfoModule {
    @Module
    abstract class Binder {
        @Binds @ActivityScope abstract fun bindView(view: BalanceInfoView): View
        @Binds @ActivityScope abstract fun bindPresenter(presenter: BalanceInfoPresenter): View.Presenter
        @Binds @ActivityScope abstract fun bindInteractor(interactor: BalanceInfoInteractor): Interactor
        @Binds @ActivityScope abstract fun bindRouter(interactor: BalanceInfoRouter): Router
    }

    @Provides @ActivityScope fun providePart(
        view: View,
        interactor: Interactor,
        presenter: View.Presenter,
        router: Router
    ) =
        BalanceInfoPart(view, interactor, presenter, router)
}
