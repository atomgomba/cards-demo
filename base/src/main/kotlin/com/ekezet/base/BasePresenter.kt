package com.ekezet.base

import androidx.annotation.StringRes
import androidx.lifecycle.DefaultLifecycleObserver
import com.ekezet.base.arch.IInteractor
import com.ekezet.base.arch.IPresenter
import com.ekezet.base.arch.IRouter
import com.ekezet.base.arch.IView
import com.ekezet.base.arch.Part
import timber.log.Timber

/**
 * @author kiri
 */
open class BasePresenter<V : IView, I : IInteractor<*>, R : IRouter> :
    IPresenter<V>, DefaultLifecycleObserver {
    protected lateinit var view: V
        private set

    protected var interactor: I? = null
        private set

    protected var router: R? = null
        private set

    init {
        @Suppress("LeakingThis")
        Timber.d("I'm alive: $this")
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBoot(part: Part<V, *, *>) {
        val view = part.view
        this.view = view ?: throw IllegalArgumentException("Presenter cannot boot without a view")
        this.interactor = part.interactor as? I
        this.router = part.router as? R
    }

    protected fun t(@StringRes resId: Int, vararg params: Any): CharSequence = if (params.isEmpty())
        view.getAndroidContext().getString(resId)
    else
        view.getAndroidContext().getString(resId, *params)
}
