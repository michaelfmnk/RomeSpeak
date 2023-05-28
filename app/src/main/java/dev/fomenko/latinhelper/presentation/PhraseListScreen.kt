package dev.fomenko.latinhelper.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


val EVEN_ROW_COLOR = Color(0xffe1e2ec)
val ODD_ROW_COLOR = Color(0xfff1f2fc)

@Composable
fun PhraseListScreen(
    viewModel: PhraseListViewModel = hiltViewModel()
) {
    val phrases = viewModel.phrases.collectAsState()
    val query = remember { mutableStateOf("") }

    LaunchedEffect(true) {
        viewModel.loadPhrases()
    }
    LaunchedEffect(query.value) {
        viewModel.searchPhrases(query.value)
    }

    Column {
        SearchBar(
            value = query.value,
            onValueChange = {
                Log.d("PhraseListScreen", "onValueChange: $it")
                query.value = it
            }
        )

        LazyColumn {
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