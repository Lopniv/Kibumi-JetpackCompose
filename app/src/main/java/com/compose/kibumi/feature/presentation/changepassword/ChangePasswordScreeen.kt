package com.compose.kibumi.feature.presentation.changepassword

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.compose.kibumi.feature.presentation.util.ButtonBoxGeneral
import com.compose.kibumi.feature.presentation.util.TextFieldGeneralOutlined
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_SECONDARY_NORMAL_LIGHT

@Composable
fun ChangePasswordScreen(navController: NavController)
{
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBarGeneral(navController = navController, stringTitle = "Change Password")
            {

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(LocalSpacing.current.MEDIUM),
                shape = RoundedCornerShape(LocalSpacing.current.LITTLE),
                backgroundColor = THEME_SECONDARY_NORMAL_LIGHT
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.SMALL))
                {
                    Text(text = "Enter email to change password", fontWeight = FontWeight.Bold, fontSize = LocalFontSize.current.SMALL)
                    Spacer(modifier = Modifier.height(LocalSpacing.current.LITTLE))
                    Text(text = "We will send a link to your email to change your password", fontSize = LocalFontSize.current.DEFAULT)
                }
            }
            TextFieldGeneralOutlined("bloginfo693@gmail.com", "Email")
        }
        ButtonBoxGeneral(Modifier.align(Alignment.BottomCenter), "Send") {}
    }
}