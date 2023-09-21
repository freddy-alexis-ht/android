package com.sunday.appteoria.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Green700,
    primaryVariant = Green900,
    secondary = Green700,
    onError = Red800
)

private val DarkColorPalette = darkColors(
    primary = LightGreen700,
    primaryVariant = LightGreen900,
    secondary = LightGreen700,
    onError = Red300
)

@Composable
fun AppTeoriaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}