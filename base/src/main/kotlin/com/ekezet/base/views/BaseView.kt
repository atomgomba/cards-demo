package com.ekezet.base.views

import android.content.Context
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import com.ekezet.base.arch.IView

/**
 * @author kiri
 */
open class BaseView(context: Context) : FrameLayout(context), IView {
    @CallSuper override fun setup(idRes: Int) {
        inflate(context, idRes, this)
    }

    override fun getAndroidContext(): Context = context
}
