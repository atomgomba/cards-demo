package com.ekezet.base.data.cache

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.ekezet.base.data.cache.evictionStrategies.LruEvictionStrategy
import com.ekezet.base.data.cache.expirationStrategies.TtlExpirationStrategy
import com.ekezet.base.data.cache.layers.MemoryCacheLayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import org.joda.time.DateTime
import timber.log.Timber

/**
 * @author kiri
 */
class CacheStore<K, T> private constructor(
    private val capacity: Int,
    private val layers: List<ICacheLayer<K, T>>,
    private val expirationStrategies: MutableList<ExpirationStrategy<K, T>>,
    private val evictionStrategies: MutableList<EvictionStrategy<K>>
) : DefaultLifecycleObserver, CoroutineScope {
    override val coroutineContext = newSingleThreadContext("CacheStoreContext")

    suspend fun getItem(key: K, updateAccess: Boolean = true): CachedItem<K, T>? = withContext(coroutineContext) {
        var missExpired = false
        var hit = false
        layers.forEach { layer ->
            val item = layer.get(key)
            if (item != null) {
                return@withContext if (item.isExpired()) {
                    if (!missExpired) {
                        Timber.i("Miss (expired): $key")
                        missExpired = true
                    }
                    remove(key)
                    null
                } else {
                    if (!hit) {
                        Timber.i("Hit: $key")
                        hit = true
                    }
                    if (updateAccess) {
                        refreshItemAccess(key, item)
                    } else {
                        layers.forEach { it.put(key, item) }
                    }

                    item
                }
            }
        }
        Timber.i("Miss: $key")
        return@withContext null
    }

    suspend fun get(key: K, updateAccess: Boolean = true): T? =
        getItem(key, updateAccess)?.data

    suspend fun put(key: K, item: T, updateExistingData: Boolean = false) = withContext(coroutineContext) {
        var new = CachedItem(key, item)
        var isCreated = false
        var isUpdated = false
        layers.forEach { layer ->
            val existing = layer.get(key)
            if (existing != null)
                if (updateExistingData) {
                    new = new.copy(createdAt = existing.createdAt)
                    if (!isUpdated) {
                        Timber.i("Update: $key")
                        isUpdated = true
                    }
                } else {
                    return@withContext
                }
            if (!isCreated) {
                Timber.i("Create: $key")
                isCreated = true
            }
            layer.evict()
            layer.put(key, new)
        }
    }

    suspend fun remove(key: K) = withContext(coroutineContext) {
        layers.forEach { layer -> layer.remove(key) }
        Timber.i("Removed: $key")
    }

    suspend fun removeAll() = withContext(coroutineContext) {
        layers.forEach { layer -> layer.removeAll() }
        Timber.i("Removed all")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        owner.lifecycleScope.launch {
            layers.forEach { layer -> layer.sync() }
        }
    }

    private fun CachedItem<K, T>.isExpired(): Boolean {
        for (strategy in expirationStrategies) {
            val isExpired = strategy.isExpiredInternal(this)
            if (strategy.isLast || isExpired) {
                return isExpired
            }
        }
        return false
    }

    private suspend fun ICacheLayer<K, T>.evict() {
        val size = cachedItems.size
        if (size == 0 || size < capacity || evictionStrategies.isEmpty()) {
            return
        }
        for (strategy in evictionStrategies) {
            val victim = strategy.findVictim(cachedItems) ?: continue
            remove(victim.key)
            Timber.i("Evicted: ${victim.key}")
            break
        }
    }

    private suspend fun refreshItemAccess(key: K, item: CachedItem<K, T>) {
        val updated = item.copy(accessedAt = DateTime.now())
        layers.forEach { it.put(key, updated) }
        Timber.d("Access time updated: $key")
    }

    interface EvictionStrategy<K> {
        fun findVictim(items: List<BareCachedItem<K>>): BareCachedItem<K>?
    }

    abstract class ExpirationStrategy<K, T> {
        internal var isLast = false
            private set

        abstract fun isExpired(item: CachedItem<K, T>): Boolean

        internal fun isExpiredInternal(item: CachedItem<K, T>): Boolean {
            isLast = false
            return isExpired(item)
        }

        protected fun finish() {
            isLast = true
        }
    }

    class Builder<K, T> {
        private var capacity: Int = 32
        private val layers: MutableList<ICacheLayer<K, T>> = ArrayList()
        private val expirationStrategies: MutableList<ExpirationStrategy<K, T>> = ArrayList()
        private val evictionStrategies: MutableList<EvictionStrategy<K>> = ArrayList()

        fun capacity(value: Int): Builder<K, T> {
            capacity = value
            return this
        }

        fun layer(vararg layer: ICacheLayer<K, T>): Builder<K, T> {
            layers.addAll(layer)
            return this
        }

        fun expirationStrategy(vararg strategy: ExpirationStrategy<K, T>): Builder<K, T> {
            expirationStrategies.addAll(strategy)
            return this
        }

        fun evictionStrategy(vararg strategy: EvictionStrategy<K>): Builder<K, T> {
            evictionStrategies.addAll(strategy)
            return this
        }

        fun build(): CacheStore<K, T> {
            if (layers.isEmpty()) {
                // use only memory cache by default
                layers.add(MemoryCacheLayer(capacity))
            }
            if (expirationStrategies.isEmpty()) {
                // default TTL is one hour
                expirationStrategies.add(TtlExpirationStrategy(60 * 60 * 1000))
            }
            if (evictionStrategies.isEmpty()) {
                // default eviction strategy is LRU
                evictionStrategies.add(LruEvictionStrategy())
            }
            return CacheStore(capacity, layers, expirationStrategies, evictionStrategies)
        }
    }
}
