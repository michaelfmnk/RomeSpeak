package dev.fomenko.latinhelper.presentation

import dev.fomenko.latinhelper.data.Phrase
import dev.fomenko.latinhelper.data.PhraseSort
import kotlinx.coroutines.flow.StateFlow

interface PhraseListViewModel {
    val query: StateFlow<String>
    val sort: StateFlow<PhraseSort>
    val phrases: StateFlow<List<Phrase>>
    val selectedTab: StateFlow<PhraseListTab>
    suspend fun loadPhrases()
    suspend fun searchPhrases(term: String)
    suspend fun sortBy(sort: PhraseSort)
    suspend fun markAsFavorite(phrase: Phrase)
    suspend fun switchTab(tab: PhraseListTab)
}
