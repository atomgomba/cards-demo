package com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.di

import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewInteractor
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewPart
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewPresenter
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewSpec.View
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.di.BalanceOverviewModule.Binder
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.views.BalanceOverviewView
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
object BalanceOverviewModule {
    @Module
    abstract class Binder {
        @Binds @ActivityScope abstract fun bindView(view: BalanceOverviewView): View
        @Binds @ActivityScope abstract fun bindPresenter(presenter: BalanceOverviewPresenter): View.Presenter
        @Binds @ActivityScope abstract fun bindInteractor(interactor: BalanceOverviewInteractor): Interactor
    }

    @Provides @ActivityScope fun providePart(
        view: View,
        interactor: Interactor,
        presenter: View.Presenter
    ) = BalanceOverviewPart(view, interactor, presenter, null)
}
