package dev.fomenko.latinhelper.presentation

import android.content.res.Resources
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Int.pxToDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).toInt().dp
}

@OptIn(ExperimentalMaterial3Api::class)
suspend fun SheetState.toggle() {
    if (isVisible) {
        hide()
    } else {
        expand()
    }
}