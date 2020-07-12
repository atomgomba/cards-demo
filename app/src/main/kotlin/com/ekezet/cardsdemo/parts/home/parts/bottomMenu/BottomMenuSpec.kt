package com.ekezet.cardsdemo.parts.home.parts.bottomMenu

import androidx.annotation.IdRes
import com.ekezet.base.arch.IInteractor
import com.ekezet.base.arch.IPresenter
import com.ekezet.base.arch.IView
import com.ekezet.base.arch.Part
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuSpec.Interactor
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuSpec.View

/**
 * @author kiri
 */
interface BottomMenuSpec {
    interface View : IView {
        fun activateMenuItem(@IdRes menuItemId: Int)
        fun updateNotificationCount(count: Int)

        interface Presenter : IPresenter<View> {
            fun onUserSelectedMenuItem(@IdRes menuItemId: Int)
        }
    }

    interface Interactor : IInteractor<Interactor.Presenter> {
        fun emitNavigationEvent(@IdRes menuItemId: Int)

        interface Presenter : IPresenter<View> {
            fun onNavigationEvent(@IdRes menuItemId: Int)
        }
    }
}

typealias BottomMenuPart = Part<View, View.Presenter, Interactor.Presenter>
