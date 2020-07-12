package com.ekezet.base.data.cache.expirationStrategies

import com.ekezet.base.data.cache.CacheKey
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * @author kiri
 */
internal class TtlExpirationStrategyTest {
    private val subject = TtlExpirationStrategy<CacheKey, Any>(60 * 60 * 1000)

    @Test fun `isExpiredItem is true`() {
        mockkStatic(DateTime::class)

        every { DateTime.now(any<DateTimeZone>()) } returns DateTime.parse("2020-02-02T16:20:00")

        assertTrue(subject.isExpired(mockk {
            every { createdAt } returns DateTime.parse("2020-02-02T13:20:00")
        }))
    }

    @Test fun `isExpiredItem is false`() {
        mockkStatic(DateTime::class)

        every { DateTime.now(any<DateTimeZone>()) } returns DateTime.parse("2020-02-02T16:20:00")

        assertFalse(subject.isExpired(mockk {
            every { createdAt } returns DateTime.parse("2020-02-02T15:20:00")
        }))
    }

    @AfterEach fun tearDown() {
        unmockkStatic(DateTime::class)
    }
}
