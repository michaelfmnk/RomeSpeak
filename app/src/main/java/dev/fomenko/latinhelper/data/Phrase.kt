package dev.fomenko.latinhelper.data

import androidx.compose.ui.text.intl.Locale
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.Collator

@Entity(tableName = "phrases")
data class Phrase(
    @PrimaryKey val phrase: String = "",
    val en: String = "",
    val uk: String = "",
    var isFavorite: Boolean = false,
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
    createComparator: () -> Comparator<Phrase>,
) {
    Latin({ compareBy { it.phrase } }),
    English({ compareBy { it.en } }),
    Ukrainian({
        val collator = Collator.getInstance(java.util.Locale("uk", "UA"))
        compareBy { collator.getCollationKey(it.uk) }
    });

    val comparator: Comparator<Phrase> = createComparator()

    companion object {
        fun valueOf(value: Locale): PhraseSort = when (value.language) {
            "en" -> English
            "uk" -> Ukrainian
            else -> Latin
        }
    }
}
