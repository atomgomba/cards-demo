package com.ekezet.cardsdemo.parts.home.di

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPagePart
import com.ekezet.cardsdemo.cards.parts.cardsPage.di.CardsPageModule
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPagePart
import com.ekezet.cardsdemo.cards.parts.transactionsPage.di.TransactionsPageModule
import com.ekezet.cardsdemo.di.MENU
import com.ekezet.cardsdemo.navigation.R.id
import com.ekezet.cardsdemo.navigation.viewmodels.NavigationItem
import com.ekezet.cardsdemo.parts.home.HomeInteractor
import com.ekezet.cardsdemo.parts.home.HomePart
import com.ekezet.cardsdemo.parts.home.HomePresenter
import com.ekezet.cardsdemo.parts.home.HomeSpec.Interactor
import com.ekezet.cardsdemo.parts.home.HomeSpec.View
import com.ekezet.cardsdemo.parts.home.di.HomeActivityModule.Binder
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.di.BottomMenuModule
import com.ekezet.cardsdemo.parts.home.views.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author kiri
 */
@Module(
    includes = [
        Binder::class,
        BottomMenuModule::class,
        CardsPageModule::class,
        TransactionsPageModule::class
    ]
)
object HomeActivityModule {
    @Module
    abstract class Binder {
        @Binds @ActivityScope abstract fun bindContext(activity: HomeActivity): Context
        @Binds @ActivityScope abstract fun bindLifecycleOwner(activity: HomeActivity): LifecycleOwner
        @Binds @ActivityScope abstract fun bindView(activity: HomeActivity): View
        @Binds @ActivityScope abstract fun bindInteractor(interactor: HomeInteractor): Interactor
        @Binds @ActivityScope abstract fun bindPresenter(presenter: HomePresenter): View.Presenter
    }

    @Provides @ActivityScope fun providePart(
        view: View,
        interactor: Interactor,
        presenter: View.Presenter
    ) = HomePart(view, interactor, presenter, null)

    @Provides @Named(MENU) @ActivityScope fun provideMenuItems(
        cardsPagePart: CardsPagePart,
        transactionsPagePart: TransactionsPagePart
    ): List<NavigationItem> =
        listOf(
            NavigationItem(id.navCards, cardsPagePart),
            NavigationItem(id.navTransactions, transactionsPagePart)
        )
}
