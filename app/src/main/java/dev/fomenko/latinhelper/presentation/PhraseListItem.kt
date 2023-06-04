package dev.fomenko.latinhelper.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.fomenko.latinhelper.data.Phrase


@Composable
fun PhraseListItem(
    modifier: Modifier = Modifier,
    phrase: Phrase,
    cardColor: Color = MaterialTheme.colorScheme.primaryContainer,
    locale: Locale = Locale.current,
    onFavoriteClick: () -> Unit = {},
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Column(
                modifier = Modifier
                    .weight(7f)
                    .padding(16.dp)
                    .border(1.dp, Color.Red, MaterialTheme.shapes.small)
            ) {
                Text(
                    text = phrase.phrase, style = MaterialTheme.typography.bodyLarge
                )
                Spacer(
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 8.dp)
                )
                Text(
                    text = phrase.translation(locale),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically)
                    .fillMaxHeight()
                    .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                    .padding(10.dp),
                onClick = onFavoriteClick
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Favorite",
                    tint = if (phrase.isFavorite) MaterialTheme.colorScheme.tertiary else Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun PhraseItemDefault_Even() {
    val phrase = Phrase("Nota bene as;ldkfja;lksdf ja;slkdf asdfadsf", en = "Note well")

    PhraseListItem(phrase = phrase)
}


@Preview
@Composable
fun PhraseItemDefault_Odd() {
    val phrase = Phrase("Nota bene", en = "Note well")

    PhraseListItem(phrase = phrase)
}

