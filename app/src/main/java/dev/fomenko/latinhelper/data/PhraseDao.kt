package dev.fomenko.latinhelper.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhraseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(phrases: List<Phrase>)

    @Query(
        """SELECT * FROM phrases WHERE phrase LIKE '%' || :query || '%' 
        OR en LIKE '%' || :query || '%'
        OR uk LIKE '%' || :query || '%'""",
    )
    suspend fun search(query: String): List<Phrase>

    @Query(
        """SELECT * FROM phrases WHERE (phrase LIKE '%' || :query || '%' 
        OR en LIKE '%' || :query || '%'
        OR uk LIKE '%' || :query || '%')
        AND isFavorite = 1""",
    )
    suspend fun searchFavorite(query: String): List<Phrase>

    @Query("SELECT * FROM phrases WHERE phrase = :id")
    suspend fun findByPhrase(id: String): Phrase?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(phrase: Phrase)
}
