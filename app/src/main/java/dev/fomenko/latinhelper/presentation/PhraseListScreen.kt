package dev.fomenko.latinhelper.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.fomenko.latinhelper.data.Phrase
import dev.fomenko.latinhelper.data.PhraseDao


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


@Composable
@Preview(showBackground = true)
fun GreetingPreview() {

    val phraseDao = object : PhraseDao {
        override suspend fun findAll(): List<Phrase> {
            return listOf(
                Phrase(phrase = "Nota bene", en = "Note well", uk = "Зверни увагу"),
                Phrase(
                    phrase = "Festina lente",
                    en = "Make haste slowly",
                    uk = "Поспішай повільно"
                ),
                Phrase(phrase = "Carpe diem", en = "Seize the day", uk = "Хапай день")
            )
        }

        override suspend fun insertAll(phrases: List<Phrase>) {
            TODO("Not yet implemented")
        }

        override suspend fun search(query: String): List<Phrase> {
            TODO("Not yet implemented")
        }

    }

    val viewModel = PhraseListViewModel(phraseDao)

    Column {

        PhraseListScreen(
            viewModel = viewModel
        )


    }
}