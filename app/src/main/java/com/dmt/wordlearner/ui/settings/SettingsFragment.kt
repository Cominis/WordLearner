package com.dmt.wordlearner.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.dmt.wordlearner.GlobalViewModel
import com.dmt.wordlearner.R
import com.dmt.wordlearner.utils.ALL

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var globalViewModel : GlobalViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        globalViewModel = ViewModelProvider(requireActivity()).get(GlobalViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)
        val summaryProvider = Preference.SummaryProvider<ListPreference> { listPreference ->
            when(listPreference.value) {
                "1" -> "Today"
                "2" -> "This week"
                "3" -> "This month"
                "4" -> "All"
                else -> "All"
            }
        }
        val listener = Preference.OnPreferenceChangeListener { preference, newValue ->
                globalViewModel.setFilter(newValue.toString().toIntOrNull() ?: ALL)
                true
            }

        val listPref = findPreference<ListPreference>("listPref")
        listPref?.summaryProvider = summaryProvider
        listPref?.onPreferenceChangeListener = listener
    }

}