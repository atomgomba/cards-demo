package com.ekezet.base.data.cache

import org.joda.time.DateTime

data class CachedItem<K, T>(
    override val key: K,
    val data: T,
    override val createdAt: DateTime = DateTime.now(),
    override val accessedAt: DateTime = DateTime.now()
) : BareCachedItem<K>(key, createdAt, accessedAt)
