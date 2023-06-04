package dev.fomenko.latinhelper.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fomenko.latinhelper.R
import dev.fomenko.latinhelper.data.Phrase
import dev.fomenko.latinhelper.data.PhraseDao
import dev.fomenko.latinhelper.data.PhraseSort
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PhraseListViewModelImpl @Inject constructor(
    private val phraseDao: PhraseDao,
) : ViewModel(), PhraseListViewModel {
    private val _phrases: MutableStateFlow<List<Phrase>> = MutableStateFlow(listOf())
    override val phrases: StateFlow<List<Phrase>> = _phrases.asStateFlow()

    private val _selectedTab: MutableStateFlow<PhraseListTab> = MutableStateFlow(PhraseListTab.All)
    override val selectedTab: StateFlow<PhraseListTab> = _selectedTab.asStateFlow()

    private val _sort = MutableStateFlow(PhraseSort.Latin)
    override val sort: StateFlow<PhraseSort> = _sort.asStateFlow()

    private val _query = MutableStateFlow("")
    override val query: StateFlow<String> = _query.asStateFlow()

    override suspend fun loadPhrases() {
        _phrases.value = _selectedTab.value.fetcher(phraseDao, query.value)
        sortBy(sort.value)
    }

    override suspend fun sortBy(sort: PhraseSort) {
        _phrases.value = _phrases.value.sortedBy(sort.keyExtractor)
    }

    override suspend fun searchPhrases(term: String) {
        _query.value = term
        loadPhrases()
    }

    override suspend fun markAsFavorite(phrase: Phrase) {
        phraseDao.save(phrase.copy(isFavorite = !phrase.isFavorite))
        loadPhrases()
    }

    override suspend fun switchTab(tab: PhraseListTab) {
        _selectedTab.value = tab
        loadPhrases()
    }
}

enum class PhraseListTab(
    val title: Int,
    val fetcher: suspend (PhraseDao, String) -> List<Phrase>,
) {
    All(
        R.string.tab_all,
        { dao, query -> dao.search(query) },
    ),
    Favorite(
        R.string.tab_favorites,
        { dao, query -> dao.searchFavorite(query) },
    ),
}
