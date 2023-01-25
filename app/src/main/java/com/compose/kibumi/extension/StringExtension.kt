package com.compose.kibumi.extension

fun String.convertToCurrency(): String
{
    return "Rp $this"
}