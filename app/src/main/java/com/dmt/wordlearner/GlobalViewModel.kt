package com.dmt.wordlearner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmt.wordlearner.utils.NO_INT_VALUE

class GlobalViewModel(initialFilter: Int) : ViewModel() {

    private val _filter = MutableLiveData(initialFilter)
    val filter : LiveData<Int>
    get() = _filter

    fun setFilter(value: Int) {
        _filter.value = value
    }
}