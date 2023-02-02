package com.compose.kibumi.feature.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.compose.kibumi.feature.presentation.util.ButtonBoxGeneral
import com.compose.kibumi.feature.presentation.util.TextFieldGeneralOutlined
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_PRIMARY_NORMAL

@Composable
fun ProfileScreen(navController: NavController)
{
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBarGeneral(navController = navController, stringTitle = "Profile")
            {

            }
            TextFieldGeneralOutlined("Abdullah Fahmi", "Name")
            TextFieldGeneralOutlined("bloginfo693@gmail.com", "Email")
            TextFieldGeneralOutlined("081296301234", "Phone")
        }
        ButtonBoxGeneral(Modifier.align(Alignment.BottomCenter), "Save") {}
    }
}