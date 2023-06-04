package dev.fomenko.latinhelper.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.fomenko.latinhelper.data.Phrase
import dev.fomenko.latinhelper.data.PhraseSort
import dev.fomenko.latinhelper.ui.theme.LatinHelperTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhraseListScreen(
    viewModel: PhraseListViewModel = hiltViewModel<PhraseListViewModelImpl>(),
) {
    val scope = rememberCoroutineScope()

    val phrases = viewModel.phrases.collectAsState()
    val selectedTab = viewModel.selectedTab.collectAsState()

    val query = viewModel.query.collectAsState()
    val sortSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false,
            initialValue = SheetValue.Hidden,
        ),
    )

    LaunchedEffect(true) {
        viewModel.loadPhrases()
    }

    PhraseListScreenScaffold(bottomSheetState = sortSheetState, sheetContent = {
        PhraseListSortSheet(onSortSelected = {
            scope.launch {
                sortSheetState.bottomSheetState.toggle()
                viewModel.sortBy(it)
            }
        })
    }, bottomBar = {
        PhraseListBottomAppBar(onSortClicked = {
            scope.launch {
                sortSheetState.bottomSheetState.toggle()
            }
        })
    }) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
        ) {
            SearchBar(value = query.value, onValueChange = {
                scope.launch {
                    viewModel.searchPhrases(it)
                }
            })

            Tabs(selectedTab = selectedTab, onSwitchTab = {
                scope.launch {
                    viewModel.switchTab(it)
                }
            })

            LazyColumn(
                contentPadding = PaddingValues(top = 20.dp),
            ) {
                items(phrases.value.size) { index ->
                    val color =
                        if (index % 2 == 0) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer
                    PhraseListItem(
                        modifier = Modifier.padding(15.dp, 5.dp),
                        cardColor = color,
                        phrase = phrases.value[index],
                        onFavoriteClick = {
                            scope.launch {
                                viewModel.markAsFavorite(phrases.value[index])
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PhraseListScreenScaffold(
    bottomSheetState: BottomSheetScaffoldState,
    sheetContent: @Composable ColumnScope.() -> Unit,
    bottomBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetContent = sheetContent,
    ) {
        Scaffold(bottomBar = bottomBar, content = content)
    }
}

@Composable
fun Tabs(
    selectedTab: State<PhraseListTab>,
    onSwitchTab: (PhraseListTab) -> Unit,
) {
    TabRow(selectedTabIndex = selectedTab.value.ordinal) {
        PhraseListTab.values().forEach { tab ->
            Tab(
                text = { Text(text = stringResource(tab.title)) },
                selected = selectedTab.value == tab,
                onClick = {
                    onSwitchTab(tab)
                },
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PhraseListScreenPreview() {
    val viewModel = object : PhraseListViewModel {
        override val query: StateFlow<String> = MutableStateFlow("")
        override val sort: StateFlow<PhraseSort> = MutableStateFlow(PhraseSort.Latin)

        override val phrases: StateFlow<List<Phrase>> = MutableStateFlow(
            listOf(
                Phrase("Nota bene", en = "Note well", uk = "Зверни увагу"),
                Phrase("Carpe diem", en = "Seize the day", uk = "Схопи день"),
                Phrase("Carpe diem", en = "Seize the day", uk = "Схопи день"),
                Phrase("Carpe diem", en = "Seize the day", uk = "Схопи день"),
                Phrase("Carpe diem", en = "Seize the day", uk = "Схопи день"),
                Phrase("Carpe diem", en = "Seize the day", uk = "Схопи день"),
                Phrase(
                    "Cogito, ergo sum",
                    en = "I think, therefore I am",
                    uk = "Я думаю, отже я є",
                ),
            ),
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
