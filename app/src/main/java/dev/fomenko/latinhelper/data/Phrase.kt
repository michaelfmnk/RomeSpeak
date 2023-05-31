package dev.fomenko.latinhelper.data

import androidx.compose.ui.text.intl.Locale
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phrases")
data class Phrase(
    @PrimaryKey val phrase: String = "",
    val en: String = "",
    val uk: String = "",
    var isFavorite: Boolean = false
) {

    fun translation(lang: Locale): String {
        return when (lang.language) {
            "en" -> en
            "uk" -> uk
            else -> en
        }
    }
}

enum class PhraseSort(
    var column: String,
    var keyExtractor: (Phrase) -> String
) {
    Latin("phrase", { it.phrase }),
    English("en", { it.en })
}