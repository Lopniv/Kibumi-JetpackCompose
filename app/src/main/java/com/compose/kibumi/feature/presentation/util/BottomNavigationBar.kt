package com.compose.kibumi.feature.presentation.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.compose.kibumi.R
import com.compose.kibumi.feature.presentation.activity.ActivityScreen
import com.compose.kibumi.feature.presentation.home.HomeScreen
import com.compose.kibumi.feature.presentation.market.MarketScreen
import com.compose.kibumi.feature.presentation.savings.SavingsScreen
import com.compose.kibumi.feature.presentation.subscription.DetailSubmissionScreen
import com.compose.kibumi.feature.presentation.subscription.DetailSubscriptionPackageScreen
import com.compose.kibumi.feature.presentation.subscription.SubscriptionPackageScreen
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_PRIMARY_NORMAL
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun BottomNav(
    navController: NavController,
    bottomBarState: MutableState<Boolean>)
{
    val items = listOf(
        BottomNavigationScreen.Home, BottomNavigationScreen.Market, BottomNavigationScreen.Activity, BottomNavigationScreen.Savings
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                modifier = Modifier
                    .padding(LocalSpacing.current.DEFAULT)
                    .height(100.dp),
                backgroundColor = Color.White,
                elevation = LocalSpacing.current.LITTLE)
            {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination

                val selectedByIndex = remember { mutableStateOf(0) }

                items.forEachIndexed { index, it ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector =
                                if (selectedByIndex.value == index)
                                {
                                    ImageVector.vectorResource(id = it.iconSelected ?: R.drawable.icon_null)
                                }
                                else
                                {
                                    ImageVector.vectorResource(id = it.iconUnselected ?: R.drawable.icon_null)
                                },
                                contentDescription = "",
                                modifier = Modifier.size(26.dp),
                                tint = if (!it.enabled) Color.White else THEME_PRIMARY_NORMAL
                            )
                        },
                        label = {
                            it.title?.let {
                                Text(
                                    text = it,
                                )
                            }
                        },
                        selected = currentRoute?.hierarchy?.any { it.route == it.route } == true,
                        enabled = it.enabled,

                        onClick = {
                            selectedByIndex.value = index
                            it.route?.let { it1 ->
                                navController.navigate(it1) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}

@ExperimentalPagerApi
@Composable
fun MainScreenNavigation(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    scaffoldState: ScaffoldState,
    openDialog: MutableState<Boolean>)
{
    NavHost(navController, startDestination = BottomNavigationScreen.Home.route!!)
    {

        //home
        composable(BottomNavigationScreen.Home.route)
        {
            LaunchedEffect(Unit)
            {
                bottomBarState.value = true
            }
            HomeScreen(navController, scaffoldState, openDialog)
        }

        //market
        composable(BottomNavigationScreen.Market.route!!)
        {
            LaunchedEffect(Unit)
            {
                bottomBarState.value = true
            }
            MarketScreen(navController, scaffoldState)
        }

        //activity
        composable(BottomNavigationScreen.Activity.route!!)
        {
            LaunchedEffect(Unit)
            {
                bottomBarState.value = true
            }
            ActivityScreen(navController, scaffoldState)
        }

        //savings
        composable(BottomNavigationScreen.Savings.route!!)
        {
            LaunchedEffect(Unit)
            {
                bottomBarState.value = true
            }
            SavingsScreen(navController, scaffoldState)
        }

        //product subscription
        composable(Screen.ProductSubscription.route)
        {
            LaunchedEffect(Unit)
            {
                bottomBarState.value = false
            }
            SubscriptionPackageScreen(navController)
        }

        //detail product
        composable(route = Screen.DetailProductSubscription.route + "/{id}",
            arguments = listOf(
                navArgument("id")
                {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            ))
        {
            LaunchedEffect(Unit)
            {
                bottomBarState.value = false
            }
            DetailSubscriptionPackageScreen(navController, it.arguments?.getInt("id") ?: 0)
        }

        //detail submission
        composable(route = Screen.DetailSubmission.route + "/{id}",
            arguments = listOf(
                navArgument("id")
                {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            ))
        {
            LaunchedEffect(Unit)
            {
                bottomBarState.value = false
            }
            DetailSubmissionScreen(navController, it.arguments?.getInt("id") ?: 0)
        }
    }
}