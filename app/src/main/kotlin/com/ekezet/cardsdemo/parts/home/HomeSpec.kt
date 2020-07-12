package com.ekezet.cardsdemo.parts.home

import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.arch.*
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.navigation.viewmodels.NavigationItem
import com.ekezet.cardsdemo.parts.home.HomeSpec.*
import kotlinx.coroutines.Job

/**
 * @author kiri
 */
interface HomeSpec {
    interface View : IView {
        var pageIndex: Int

        fun mountBottomMenu(part: AnyPart, owner: LifecycleOwner)
        fun setNavigationItems(items: List<NavigationItem>)
        fun showError(text: CharSequence)
        fun showLoading()
        fun hideLoading()

        interface Presenter : IPresenter<View>
    }

    interface Interactor : IInteractor<Interactor.Presenter> {
        fun updateCardsList(): Job

        interface Presenter : IPresenter<View> {
            fun onLoadingStarted()
            fun onLoadingError(throwable: Throwable)
            fun onLoadingSuccessful(items: List<Card>)
            fun onNavigationItemSelected(@IdRes menuId: Int)
        }
    }
}

typealias HomePart = Part<View, View.Presenter, Interactor.Presenter>
