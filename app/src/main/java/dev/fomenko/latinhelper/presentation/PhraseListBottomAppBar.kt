package dev.fomenko.latinhelper.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.fomenko.latinhelper.R

@Composable
fun PhraseListBottomAppBar(
    onSortClicked: () -> Unit
) {
    BottomAppBar(
        actions = {
            IconButton(onClick = onSortClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.sort_by_alpha),
                    modifier = Modifier.padding(7.dp),
                    contentDescription = "Sort by"
                )
            }
        }
    )
}