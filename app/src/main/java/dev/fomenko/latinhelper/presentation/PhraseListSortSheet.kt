package dev.fomenko.latinhelper.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.fomenko.latinhelper.R
import dev.fomenko.latinhelper.data.PhraseSort

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhraseListSortSheet(
    sortSheetState: BottomSheetScaffoldState,
    onSortSelected: (PhraseSort) -> Unit,
) {
    BottomSheetScaffold(
        scaffoldState = sortSheetState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp, 0.dp, 50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.sort_by),
                    modifier = Modifier
                        .padding(0.dp, 15.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = stringResource(id = R.string.sort_by_latin),

                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            onSortSelected(PhraseSort.Latin)
                        }
                )
                Text(
                    text = stringResource(id = R.string.sort_by_english),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            onSortSelected(PhraseSort.English)
                        }
                )
            }
        }) {
    }
}
