package com.dmt.wordlearner.db

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

object Converters {

    @TypeConverter
    @JvmStatic
    fun timestampToLocalDateTime(value: Long): LocalDateTime {
        val instant: Instant = Instant.ofEpochMilli(value)
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }

    @TypeConverter
    @JvmStatic
    fun localDateTimeToTimestamp(localDateTime: LocalDateTime): Long {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

}