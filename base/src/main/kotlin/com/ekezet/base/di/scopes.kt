package com.ekezet.base.di

import javax.inject.Scope

/**
 * @author kiri
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope
