package dev.fomenko.latinhelper.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fomenko.latinhelper.data.Phrase
import dev.fomenko.latinhelper.data.PhraseDao
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

    override suspend fun loadPhrases() {
        _phrases.value = phraseDao.findAll()
    }

    override suspend fun searchPhrases(term: String) {
        _phrases.value = phraseDao.search(term)
    }

    override fun sortBy(keyExtractor: (Phrase) -> String) {
        _phrases.value = _phrases.value.sortedBy(keyExtractor)
    }

    override suspend fun markAsFavorite(phrase: Phrase) {
        _phrases.value = _phrases.value.map { currentPhrase ->
            if (currentPhrase == phrase) {
                currentPhrase.copy(isFavorite = !currentPhrase.isFavorite).also {
                    phraseDao.save(it)
                }
            } else {
                currentPhrase
            }
        }
    }
}