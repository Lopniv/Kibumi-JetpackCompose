package com.compose.kibumi.feature.presentation.subscription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.kibumi.R
import com.compose.kibumi.extension.convertToCurrency
import com.compose.kibumi.extension.convertToStringThousandSeparator
import com.compose.kibumi.feature.domain.`object`.getListSubscriptionPackage
import com.compose.kibumi.feature.domain.model.SubscriptionPackageModel
import com.compose.kibumi.feature.presentation.util.Screen
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_TERTIARY_DARK
import com.compose.kibumi.ui.theme.THEME_TERTIARY_LIGHT

@Composable
fun SubscriptionPackageScreen(navController: NavController)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(THEME_TERTIARY_LIGHT)
    ) {
        TopAppBarGeneral(navController = navController, "Subscription") { }

        val listPackage = getListSubscriptionPackage()

        LazyColumn(
            contentPadding = PaddingValues(bottom = LocalSpacing.current.MEDIUM),
            modifier = Modifier.padding(LocalSpacing.current.LITTLE),
            content =
        {
            itemsIndexed(listPackage)
            {  _, model ->
                ItemPackageSubscription(modelSubscriptionPackage = model)
                {
                    navController.navigate(Screen.DetailProductSubscription.withArgs(model.ID.toString()))
                }
            }
        })
    }
}

@Composable
fun ItemPackageSubscription(modelSubscriptionPackage: SubscriptionPackageModel, onClick: () -> Unit )
{
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(LocalSpacing.current.LITTLE)
            .clickable { onClick() },
        shape = RoundedCornerShape(LocalSpacing.current.MEDIUM),
        backgroundColor = Color.White,
        elevation = LocalSpacing.current.LITTLE
    ) {
        Row(
            modifier = Modifier.padding(LocalSpacing.current.SMALL),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = LocalSpacing.current.LITTLE),
                painter = painterResource(id = modelSubscriptionPackage.Icon ?: R.drawable.icon_null),
                contentDescription = "icon package")
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = modelSubscriptionPackage.Title ?: "", fontSize = LocalFontSize.current.LARGE, fontWeight = FontWeight.Bold)
                Text(text = "${modelSubscriptionPackage.PickupDays} Pickup/week", fontSize = LocalFontSize.current.DEFAULT, color = THEME_TERTIARY_DARK)
                Text(text = "The price of trash is worth ${modelSubscriptionPackage.PricePerKilos}/kg", fontSize = LocalFontSize.current.DEFAULT, color = THEME_TERTIARY_DARK)
                Spacer(modifier = Modifier.height(LocalSpacing.current.LITTLE))
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Text(text = modelSubscriptionPackage.Price?.convertToStringThousandSeparator()?.convertToCurrency() ?: "", fontSize = LocalFontSize.current.MEDIUM, fontWeight = FontWeight.Bold)
                    Text(text = " /month", fontSize = LocalFontSize.current.DEFAULT, color = THEME_TERTIARY_DARK)
                }
            }
        }
    }
}