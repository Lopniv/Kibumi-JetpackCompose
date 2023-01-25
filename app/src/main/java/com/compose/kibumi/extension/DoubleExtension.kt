package com.compose.kibumi.extension

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.convertToStringThousandSeparator(fraction: Int = 0, format: String = "#" + "," + "###" + "." + "##"): String
{
    val decimalFormatSymbols = DecimalFormatSymbols(Locale.getDefault())
    decimalFormatSymbols.groupingSeparator = ".".single()
    decimalFormatSymbols.decimalSeparator = ",".single()
    val formatDecimal: DecimalFormat = DecimalFormat(format, decimalFormatSymbols)

    if (fraction > 0)
    {
        formatDecimal.isDecimalSeparatorAlwaysShown = true
        formatDecimal.minimumFractionDigits = fraction
    }
    else
    {
        formatDecimal.isDecimalSeparatorAlwaysShown = false
    }
    return formatDecimal.format(this)
}