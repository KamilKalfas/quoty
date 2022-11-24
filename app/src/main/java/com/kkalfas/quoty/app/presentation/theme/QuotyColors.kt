package com.kkalfas.quoty.app.presentation.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private object QuotyColors {
    val white = Color.White
    val blue_gray = Color(0xFF546e7a)
    val blue_gray_light = Color(0xFF819ca9)
    val blue_gray_dark = Color(0xFF29434e)
    val pink = Color(0xFFc41061)
    val pink_light = Color(0xFFfc548e)
    val pink_dark = Color(0xFF8e0038)
    val almond = Color(0xFFC9C2BF)
}

val LightThemeColors = lightColors(
    primary = QuotyColors.blue_gray,
    secondary = QuotyColors.pink,
    surface = QuotyColors.white,
    background = QuotyColors.almond,
    onPrimary = QuotyColors.white
)
