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
    val pl: String = "",
) {

    fun translation(lang: Locale): String {
        return when (lang.language) {
            "en" -> en
            "uk" -> uk
            "pl" -> pl
            else -> en
        }
    }
}

enum class PhraseSort(
    val lang: String,
    createComparator: () -> Comparator<Phrase>,
) {
    Latin("latin", { compareBy { it.phrase } }),
    English("en", { compareBy { it.en } }),
    Ukrainian("uk", {
        val collator = Collator.getInstance(java.util.Locale("uk", "UA"))
        compareBy { collator.getCollationKey(it.uk) }
    }),
    Polish("pl", {
        val collator = Collator.getInstance(java.util.Locale("pl", "PL"))
        compareBy { collator.getCollationKey(it.pl) }
    });

    val comparator: Comparator<Phrase> = createComparator()

    companion object {
        fun valueOf(value: Locale): PhraseSort = PhraseSort.values()
            .find { it.lang == value.language } ?: Latin
    }
}
