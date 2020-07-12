package com.ekezet.base.arch

import android.content.Context
import androidx.annotation.LayoutRes

/**
 * @author kiri
 */
interface IView {
    fun setup(@LayoutRes idRes: Int)
    fun getAndroidContext(): Context
}
