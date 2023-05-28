package dev.fomenko.latinhelper.presentation

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Int.pxToDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).toInt().dp
}