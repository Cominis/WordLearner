package com.dmt.wordlearner.ui.main

import androidx.lifecycle.*
import com.dmt.wordlearner.domain.Translation
import com.dmt.wordlearner.repository.TranslationRepository
import com.dmt.wordlearner.utils.*
import java.time.LocalDateTime

class MainViewModel(private val repo: TranslationRepository, filter: Int) : ViewModel() {

    private val start = MutableLiveData<LocalDateTime>()
    private val end = MutableLiveData<LocalDateTime>()

    private val allTranslations = repo.translations

    private val _translations = MediatorLiveData<List<Translation>>()
    val translations : LiveData<List<Translation>>
        get() = _translations

    init {
        applyFilter(filter)

        _translations.addSource(start) { start ->
            _translations.value = getFilteredResults(start, end, allTranslations)
        }
        _translations.addSource(end) { end ->
            _translations.value = getFilteredResults(start, end, allTranslations)
        }
        _translations.addSource(allTranslations) { list ->
            _translations.value = getFilteredResults(start, end, list)
        }
    }

    private fun getFilteredResults(
        start: LiveData<LocalDateTime>,
        end: LocalDateTime,
        allTranslations: LiveData<List<Translation>>
    ): List<Translation> =
        allTranslations.value?.filter { it.dateTime in start.value!!..end } ?: listOf()

    private fun getFilteredResults(
        start: LocalDateTime,
        end: LiveData<LocalDateTime>,
        allTranslations: LiveData<List<Translation>>
    ): List<Translation> =
        allTranslations.value?.filter { it.dateTime in start..end.value!! } ?: listOf()

    private fun getFilteredResults(
        start: LiveData<LocalDateTime>,
        end: LiveData<LocalDateTime>,
        allTranslations: List<Translation>
    ): List<Translation> =
        allTranslations.filter { it.dateTime in start.value!!..end.value!! }

    fun applyFilter(value: Int){
        when(value){
            1 -> {
                start.value = startOfDay()
                end.value = endOfDay()
            }
            2 -> {
                start.value = startOfWeek()
                end.value = endOfWeek()
            }
            3 -> {
                start.value = startOfMonth()
                end.value = endOfMonth()
            }
            4 -> {
                start.value = startOfYear()
                end.value = endOfYear()
            }
            else -> {
                start.value = startOfYear()
                end.value = endOfYear()
            }
        }
    }
}