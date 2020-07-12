package com.ekezet.cardsdemo

import com.ekezet.cardsdemo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class App : DaggerApplication() {
    private val appInjector = DaggerAppComponent.factory()
        .create(this)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(MainDebugTree())
    }

    override fun applicationInjector(): AndroidInjector<App> = appInjector

    private inner class MainDebugTree : Timber.DebugTree(), CoroutineScope {
        override val coroutineContext = Dispatchers.Main

        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            launch {
                super.log(priority, tag, message, t)
            }
        }

        override fun createStackElementTag(element: StackTraceElement) = with(element) {
            "($fileName:$lineNumber)"
        }
    }
}
