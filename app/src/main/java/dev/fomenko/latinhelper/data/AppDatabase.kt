package dev.fomenko.latinhelper.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Phrase::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun phraseDao(): PhraseDao

}

