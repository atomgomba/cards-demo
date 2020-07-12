package com.ekezet.base.utils

import java.text.NumberFormat
import java.util.Locale
import org.joda.time.DateTime

/**
 * @author kiri
 */
fun Float.formatAsMoney(): CharSequence = NumberFormat.getInstance(Locale.US).apply {
    minimumFractionDigits = 2
    maximumFractionDigits = 2
}.format(toDouble())

fun DateTime.formatAsDate(): CharSequence =
    toString("dd.MM.yyyy")
