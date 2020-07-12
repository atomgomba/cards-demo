package com.ekezet.base.arch

import androidx.lifecycle.LifecycleObserver

/**
 * @author kiri
 */
interface IPresenter<V : IView> : LifecycleObserver {
    fun onBoot(part: Part<V, *, *>)
}
