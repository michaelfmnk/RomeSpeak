package dev.fomenko.latinhelper.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import dev.fomenko.latinhelper.data.Phrase


@Composable
fun PhraseItem(
    modifier: Modifier = Modifier,
    phrase: Phrase,
    cardColor: Color
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = phrase.phrase,
                style = MaterialTheme.typography.bodyLarge
            )
            Divider(
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 8.dp)
            )
            Text(
                text = phrase.translation(Locale.current),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}
