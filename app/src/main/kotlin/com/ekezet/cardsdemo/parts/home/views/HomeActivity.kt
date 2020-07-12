package com.ekezet.cardsdemo.parts.home.views

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.arch.AnyPart
import com.ekezet.base.views.BaseActivity
import com.ekezet.cardsdemo.R
import com.ekezet.cardsdemo.navigation.viewmodels.NavigationItem
import com.ekezet.cardsdemo.parts.home.HomePart
import com.ekezet.cardsdemo.parts.home.HomeSpec.View
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_home.*

/**
 * @author kiri
 */
class HomeActivity : BaseActivity<HomePart, View, View.Presenter>(), View {
    @Inject internal lateinit var menuPagerAdapter: HomePagerAdapter

    override var pageIndex: Int
        get() = viewPager.currentItem
        set(value) {
            viewPager.currentItem = value
        }

    override fun setup(idRes: Int) {
        super.setup(idRes)
        viewPager.isUserInputEnabled = false
        viewPager.adapter = menuPagerAdapter
    }

    override fun setNavigationItems(items: List<NavigationItem>) {
        viewPager.offscreenPageLimit = items.size
        menuPagerAdapter.setPages(items.map { it.part as AnyPart })
    }

    override fun mountBottomMenu(part: AnyPart, owner: LifecycleOwner) {
        part.bootWithContainer(bottomMenuContainer, owner.lifecycle)
    }

    override fun showError(text: CharSequence) {
        val root: ViewGroup = findViewById(android.R.id.content) ?: return
        Snackbar.make(root, text, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(R.string.common__ok) { _ -> dismiss() }
            show()
        }
    }

    override fun showLoading() {
        progressIndicator.isVisible = true
    }

    override fun hideLoading() {
        progressIndicator.isVisible = false
    }
}
