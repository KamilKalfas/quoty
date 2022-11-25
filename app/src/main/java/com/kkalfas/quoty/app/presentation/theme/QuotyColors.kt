package com.kkalfas.quoty.app.presentation.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private object QuotyColors {
    val white = Color.White
    val blue_gray = Color(0xFF546e7a)
    val blue_gray_light = Color(0xFFeceff1)
    val blue_gray_dark = Color(0xFF29434e)
    val pink = Color(0xFFc41061)
    val pink_light = Color(0xFFfc548e)
    val pink_dark = Color(0xFF8e0038)
}

val LightThemeColors = lightColors(
    primary = QuotyColors.blue_gray,
    primaryVariant = QuotyColors.blue_gray_dark,
    secondary = QuotyColors.pink,
    secondaryVariant = QuotyColors.pink_light,
    surface = QuotyColors.blue_gray_light,
    background = QuotyColors.white,
    onPrimary = QuotyColors.white,
    onSecondary = QuotyColors.white
)
