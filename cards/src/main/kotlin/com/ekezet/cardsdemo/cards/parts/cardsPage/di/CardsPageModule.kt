package com.ekezet.cardsdemo.cards.parts.cardsPage.di

import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPageInteractor
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPagePart
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPagePresenter
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPageSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPageSpec.View
import com.ekezet.cardsdemo.cards.parts.cardsPage.di.CardsPageModule.Binder
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.di.BalanceIndicatorModule
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.di.BalanceInfoModule
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.di.CardPagerModule
import com.ekezet.cardsdemo.cards.parts.cardsPage.views.CardsPageView
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * @author kiri
 */
@Module(
    includes = [
        Binder::class,
        CardPagerModule::class,
        BalanceIndicatorModule::class,
        BalanceInfoModule::class
    ]
)
object CardsPageModule {
    @Module
    abstract class Binder {
        @Binds @ActivityScope abstract fun bindView(view: CardsPageView): View
        @Binds @ActivityScope abstract fun bindPresenter(presenter: CardsPagePresenter): View.Presenter
        @Binds @ActivityScope abstract fun bindInteractor(interactor: CardsPageInteractor): Interactor
    }

    @Provides @ActivityScope fun providePart(view: View, presenter: View.Presenter, interactor: Interactor) =
        CardsPagePart(view, interactor, presenter, null)
}
