package com.dmt.wordlearner.ui.settings

import androidx.lifecycle.ViewModel
import com.dmt.wordlearner.repository.TranslationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SettingsViewModel(private val repo : TranslationRepository) : ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

}