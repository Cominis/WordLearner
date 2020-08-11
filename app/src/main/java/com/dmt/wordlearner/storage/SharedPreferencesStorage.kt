package com.dmt.wordlearner.storage

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPreferencesStorage constructor(context: Context) : IStorage {

    private val sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(context)

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }
}