package com.ekezet.cardsdemo.parts.home.parts.bottomMenu.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.views.BaseView
import com.ekezet.cardsdemo.R
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuSpec.View
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuSpec.View.Presenter
import javax.inject.Inject
import kotlinx.android.synthetic.main.view_bottom_menu.view.*

/**
 * @author kiri
 */
@SuppressLint("ViewConstructor")
@ActivityScope
class BottomMenuView @Inject constructor(
    context: Context,
    private val presenter: Presenter
) : BaseView(context), View {
    override fun setup(idRes: Int) {
        super.setup(idRes)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            presenter.onUserSelectedMenuItem(menuItem.itemId)
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun updateNotificationCount(count: Int) {
        bottomNavigationView.getOrCreateBadge(R.id.navStatements).apply {
            backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
            isVisible = true
            number = count
        }
    }

    override fun activateMenuItem(menuItemId: Int) {
        if (bottomNavigationView.selectedItemId != menuItemId) {
            bottomNavigationView.selectedItemId = menuItemId
        }
    }
}
