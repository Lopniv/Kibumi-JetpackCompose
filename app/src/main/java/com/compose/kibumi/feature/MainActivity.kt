package com.compose.kibumi.feature

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.kibumi.R
import com.compose.kibumi.feature.domain.model.MenuItemModel
import com.compose.kibumi.feature.presentation.util.Dialog
import com.compose.kibumi.feature.presentation.util.*
import com.compose.kibumi.ui.theme.KibumiJetpackTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity()
{
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent()
        {
            KibumiJetpackTheme {
                val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
                val drawerGestureState = rememberSaveable { (mutableStateOf(false)) }
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                val scaffoldState = rememberScaffoldState()

                val selectedIndex = remember { mutableStateOf(0) }

                when(navBackStackEntry?.destination?.route)
                {
                    BottomNavigationScreen.Home.route -> drawerGestureState.value = true
                    BottomNavigationScreen.Market.route -> drawerGestureState.value = true
                    BottomNavigationScreen.Activity.route -> drawerGestureState.value = true
                    BottomNavigationScreen.Savings.route -> drawerGestureState.value = true
                    else -> drawerGestureState.value = false
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                    bottomBar =
                    {
                        if (bottomBarState.value)
                        {
                            BottomAppBar(
                                modifier = Modifier
                                    .height(60.dp)
                                    .fillMaxWidth(),
                                backgroundColor = Color.Transparent
                            ) {
                                BottomNav(navController = navController, bottomBarState, selectedIndex)
                            }
                        }
                    },
                    drawerContent =
                    {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItemModel("profile", "Profile", "", R.drawable.icon_profile),
                                MenuItemModel("subscription", "Subscription", "", R.drawable.icon_subscription),
                                MenuItemModel("change_password", "Change Password", "", R.drawable.icon_change_password),
                                MenuItemModel("help", "Help", "", R.drawable.icon_help),
                                MenuItemModel("logout", "Logout", "", R.drawable.icon_logout),
                            ),
                            onItemClick =
                            {
                                when(it.Id)
                                {
                                    "profile" ->
                                    {
                                        navController.navigate(Screen.Profile.route)
                                        closeDrawer(scope, scaffoldState)
                                    }
                                    "subscription" ->
                                    {
                                        navController.navigate(Screen.Address.route)
                                        closeDrawer(scope, scaffoldState)
                                    }
                                    "change_password" ->
                                    {
                                        navController.navigate(Screen.ChangePassword.route)
                                        closeDrawer(scope, scaffoldState)
                                    }
                                    "help" -> return@DrawerBody
                                    "logout" -> return@DrawerBody
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = drawerGestureState.value
                ) {
                    MainScreenNavigation(navController, bottomBarState, scaffoldState, selectedIndex)
                }
            }
        }
    }
}

private fun closeDrawer(scope: CoroutineScope, scaffoldState: ScaffoldState)
{
    scope.launch()
    {
        scaffoldState.drawerState.close()
    }
}