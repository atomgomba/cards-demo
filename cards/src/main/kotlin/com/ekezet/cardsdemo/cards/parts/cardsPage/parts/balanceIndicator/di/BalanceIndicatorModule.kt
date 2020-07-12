package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.di

import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorInteractor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorPart
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorPresenter
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.View
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.di.BalanceIndicatorModule.Binder
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.views.BalanceIndicatorView
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
object BalanceIndicatorModule {
    @Module
    abstract class Binder {
        @Binds @ActivityScope abstract fun bindView(view: BalanceIndicatorView): View
        @Binds @ActivityScope abstract fun bindPresenter(presenter: BalanceIndicatorPresenter): View.Presenter
        @Binds @ActivityScope abstract fun bindInteractor(interactor: BalanceIndicatorInteractor): Interactor
    }

    @Provides @ActivityScope fun providePart(
        view: View,
        interactor: Interactor,
        presenter: View.Presenter
    ) = BalanceIndicatorPart(view, interactor, presenter, null)
}
