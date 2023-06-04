package dev.fomenko.latinhelper.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.fomenko.latinhelper.R

@Composable
fun PhraseListBottomAppBar(
    modifier: Modifier = Modifier,
    onSortClicked: () -> Unit
) {
    BottomAppBar(
        modifier = modifier,
        actions = {
            IconButton(onClick = onSortClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.sort_by_alpha),
                    modifier = Modifier.padding(7.dp),
                    contentDescription = stringResource(id = R.string.sort_by)
                )
            }
        }
    )
}