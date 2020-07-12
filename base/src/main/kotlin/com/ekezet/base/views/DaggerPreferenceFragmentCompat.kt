package com.ekezet.base.views

import android.content.Context
import androidx.preference.PreferenceFragmentCompat
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * @author kiri
 */
abstract class DaggerPreferenceFragmentCompat : PreferenceFragmentCompat(), HasAndroidInjector {
    @Inject internal lateinit var androidInjector: DispatchingAndroidInjector<Any?>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any?> = androidInjector
}
