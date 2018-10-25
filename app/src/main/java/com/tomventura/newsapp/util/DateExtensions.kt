package com.tomventura.newsapp.util

import android.text.format.DateUtils
import java.util.*

fun Date.getRelativeTimeAgo() : String {
    return DateUtils.getRelativeTimeSpanString(this.time, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString()
}