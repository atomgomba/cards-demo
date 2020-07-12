package com.ekezet.cardsdemo.cards.parts.transactionsPage.di

import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionPagePresenter
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPagePart
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPageRouter
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPageSpec.Router
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPageSpec.View
import com.ekezet.cardsdemo.cards.parts.transactionsPage.di.TransactionsPageModule.Binder
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.di.BalanceOverviewModule
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.di.DetailedGraphModule
import com.ekezet.cardsdemo.cards.parts.transactionsPage.views.TransactionsPageView
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * @author kiri
 */
@Module(
    includes = [
        Binder::class,
        DetailedGraphModule::class,
        BalanceOverviewModule::class
    ]
)
object TransactionsPageModule {
    @Module
    abstract class Binder {
        @Binds @ActivityScope abstract fun bindView(view: TransactionsPageView): View
        @Binds @ActivityScope abstract fun bindPresenter(presenter: TransactionPagePresenter): View.Presenter
        @Binds @ActivityScope abstract fun bindRouter(router: TransactionsPageRouter): Router
    }

    @Provides @ActivityScope fun providePart(view: View, presenter: View.Presenter, router: Router) =
        TransactionsPagePart(view, null, presenter, router)
}
