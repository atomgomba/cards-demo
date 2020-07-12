package com.ekezet.base.arch

import androidx.lifecycle.LifecycleObserver
import kotlinx.coroutines.CoroutineScope

/**
 * @author kiri
 */
interface IInteractor<P : IPresenter<*>> : LifecycleObserver, CoroutineScope {
    fun onBoot(presenter: P)
    fun cancel()
}
