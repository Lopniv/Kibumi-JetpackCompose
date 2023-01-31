package com.compose.kibumi.feature.presentation.address

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.kibumi.R
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_PRIMARY_NORMAL
import com.compose.kibumi.ui.theme.THEME_TERTIARY_LIGHT

@Composable
fun AddressScreen(navController: NavController)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBarGeneral(
            navController = navController,
            stringTitle = "Subscription",
            includedIconRight = true,
            iconRight = R.drawable.icon_history)
        {

        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.MEDIUM))
        ItemAddress("Address", "Cibubur-Jakarta Timur", R.drawable.icon_location, "Change")
        ItemAddress("Subscription", "Jemputin OKE 3x", R.drawable.icon_silver, "Extend")
    }
}

@Composable
fun ItemAddress(
    title: String,
    value: String,
    image: Int,
    textButton: String)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.LITTLE),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = LocalFontSize.current.MEDIUM)
        Image(
            modifier = Modifier.rotate(270F),
            painter = painterResource(id = R.drawable.icon_keyboard_right),
            contentDescription = "arrow",
            colorFilter = ColorFilter.tint(Color.Black))
    }
    Divider(Modifier.padding(horizontal = LocalSpacing.current.MEDIUM))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.MEDIUM)
            .background(THEME_TERTIARY_LIGHT),
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.LITTLE),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = value)
            Image(
                modifier = Modifier
                    .size(40.dp),
                painter = painterResource(id = image),
                contentDescription = "image")
        }
        Button(
            modifier = Modifier
                .wrapContentWidth()
                .padding(LocalSpacing.current.LITTLE),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(LocalSpacing.current.MEDIUM),
            onClick = {})
        {
            Text(text = textButton, color = THEME_PRIMARY_NORMAL)
        }
    }
    Divider(Modifier.padding(horizontal = LocalSpacing.current.MEDIUM))
}