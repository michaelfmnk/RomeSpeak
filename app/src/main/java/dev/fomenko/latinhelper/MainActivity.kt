package dev.fomenko.latinhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.fomenko.latinhelper.presentation.PhraseListScreen
import dev.fomenko.latinhelper.ui.theme.LatinHelperTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            LatinHelperTheme {
                PhraseListScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LatinHelperTheme {
    }
}