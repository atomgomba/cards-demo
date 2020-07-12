package com.ekezet.base.data.cache.layers

import com.ekezet.base.data.cache.BareCachedItem
import com.ekezet.base.data.cache.CachedItem
import com.ekezet.base.data.cache.ICacheLayer
import java.util.concurrent.ConcurrentHashMap

/**
 * @author kiri
 */
class MemoryCacheLayer<K, T>(initialCapacity: Int = 16) : ICacheLayer<K, T> {
    override val cachedItems: List<BareCachedItem<K>>
        get() = items.values.toList()

    private val items: MutableMap<String, CachedItem<K, T>> = ConcurrentHashMap(initialCapacity)

    @Synchronized override suspend fun get(key: K): CachedItem<K, T>? =
        items[key.toString()]

    override suspend fun put(key: K, item: CachedItem<K, T>) {
        items[key.toString()] = item
    }

    override suspend fun remove(key: K) {
        items.remove(key.toString())
    }

    override suspend fun removeAll() {
        items.clear()
    }
}
