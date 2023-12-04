package com.caique.aetnatestflickr.core

import androidx.compose.ui.graphics.Color

object Colors {
    val Teal200 = Color(0xFF03DAC5)
    val Teal500 = Color(0xFF009688)
    val Teal700 = Color(0xFF00796B)
    val Purple200 = Color(0xFFBB86FC)
    val Purple500 = Color(0xFF6200EA)
}

object LightColorPalette {
    val primary = Colors.Teal500
    val primaryVariant = Colors.Teal700
    val secondary = Colors.Purple500
    val background = Color.White
    val surface = Color.LightGray
    val onPrimary = Color.White
    val onSecondary = Color.Black
    val onBackground = Color.Black
    val onSurface = Color.Black
}

object DarkColorPalette {
    val primary = Colors.Teal200
    val primaryVariant = Colors.Teal700
    val secondary = Colors.Purple200
    val background = Color.Black
    val surface = Color.DarkGray
    val onPrimary = Color.White
    val onSecondary = Color.Black
    val onBackground = Color.White
    val onSurface = Color.White
}
