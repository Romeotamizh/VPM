package com.optisol.vpm.common

import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.util.*

fun String.formatToLocale(
    dateFormat: String = DATE_TIME_FORMAT,
    timeZone: TimeZone = TimeZone.getTimeZone(UTC)
):String = SimpleDateFormat(dateFormat, Locale.getDefault()).let {
    it.timeZone = timeZone
    return@let it.parse(this).formatToString(timeZone = TimeZone.getDefault() )
}

fun Date.formatToString(
    dateFormat: String = DATE_TIME_FORMAT,
    timeZone: TimeZone = TimeZone.getTimeZone(UTC)
): String = SimpleDateFormat(dateFormat, Locale.getDefault()).let {
    it.timeZone = timeZone
    return@let it.format(this)
}


const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
const val UTC = "UTC"