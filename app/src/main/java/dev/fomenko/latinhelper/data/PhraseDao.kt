package dev.fomenko.latinhelper.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhraseDao {
    @Query("SELECT * FROM phraseentity")
    suspend fun findAll(): List<PhraseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(phrases: List<PhraseEntity>)
}