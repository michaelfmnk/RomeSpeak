package dev.fomenko.latinhelper.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fomenko.latinhelper.data.Phrase
import dev.fomenko.latinhelper.data.PhraseDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PhraseListViewModel @Inject constructor(
    private val phraseDao: PhraseDao,
) : ViewModel() {
    private val _phrases: MutableStateFlow<List<Phrase>> = MutableStateFlow(listOf())
    val phrases: StateFlow<List<Phrase>> = _phrases.asStateFlow()

    suspend fun loadPhrases() {
        _phrases.value = phraseDao.findAll()
    }

    suspend fun searchPhrases(term: String) {
        _phrases.value = phraseDao.search(term)
    }
}