package com.compose.kibumi.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing
(
    val DEFAULT: Dp = 0.dp,
    val THIN: Dp = 2.dp,
    val TINY: Dp = 5.dp,
    val NARROW: Dp = 7.5.dp,
    val LITTLE: Dp = 10.dp,
    val SMALL: Dp = 15.dp,
    val MEDIUM: Dp = 20.dp,
    val LARGE: Dp = 25.dp,
    val HUGE: Dp = 30.dp,
    val ENORMOUS: Dp = 40.dp,
    val BIG: Dp = 50.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }