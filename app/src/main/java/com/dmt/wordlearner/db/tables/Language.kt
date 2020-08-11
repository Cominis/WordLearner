package com.dmt.wordlearner.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languages_table")
data class Language(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "language_code")
    var languageCode : String

){
    override fun toString(): String {
        return languageCode
    }
}