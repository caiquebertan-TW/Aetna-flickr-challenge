package com.caique.aetnatestflickr.ui.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.caique.aetnatestflickr.core.Colors.Purple200
import com.caique.aetnatestflickr.core.Colors.Purple500
import com.caique.aetnatestflickr.core.Colors.Teal200
import com.caique.aetnatestflickr.core.Colors.Teal500

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        content = content,
        colorScheme = colors
    )
}

private val DarkColorPalette = darkColorScheme(
    primary = Teal200,
    secondary = Purple200,
    background = Color.Black,
    surface = Color.DarkGray,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorPalette = lightColorScheme(
    primary = Teal500,
    secondary = Purple500,
    background = Color.White,
    surface = Color.LightGray,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)
