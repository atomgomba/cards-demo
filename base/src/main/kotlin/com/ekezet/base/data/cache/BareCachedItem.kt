package com.ekezet.base.data.cache

import org.joda.time.DateTime

/**
 * @author kiri
 */
open class BareCachedItem<K>(
    open val key: K,
    open val createdAt: DateTime,
    open val accessedAt: DateTime
)
