package dev.fomenko.latinhelper.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.fomenko.latinhelper.data.Phrase


val EVEN_ROW_COLOR = Color(0xffe1e2ec)
val ODD_ROW_COLOR = Color(0xfff1f2fc)

@Composable
fun PhraseListScreen() {
    val viewModel = hiltViewModel<PhraseListViewModel>()
    val state = viewModel.phrases.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadPhrases()
    }

    LazyColumn {
        items(state.value.size) { index ->
            val color = if (index % 2 == 0) EVEN_ROW_COLOR else ODD_ROW_COLOR
            PhraseItem(
                modifier = Modifier
                    .padding(15.dp, 5.dp),
                cardColor = color,
                phrase = state.value[index],
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun GreetingPreview() {
    PhraseItem(
        phrase = Phrase(
            phrase = "phrase",
            en = "en",
            uk = "uk"
        ),
        cardColor = Color(0xffe1e2ec)
    )
}