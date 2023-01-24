package com.compose.kibumi.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class FontSize
(
    val DEFAULT: TextUnit = 12.sp,
    val SMALL: TextUnit = 14.sp,
    val MEDIUM: TextUnit = 16.sp,
    val LARGE: TextUnit = 18.sp,
)

val LocalFontSize = compositionLocalOf { FontSize() }