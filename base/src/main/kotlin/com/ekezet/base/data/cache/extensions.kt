package com.ekezet.base.data.cache

/**
 * @author kiri
 */
suspend inline fun <K : CacheKey, T> CacheStore<K, T>.doIfFresh(key: K, ifFresh: (T) -> T): T? =
    get(key)?.let {
        ifFresh(it)
    }
