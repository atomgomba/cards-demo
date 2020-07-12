package com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.di

import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphInteractor
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphPart
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphPresenter
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphSpec.View
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.di.DetailedGraphModule.Binder
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.views.DetailedGraphView
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
object DetailedGraphModule {
    @Module
    abstract class Binder {
        @Binds @ActivityScope abstract fun bindView(view: DetailedGraphView): View
        @Binds @ActivityScope abstract fun bindPresenter(presenter: DetailedGraphPresenter): View.Presenter
        @Binds @ActivityScope abstract fun bindInteractor(interactor: DetailedGraphInteractor): Interactor
    }

    @Provides @ActivityScope fun providePart(
        view: View,
        interactor: Interactor,
        presenter: View.Presenter
    ) = DetailedGraphPart(view, interactor, presenter, null)
}
