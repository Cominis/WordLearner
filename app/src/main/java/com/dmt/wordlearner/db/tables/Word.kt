package com.dmt.wordlearner.db.tables

import androidx.room.*
import java.time.LocalDateTime


@Entity(
    tableName = "words_table",
    foreignKeys = [
        ForeignKey(
            entity = Language::class,
            parentColumns = ["id"],
            childColumns = ["language_id"],
            onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("language_id")]
)
data class Word(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var word: String,
    var translation: String,

    @ColumnInfo(name = "date_time")
    var dateTime: LocalDateTime,

    var weekday: Int,

    @ColumnInfo(name = "language_id")
    var languageId: Long

)