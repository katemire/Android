package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = OrangePrimary,
    onPrimary = Color.White,
    secondary = OrangeLight,
    onSecondary = TextDark,
    secondaryContainer = Color(0xFFF0DDC9),
    onSecondaryContainer = TextDark,
    tertiary = Sage,
    onTertiary = Color.White,
    background = Cream,
    onBackground = TextDark,
    surface = Color.White,
    onSurface = TextDark,
    surfaceVariant = Color(0xFFF2ECE5),
    onSurfaceVariant = GrayText,
    outline = Color(0xFFCFC2B5),
    outlineVariant = WarmBorder,
    error = RedAccent,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFE28A6E),
    onPrimary = Color(0xFF351B14),
    secondary = Color(0xFFD8AD79),
    onSecondary = Color(0xFF302316),
    secondaryContainer = DarkOrange,
    onSecondaryContainer = DarkText,
    tertiary = Color(0xFFA4B39A),
    onTertiary = Color(0xFF1B261B),
    background = DarkBackground,
    onBackground = DarkText,
    surface = Color(0xFF211F1C),
    onSurface = DarkText,
    surfaceVariant = DarkCard,
    onSurfaceVariant = DarkGrayText,
    outline = Color(0xFF75685E),
    outlineVariant = DarkBorder,
    error = Color(0xFFFF8A80),
    onError = Color(0xFF3D0805)
)

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        shapes = AppShapes,
        content = content
    )
}
