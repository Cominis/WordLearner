package com.dmt.wordlearner.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dmt.wordlearner.repository.TranslationRepository

class MainViewModelFactory(
    private val repo: TranslationRepository,
    private val filter: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repo, filter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}