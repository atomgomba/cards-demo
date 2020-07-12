package com.ekezet.base.data.cache.expirationStrategies

import com.ekezet.base.data.cache.CacheStore.ExpirationStrategy
import com.ekezet.base.data.cache.CachedItem
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

/**
 * @author kiri
 */
class TtlExpirationStrategy<K, T> constructor(
    private val ttlMillis: Int,
    private val dateTimeZone: DateTimeZone? = null
) : ExpirationStrategy<K, T>() {
    override fun isExpired(item: CachedItem<K, T>) = if (dateTimeZone == null)
        DateTime.now().minusMillis(ttlMillis).isAfter(item.createdAt)
    else
        DateTime.now(dateTimeZone).minusMillis(ttlMillis).isAfter(item.createdAt)
}
