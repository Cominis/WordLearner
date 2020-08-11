package com.dmt.wordlearner.repository

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dmt.wordlearner.db.WordsDatabase
import com.dmt.wordlearner.db.tables.Language
import com.dmt.wordlearner.db.tables.Word
import com.dmt.wordlearner.domain.Translation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import kotlin.random.Random

class TranslationRepository(private val db: WordsDatabase) {

    //val translations = db.wordDao.getTranslationsLiveData()

    private val seed = System.currentTimeMillis()
    val translations = Transformations.map(db.wordDao.getTranslationsLiveData()) {
        it.shuffled(Random(seed))
    }

    val languages = db.languageDao.getLanguagesLiveData()

    fun getTranslationsInBetween(start: LocalDateTime, end: LocalDateTime) : LiveData<List<Translation>> =
        db.wordDao.getTranslationsInBetweenLiveData(start, end)

    suspend fun insert(vararg word: Word) : List<Long> =
        withContext(Dispatchers.IO) {
            db.wordDao.insert(*word)
        }

    suspend fun getTranslation(wordId: Long) : Translation =
        withContext(Dispatchers.IO) {
            db.wordDao.getTranslation(wordId)
        }

    suspend fun insert(vararg language: Language) : List<Long> =
        withContext(Dispatchers.IO) {
            db.languageDao.insert(*language)
        }

    suspend fun update(vararg word: Word) : Int =
        withContext(Dispatchers.IO) {
            db.wordDao.update(*word)
        }

    suspend fun delete(vararg word: Word) : Int =
        withContext(Dispatchers.IO) {
            db.wordDao.delete(*word)
        }
}