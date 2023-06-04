package dev.fomenko.latinhelper.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.fomenko.latinhelper.R
import dev.fomenko.latinhelper.data.PhraseSort

@Composable
fun PhraseListSortSheet(
    onSortSelected: (PhraseSort) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
    ) {
        Text(
            text = stringResource(id = R.string.sort_by),
            modifier = Modifier
                .padding(0.dp, 15.dp),
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = stringResource(id = R.string.sort_by_latin),

            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onSortSelected(PhraseSort.Latin)
                },
        )
        Text(
            text = stringResource(id = R.string.sort_by_english),
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onSortSelected(PhraseSort.English)
                },
        )
    }
}
