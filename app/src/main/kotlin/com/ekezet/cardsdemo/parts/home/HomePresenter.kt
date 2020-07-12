package com.ekezet.cardsdemo.parts.home

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BasePresenter
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.R
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.di.MENU
import com.ekezet.cardsdemo.navigation.viewmodels.NavigationItem
import com.ekezet.cardsdemo.parts.home.HomeSpec.Interactor
import com.ekezet.cardsdemo.parts.home.HomeSpec.View
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuPart
import javax.inject.Inject
import javax.inject.Named

/**
 * @author kiri
 */
@ActivityScope
class HomePresenter @Inject constructor(
    @Named(MENU) private val menuItems: List<NavigationItem>,
    private val bottomMenuPart: BottomMenuPart
) : BasePresenter<View, Interactor, Nothing>(), View.Presenter, Interactor.Presenter {
    private val pageIndexMap: MutableMap<Int, Int> = HashMap()

    init {
        menuItems.forEachIndexed { index, navigationItem ->
            pageIndexMap[navigationItem.menuId] = index
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        with(view) {
            setup(R.layout.activity_home)
            mountBottomMenu(bottomMenuPart, owner)
            setNavigationItems(menuItems)
        }

        interactor!!.updateCardsList()
    }

    override fun onLoadingSuccessful(items: List<Card>) {
        view.hideLoading()
    }

    override fun onLoadingError(throwable: Throwable) {
        view.hideLoading()
        view.showError(throwable.message ?: "Unknown error")
    }

    override fun onNavigationItemSelected(menuId: Int) {
        view.pageIndex = pageIndexMap[menuId] ?: 0
        // TODO: select bottom menu item
    }

    override fun onLoadingStarted() {
        view.showLoading()
    }
}
