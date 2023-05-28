package dev.fomenko.latinhelper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PhraseEntity {
    @PrimaryKey val phrase: String = ""
    val en: String = ""
    val uk: String = ""
}