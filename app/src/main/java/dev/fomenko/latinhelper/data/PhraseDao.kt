package dev.fomenko.latinhelper.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhraseDao {
    @Query("SELECT * FROM phrases")
    suspend fun findAll(): List<Phrase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(phrases: List<Phrase>)
}