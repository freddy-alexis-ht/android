package com.sunday.appteoria.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sunday.appteoria.R

private val Inconsolata = FontFamily(
    Font(R.font.inconsolata_extralight, FontWeight.ExtraLight),
    Font(R.font.inconsolata_light, FontWeight.Light),
    Font(R.font.inconsolata_regular, FontWeight.Normal),
    Font(R.font.inconsolata_medium, FontWeight.Medium),
    Font(R.font.inconsolata_semibold, FontWeight.SemiBold),
    Font(R.font.inconsolata_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Inconsolata,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = Inconsolata,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = Inconsolata,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = Inconsolata,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)