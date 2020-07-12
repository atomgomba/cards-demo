package com.ekezet.cardsdemo.parts.home.parts.bottomMenu

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BasePresenter
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.R
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuSpec.Interactor
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuSpec.View
import javax.inject.Inject

/**
 * @author kiri
 */
@ActivityScope
class BottomMenuPresenter @Inject constructor() : BasePresenter<View, Interactor, Nothing>(), View.Presenter, Interactor.Presenter {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        view.setup(R.layout.view_bottom_menu)
        view.updateNotificationCount(3)
    }

    override fun onUserSelectedMenuItem(menuItemId: Int) {
        interactor!!.emitNavigationEvent(menuItemId)
    }

    override fun onNavigationEvent(menuItemId: Int) {
        view.activateMenuItem(menuItemId)
    }
}
