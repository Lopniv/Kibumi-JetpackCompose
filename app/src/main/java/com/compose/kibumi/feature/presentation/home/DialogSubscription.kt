package com.compose.kibumi.feature.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.compose.kibumi.R
import com.compose.kibumi.feature.presentation.util.Screen
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_SECONDARY_NORMAL
import com.compose.kibumi.ui.theme.THEME_TERTIARY_DARK

@Composable
fun DialogSubscription(setShowDialog: MutableState<Boolean>, navController: NavController)
{
    if (setShowDialog.value)
    {
        Dialog(onDismissRequest = { setShowDialog.value = false })
        {
            Surface(
                shape = RoundedCornerShape(LocalSpacing.current.MEDIUM),
                color = Color.White)
            {
                Box(contentAlignment = Alignment.Center)
                {
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Image(
                            modifier = Modifier.size(250.dp),
                            painter = painterResource(id = R.drawable.icon_subscribe),
                            contentDescription = "subscription")
                        Column(modifier = Modifier
                            .padding(top = LocalSpacing.current.MEDIUM, start = LocalSpacing.current.MEDIUM, end = LocalSpacing.current.MEDIUM)
                            .offset(0.dp, (-30).dp))
                        {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "You haven't subscribe yet",
                                fontSize = LocalFontSize.current.MEDIUM,
                                fontWeight = FontWeight.Bold)
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Choose a subscription package to be able to enjoy Kibumi services",
                                fontSize = LocalFontSize.current.SMALL)
                            Spacer(modifier = Modifier.padding(top = LocalSpacing.current.MEDIUM))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End)
                            {
                                Text(
                                    modifier = Modifier.clickable()
                                    {
                                        setShowDialog.value = false
                                    },
                                    text = "Back", color = THEME_TERTIARY_DARK)

                                Spacer(modifier = Modifier.padding(start = LocalSpacing.current.LITTLE))

                                Text(
                                    modifier = Modifier.clickable()
                                    {
                                        setShowDialog.value = false
                                        navController.navigate(Screen.ProductSubscription.route)
                                    },
                                    text = "Select Package",
                                    color = THEME_SECONDARY_NORMAL)
                            }
                        }
                    }
                }
            }
        }
    }
}