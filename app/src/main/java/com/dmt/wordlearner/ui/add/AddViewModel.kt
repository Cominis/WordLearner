package com.dmt.wordlearner.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmt.wordlearner.db.tables.Word
import com.dmt.wordlearner.repository.TranslationRepository
import com.dmt.wordlearner.utils.NO_LONG_VALUE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class AddViewModel(private val repo : TranslationRepository, private val wordId: Long) : ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    val word = MutableLiveData("")
    val translation = MutableLiveData("")
    val language = MutableLiveData("")

    val languages = repo.languages

    init {
        if(wordId != NO_LONG_VALUE) {
            coroutineScope.launch {
                val currentTranslation = repo.getTranslation(wordId)
                word.value = currentTranslation.word
                translation.value = currentTranslation.translation
                language.value = currentTranslation.languageCode
            }
        }
    }


    private val _navigateToMainFragment = MutableLiveData<Boolean>(null)
    val navigateToMainFragment : LiveData<Boolean>
        get() = _navigateToMainFragment

    fun navigateMainFragment() {
        _navigateToMainFragment.value = true
    }

    fun doneNavigatingMainFragment() {
        _navigateToMainFragment.value = null
    }

    fun addWord() {
        val languageId = languages.value?.firstOrNull { it.languageCode == language.value }?.id ?: NO_LONG_VALUE
        if(languageId != NO_LONG_VALUE && !word.value.isNullOrBlank() && !translation.value.isNullOrBlank()) {

            val date = LocalDateTime.now()

            val newWord = Word(
                id = if(wordId != NO_LONG_VALUE) wordId else 0L,
                word = word.value!!,
                translation = translation.value!!,
                dateTime = date,
                weekday = date.dayOfWeek.value,
                languageId = languageId
            )

            coroutineScope.launch {
                if(wordId != NO_LONG_VALUE)
                    repo.update(newWord)
                else
                    repo.insert(newWord)
                navigateMainFragment()
            }

        }

    }
}