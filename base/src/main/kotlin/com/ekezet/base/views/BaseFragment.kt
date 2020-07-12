package com.ekezet.base.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import com.ekezet.base.arch.IFragmentPresenter
import com.ekezet.base.arch.IPresenter
import com.ekezet.base.arch.IView
import com.ekezet.base.arch.Part
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * @author kiri
 */
open class BaseFragment<V : IView, VP : IPresenter<V>, IP : IPresenter<V>> : DaggerFragment(), IView {
    @Inject internal lateinit var part: Part<V, VP, IP>

    protected lateinit var presenter: VP
        private set

    @LayoutRes private var layoutId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = part.presenter
        @Suppress("UNCHECKED_CAST")
        part.bootWithView(this as V, lifecycle)
    }

    @CallSuper override fun setup(idRes: Int) {
        layoutId = idRes
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val presenter = presenter
        if (presenter is IFragmentPresenter<*>) {
            presenter.onViewCreated()
        }
    }

    override fun getAndroidContext() = requireContext()
}
