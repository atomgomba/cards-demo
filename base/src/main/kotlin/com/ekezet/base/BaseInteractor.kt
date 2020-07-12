package com.ekezet.base

import androidx.annotation.CallSuper
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.arch.IInteractor
import com.ekezet.base.arch.IPresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import timber.log.Timber

/**
 * @author kiri
 */
open class BaseInteractor<P : IPresenter<*>>(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IInteractor<P>, DefaultLifecycleObserver {

    private val handler = CoroutineExceptionHandler { _, throwable ->
        onException(throwable)
    }

    override val coroutineContext = dispatcher + handler

    protected lateinit var presenter: P

    override fun onBoot(presenter: P) {
        this.presenter = presenter
    }

    @CallSuper override fun onDestroy(owner: LifecycleOwner) {
        cancel()
    }

    override fun cancel() {
        coroutineContext.cancel()
    }

    protected open fun onException(throwable: Throwable) {
        Timber.e(throwable)
    }
}
