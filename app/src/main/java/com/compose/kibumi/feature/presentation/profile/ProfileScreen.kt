package com.compose.kibumi.feature.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
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
            TextFieldProfile("Abdullah Fahmi", "Name")
            TextFieldProfile("bloginfo693@gmail.com", "Email")
            TextFieldProfile("081296301234", "Phone")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.TINY),
            colors = ButtonDefaults.buttonColors(backgroundColor = THEME_PRIMARY_NORMAL),
            onClick = {})
        {
            Text(text = "Save", color = Color.White)
        }
    }
}

@Composable
fun TextFieldProfile(value: String, label: String)
{
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.LITTLE)
            .background(Color.White),
        value = value,
        label = { Text(text = label) },
        onValueChange = {})
}