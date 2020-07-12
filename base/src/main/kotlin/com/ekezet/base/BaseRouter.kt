package com.ekezet.base

import android.content.Context
import android.content.Intent
import com.ekezet.base.arch.IRouter

/**
 * @author kiri
 */
open class BaseRouter : IRouter {
    protected lateinit var context: Context
        private set

    override fun onBoot(context: Context) {
        this.context = context
    }

    protected fun Intent.startActivity() {
        context.startActivity(this)
    }
}
