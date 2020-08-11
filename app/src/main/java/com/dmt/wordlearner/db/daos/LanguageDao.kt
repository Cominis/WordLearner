package com.dmt.wordlearner.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dmt.wordlearner.db.tables.Language

@Dao
abstract class LanguageDao : BaseDao<Language> {

    @Query("SELECT * from languages_table")
    abstract  fun getLanguagesLiveData(): LiveData<List<Language>>

}