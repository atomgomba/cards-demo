package com.ekezet.base.data.cache

import androidx.lifecycle.LifecycleObserver

/**
 * @author kiri
 */
interface ICacheLayer<K, T> : LifecycleObserver {
    val cachedItems: List<BareCachedItem<K>>

    suspend fun get(key: K): CachedItem<K, T>?
    suspend fun put(key: K, item: CachedItem<K, T>)
    suspend fun remove(key: K)
    suspend fun removeAll()
    suspend fun sync() {
    }
}
