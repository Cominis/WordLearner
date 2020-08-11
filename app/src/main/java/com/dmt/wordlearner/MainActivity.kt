package com.dmt.wordlearner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dmt.wordlearner.db.WordsDatabase
import com.dmt.wordlearner.db.tables.Language
import com.dmt.wordlearner.storage.SharedPreferencesStorage
import com.dmt.wordlearner.ui.main.MainViewModelFactory
import com.dmt.wordlearner.utils.ALL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val sharedPref = SharedPreferencesStorage(applicationContext)
        val filter = sharedPref.getString("listPref").toIntOrNull() ?: ALL
        val factory = GlobalViewModelFactory(filter)
        ViewModelProvider(this, factory).get(GlobalViewModel::class.java)

        //val db = WordsDatabase.getInstance(applicationContext)
        //val langs = arrayOf(Language(languageCode = "RUS"), Language(languageCode = "EN"))
        //db.languageDao.insert(*langs)

    }
}