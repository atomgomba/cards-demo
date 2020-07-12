package com.ekezet.base.data.cache.evictionStrategies

import com.ekezet.base.data.cache.BareCachedItem
import com.ekezet.base.data.cache.CacheStore.EvictionStrategy

/**
 * @author kiri
 */
class LruEvictionStrategy<K> : EvictionStrategy<K> {
    override fun findVictim(items: List<BareCachedItem<K>>) =
        items.minBy { it.accessedAt }
}
