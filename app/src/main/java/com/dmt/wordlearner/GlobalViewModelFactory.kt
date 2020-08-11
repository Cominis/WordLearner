package com.dmt.wordlearner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dmt.wordlearner.repository.TranslationRepository
import com.dmt.wordlearner.ui.main.MainViewModel

class GlobalViewModelFactory(
    private val filter: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GlobalViewModel::class.java)) {
            return GlobalViewModel(filter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}