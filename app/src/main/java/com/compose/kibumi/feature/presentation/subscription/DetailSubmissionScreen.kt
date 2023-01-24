package com.compose.kibumi.feature.presentation.subscription

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.kibumi.feature.domain.model.SubscriptionPackageModel
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_TERTIARY_LIGHT
import com.compose.kibumi.R
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.THEME_TERTIARY_DARK

@Composable
fun DetailSubmissionScreen(navController: NavController, idProduct: Int)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var modelSubscriptionPackage = SubscriptionPackageModel()

        listPackage.forEach()
        {
            if (idProduct == it.ID)
            {
                modelSubscriptionPackage = it
            }
        }

        TopAppBarGeneral(navController = navController, "Detail Submission")
        ItemRow(icon = R.drawable.icon_package, title = "Package", value = "Jemputin Hebat 5x")
        ItemRow(icon = R.drawable.icon_monetization, title = "Payment method", value = "BRIVA")
        ItemRow(icon = R.drawable.icon_voucher, title = "Voucher", value = "BUMIKITA")
    }
}

@Composable
fun ItemRow(icon: Int, title: String, value: String)
{
    Box(modifier = Modifier.height(70.dp))
    {
        Row(
            modifier = Modifier.padding(LocalSpacing.current.MEDIUM),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Image(
                    modifier = Modifier
                        .size(35.dp)
                        .padding(end = LocalSpacing.current.LITTLE),
                    painter = painterResource(icon),
                    contentDescription = "icon")
                Text(text = title, fontSize = LocalFontSize.current.SMALL, fontWeight = FontWeight.Bold)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = value, fontSize = LocalFontSize.current.SMALL)
                Image(
                    painter = painterResource(id = R.drawable.icon_keyboard_right),
                    contentDescription = "arrow",
                    colorFilter = ColorFilter.tint(THEME_TERTIARY_DARK))
            }
        }
        Divider(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            thickness = 1.dp,
            color = THEME_TERTIARY_LIGHT)
    }
}