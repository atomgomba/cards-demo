package com.ekezet.base.arch

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

/**
 * @author kiri
 */
open class Part<V : IView, VP : IPresenter<V>, IP : IPresenter<V>>(
    internal var view: V?,
    internal var interactor: IInteractor<IP>? = null,
    var presenter: VP,
    internal var router: IRouter? = null
) {
    private fun boot(lifecycle: Lifecycle? = null, context: Context? = null) {
        val bootView = view
            ?: throw IllegalArgumentException("Cannot boot without a view. Please use bootWithView() method.")
        presenter.onBoot(this)
        interactor?.onBoot(presenter as IP)
        router?.onBoot(context ?: bootView.getAndroidContext())
        lifecycle?.addObserver(presenter)
        if (interactor != null) {
            lifecycle?.addObserver(interactor as LifecycleObserver)
        }
    }

    fun bootWithView(view: V, lifecycle: Lifecycle? = null) {
        this.view = view
        boot(lifecycle)
    }

    fun bootWithContainer(container: ViewGroup, lifecycle: Lifecycle? = null) {
        container.addView(view as android.view.View)
        boot(lifecycle)
    }

    fun bootWithFragment(
        context: Context,
        fragmentManager: FragmentManager,
        tag: String? = null,
        args: Bundle? = null
    ) {
        if (tag != null && fragmentManager.findFragmentByTag(tag)?.isVisible == true) {
            // do not add same Fragment twice
            return
        }
        val fragment = view as Fragment
        if (args != null) {
            fragment.arguments = args
        }
        val fragmentTag = if (view is IFragmentView && tag == null) {
            (view as IFragmentView).fragmentTag
        } else {
            tag
        }
        fragmentManager.beginTransaction()
            .add(fragment, fragmentTag)
            .commit()
        boot(fragment.lifecycle, context)
    }
}
