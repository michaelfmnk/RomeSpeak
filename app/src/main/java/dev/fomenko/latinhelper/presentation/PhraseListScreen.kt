package dev.fomenko.latinhelper.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import dev.fomenko.latinhelper.data.Phrase
import dev.fomenko.latinhelper.data.PhraseSort
import dev.fomenko.latinhelper.ui.theme.LatinHelperTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


val EVEN_ROW_COLOR = Color(0xffe1e2ec)
val ODD_ROW_COLOR = Color(0xfff1f2fc)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhraseListScreen(
    viewModel: PhraseListViewModel = hiltViewModel<PhraseListViewModelImpl>()
) {
    val scope = rememberCoroutineScope()

    val phrases = viewModel.phrases.collectAsState()
    val selectedTab = viewModel.selectedTab.collectAsState()

    val query = viewModel.query.collectAsState()
    val sortSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false, initialValue = SheetValue.Hidden
        )
    )

    LaunchedEffect(true) {
        viewModel.loadPhrases()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.statusBarsPadding()
            ) {

                SearchBar(value = query.value, onValueChange = {
                    scope.launch {
                        viewModel.searchPhrases(it)
                    }
                })

            }

            Tabs(selectedTab = selectedTab, onSwitchTab = {
                scope.launch {
                    viewModel.switchTab(it)
                }
            })

            LazyColumn(
                contentPadding = PaddingValues(top = 20.dp)
            ) {
                items(phrases.value.size) { index ->
                    val color = if (index % 2 == 0) EVEN_ROW_COLOR else ODD_ROW_COLOR
                    PhraseListItem(modifier = Modifier.padding(15.dp, 5.dp),
                        cardColor = color,
                        phrase = phrases.value[index],
                        onFavoriteClick = {
                            scope.launch {
                                viewModel.markAsFavorite(phrases.value[index])
                            }
                        })
                }
            }
        }

        Box(
            modifier = Modifier
                .zIndex(10f)
        ) {
            PhraseListSortSheet(
                sortSheetState = sortSheetState,
                onSortSelected = {
                    scope.launch {
                        sortSheetState.bottomSheetState.toggle()
                        viewModel.sortBy(it)
                    }
                }
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(1f)
        ) {
            PhraseListBottomAppBar(onSortClicked = {
                scope.launch {
                    sortSheetState.bottomSheetState.toggle()
                }
            })
        }
    }
}

@Composable
fun Tabs(
    selectedTab: State<PhraseListTab>, onSwitchTab: (PhraseListTab) -> Unit
) {
    TabRow(selectedTabIndex = selectedTab.value.ordinal) {
        PhraseListTab.values().forEach { tab ->
            Tab(text = { Text(text = stringResource(tab.title)) },
                selected = selectedTab.value == tab,
                onClick = {
                    onSwitchTab(tab)
                })
        }
    }
}


@Preview
@Composable
fun PhraseListScreenPreview() {
    val viewModel = object : PhraseListViewModel {
        override val query: StateFlow<String> = MutableStateFlow("")
        override val sort: StateFlow<PhraseSort> = MutableStateFlow(PhraseSort.Latin)

        override val phrases: StateFlow<List<Phrase>> = MutableStateFlow(
            listOf(
                Phrase("Nota bene", en = "Note well", uk = "Зверни увагу"),
                Phrase("Carpe diem", en = "Seize the day", uk = "Схопи день"),
                Phrase(
                    "Cogito, ergo sum", en = "I think, therefore I am", uk = "Я думаю, отже я є"
                ),
            )
        )
        override val selectedTab: StateFlow<PhraseListTab> = MutableStateFlow(PhraseListTab.All)

        override suspend fun loadPhrases() {
        }

        override suspend fun searchPhrases(term: String) {
        }

        override suspend fun sortBy(sort: PhraseSort) {
        }

        override suspend fun markAsFavorite(phrase: Phrase) {

        }

        override suspend fun switchTab(tab: PhraseListTab) {
        }
    }

    LatinHelperTheme {
        PhraseListScreen(viewModel)
    }
}