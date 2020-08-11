package com.dmt.wordlearner.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dmt.wordlearner.db.daos.LanguageDao
import com.dmt.wordlearner.db.daos.WordDao
import com.dmt.wordlearner.db.tables.Language
import com.dmt.wordlearner.db.tables.Word

@Database(
    entities = [Word::class, Language::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WordsDatabase : RoomDatabase() {

    abstract val wordDao: WordDao
    abstract val languageDao: LanguageDao

    companion object {
        @Volatile
        private var INSTANCE: WordsDatabase? = null

        fun getInstance(context: Context): WordsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordsDatabase::class.java,
                        "translations_database"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}


