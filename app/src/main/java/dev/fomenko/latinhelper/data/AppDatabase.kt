package dev.fomenko.latinhelper.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhraseDao::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun phraseDao(): PhraseDao
}