package dev.fomenko.latinhelper.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState

@OptIn(ExperimentalMaterial3Api::class)
suspend fun SheetState.toggle() {
    if (isVisible) {
        hide()
    } else {
        expand()
    }
}