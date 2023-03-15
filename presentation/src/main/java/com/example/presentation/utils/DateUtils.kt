package com.example.presentation.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private val defaultLocale = Locale.ENGLISH

fun Long.formatDate(
    to: DateFormat,
    locale: Locale = defaultLocale
): String? {
    return try {
        this.formatWithPattern(to, locale)
    } catch (e: Exception) {
        Log.d("formatDate", e.toString())
        null
    }
}

private fun Long.formatWithPattern(
    to: DateFormat,
    locale: Locale
): String {
    val simpleDateFormat = SimpleDateFormat(to.pattern, locale)
    return simpleDateFormat.format(Date(TimeUnit.SECONDS.toMillis(this)))
}

enum class DateFormat(val pattern: String) {
    DAY_IN_WEEK_MONTH_TIME("EEEE, dd MMM HH:mm"),
    DAY_IN_WEEK("EEEE"),
    DAY_IN_WEEK_DAY_MONTH("EEEE (dd/MM)")
}