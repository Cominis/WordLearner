package com.dmt.wordlearner.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dmt.wordlearner.repository.TranslationRepository

class AddViewModelFactory(
    private val repo: TranslationRepository,
    private val wordId: Long
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            return AddViewModel(repo, wordId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}