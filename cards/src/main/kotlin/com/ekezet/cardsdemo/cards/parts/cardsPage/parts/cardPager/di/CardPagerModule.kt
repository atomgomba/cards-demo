package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.di

import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerInteractor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerPart
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerPresenter
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerSpec.View
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.di.CardPagerModule.Binder
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.views.CardPagerView
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
object CardPagerModule {
    @Module
    abstract class Binder {
        @Binds @ActivityScope abstract fun bindView(view: CardPagerView): View
        @Binds @ActivityScope abstract fun bindPresenter(presenter: CardPagerPresenter): View.Presenter
        @Binds @ActivityScope abstract fun bindInteractor(interactor: CardPagerInteractor): Interactor
    }

    @Provides @ActivityScope fun providePart(
        view: View,
        interactor: Interactor,
        presenter: View.Presenter
    ) = CardPagerPart(view, interactor, presenter, null)
}
