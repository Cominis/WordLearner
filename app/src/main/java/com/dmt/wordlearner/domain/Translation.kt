package com.dmt.wordlearner.domain

import androidx.room.ColumnInfo
import java.time.LocalDateTime

data class Translation(

    var id: Long,

    var word: String,
    var translation: String,

    @ColumnInfo(name = "date_time")
    var dateTime: LocalDateTime,
    var weekday: Int,

    @ColumnInfo(name = "language_code")
    var languageCode: String

)