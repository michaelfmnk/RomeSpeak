package dev.fomenko.latinhelper.presentation

import dev.fomenko.latinhelper.data.Phrase
import kotlinx.coroutines.flow.StateFlow

interface PhraseListViewModel {
    val phrases: StateFlow<List<Phrase>>

    suspend fun loadPhrases()
    suspend fun searchPhrases(term: String)
    fun sortBy(keyExtractor: (Phrase) -> String)
    suspend fun markAsFavorite(phrase: Phrase)
}