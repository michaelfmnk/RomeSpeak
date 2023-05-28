package dev.fomenko.latinhelper.presentation

import android.content.res.Resources
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel


val EVEN_ROW_COLOR = Color(0xffe1e2ec)
val ODD_ROW_COLOR = Color(0xfff1f2fc)

@Composable
fun PhraseListScreen(
    viewModel: PhraseListViewModel = hiltViewModel()
) {
    val phrases = viewModel.phrases.collectAsState()
    val query = remember { mutableStateOf("") }
    val searchBarHeight = remember { mutableStateOf(0.dp) }

    LaunchedEffect(true) {
        viewModel.loadPhrases()
    }
    LaunchedEffect(query.value) {
        viewModel.searchPhrases(query.value)
    }

    Box {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .zIndex(1f)
                .onSizeChanged { searchBarHeight.value = it.height.pxToDp() }
            ) {

            SearchBar(
                value = query.value,
                onValueChange = {
                    query.value = it
                }
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(top = searchBarHeight.value + 50.dp)
        ) {
            items(phrases.value.size) { index ->
                val color = if (index % 2 == 0) EVEN_ROW_COLOR else ODD_ROW_COLOR
                PhraseItem(
                    modifier = Modifier
                        .padding(15.dp, 5.dp),
                    cardColor = color,
                    phrase = phrases.value[index],
                )
            }
        }
    }
}