package com.ekezet.base.arch

/**
 * @author kiri
 */
interface IFragmentPresenter<V : IView> : IPresenter<V> {
    fun onViewCreated() {}
}
