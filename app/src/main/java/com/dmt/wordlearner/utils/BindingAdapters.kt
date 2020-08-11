package com.dmt.wordlearner.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@BindingAdapter("setWeekday")
fun setWeekdayFromInt(view: TextView, weekday: Int) {
    val text = when(weekday){
        1 -> "Mon"
        2 -> "Tue"
        3 -> "Wed"
        4 -> "Thu"
        5 -> "Fri"
        6 -> "Sat"
        7 -> "Sun"
        else -> "Unknown"
    }
    view.text = text
}

@BindingAdapter("setDate")
fun setDateFromTranslation(view: TextView, localDateTime: LocalDateTime) {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    view.text = localDateTime.format(formatter)
}
