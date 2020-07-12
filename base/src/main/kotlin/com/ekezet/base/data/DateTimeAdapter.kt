package com.ekezet.base.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.joda.time.DateTime

/**
 * @author kiri
 */
class DateTimeAdapter {
    @ToJson fun toJson(value: DateTime) = value.toString()
    @FromJson fun fromJson(value: String): DateTime = DateTime.parse(value.fixMalformedDate())

    @Deprecated("Remove when API gets fixed", replaceWith = ReplaceWith("// remove function"))
    private fun String.fixMalformedDate() = replace('.', '-').trimEnd('-')
}
