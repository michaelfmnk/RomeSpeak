package dev.fomenko.latinhelper.data

import androidx.compose.ui.text.intl.Locale
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phrases")
data class Phrase(
    @PrimaryKey val phrase: String = "",
    val en: String = "",
    val uk: String = "",
) {
    fun translation(lang: Locale): String {
        return when (lang.language) {
            "en" -> en
            "uk" -> uk
            else -> en
        }
    }
}