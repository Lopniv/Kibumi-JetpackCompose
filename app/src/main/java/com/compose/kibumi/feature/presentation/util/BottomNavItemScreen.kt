package com.compose.kibumi.feature.presentation.util

import com.compose.kibumi.R

sealed class BottomNavigationScreen(val route: String?, val title: String?, val iconSelected: Int?, val iconUnselected: Int?, val enabled: Boolean = true) {
    object Home: BottomNavigationScreen("home", "Home", R.drawable.icon_home_filled, R.drawable.icon_home_outline)
    object Market: BottomNavigationScreen("market", "Market", R.drawable.icon_market_filled, R.drawable.icon_market_outline)
    object Activity: BottomNavigationScreen("activity", "Activity", R.drawable.icon_activity_filled, R.drawable.icon_activity_outline)
    object Savings: BottomNavigationScreen("savings", "Savings", R.drawable.icon_savings_filled, R.drawable.icon_savings_outline)
}