package com.dmt.wordlearner.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dmt.wordlearner.db.tables.Word
import com.dmt.wordlearner.domain.Translation
import java.time.LocalDateTime


@Dao
abstract class WordDao : BaseDao<Word> {

    @Query("SELECT words_table.id as 'id', word, date_time, weekday, translation, language_code from words_table, languages_table WHERE language_id = languages_table.id")
    abstract  fun getTranslationsLiveData(): LiveData<List<Translation>>

    @Query("SELECT words_table.id as 'id', word, date_time, weekday, translation, language_code from words_table, languages_table WHERE language_id = languages_table.id AND date_time BETWEEN :start AND :end")
    abstract  fun getTranslationsInBetweenLiveData(start: LocalDateTime, end: LocalDateTime): LiveData<List<Translation>>

    @Query("SELECT words_table.id as 'id', word, date_time, weekday, translation, language_code from words_table, languages_table WHERE language_id = languages_table.id AND words_table.id = :wordId")
    abstract  fun getTranslation(wordId: Long): Translation
}