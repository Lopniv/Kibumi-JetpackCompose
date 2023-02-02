package com.compose.kibumi.feature.presentation.util

sealed class Screen(val route: String)
{
    object ProductSubscription: Screen("product_subscription")
    object DetailProductSubscription: Screen("detail_product")
    object DetailSubmission: Screen("detail_submission")
    object Payment: Screen("payment")
    object SchedulePickup: Screen("schedule_pickup")
    object WasteSell: Screen("waste_sell")
    object Notification: Screen("notification")
    object DetailNotification: Screen("detail_notification")
    object Profile: Screen("profile")
    object Address: Screen("address")
    object ChangePassword: Screen("change_password")
    object AllSavingsScreen: Screen("all_savings_screen")

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
