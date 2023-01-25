package com.compose.kibumi.feature.presentation.util

sealed class Screen(val route: String)
{
    object ProductSubscription: Screen("product_subscription")
    object DetailProductSubscription: Screen("detail_product")
    object DetailSubmission: Screen("detail_submission")
    object Payment: Screen("payment")

    fun withArgs(vararg args: String): String
    {
        return buildString()
        {
            append(route)
            args.forEach()
            { arg ->
                append("/$arg")
            }
        }
    }
}
