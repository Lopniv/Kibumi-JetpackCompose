package com.compose.kibumi.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkColorPalette = darkColors(
    primary = Purple80, primaryVariant = PurpleGrey80, secondary = Pink80
)

private val LightColorPalette = lightColors(
    primary = Purple40, primaryVariant = PurpleGrey40, secondary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable fun KibumiJetpackTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit)
{
    val colors = LightColorPalette

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalFontSize provides FontSize()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}