package com.compose.kibumi.feature.presentation.subscription

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.kibumi.feature.domain.model.SubscriptionPackageModel
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.R
import com.compose.kibumi.extension.convertToCurrency
import com.compose.kibumi.extension.convertToStringThousandSeparator
import com.compose.kibumi.feature.presentation.util.Screen
import com.compose.kibumi.ui.theme.*
import kotlinx.coroutines.launch

var totalPrice = 0.0

@Composable
fun DetailSubmissionScreen(navController: NavController)
{
    val subscription = rememberSaveable { mutableStateOf(1) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = LocalSpacing.current.BIG)
                .verticalScroll(rememberScrollState())
        ) {
            TopAppBarGeneral(navController = navController, "Detail Submission") { }
            ItemPackage(icon = R.drawable.icon_package, title = "Package", value = modelSubscriptionPackage.Title ?: "")
            ItemCounterSubscription { subscription.value = it }
            ItemPackage(icon = R.drawable.icon_monetization, title = "Payment method", value = "BRIVA")
            ItemPackage(icon = R.drawable.icon_voucher, title = "Voucher", value = "BUMIKITA")
            OrderDetails(subscription.value)
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.MEDIUM)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(backgroundColor = THEME_PRIMARY_NORMAL),
            onClick =
            {
                navController.navigate(Screen.Payment.route)
            })
        {
            Text(text = "Pay", color = Color.White)
        }
    }
}

@Composable
fun ItemPackage(icon: Int, title: String, value: String)
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

@Composable
fun ItemCounterSubscription(returnSubscription: (Int) -> Unit)
{
    val subscription = rememberSaveable { (mutableStateOf(1)) }

    Box(modifier = Modifier.height(100.dp))
    {
        Column(modifier = Modifier.padding(LocalSpacing.current.MEDIUM))
        {
            Text(text = "Subscription", fontSize = LocalFontSize.current.DEFAULT)
            Spacer(modifier = Modifier.height(LocalSpacing.current.LITTLE))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BorderedItem(
                    shape = RoundedCornerShape(topStart = LocalSpacing.current.TINY, bottomStart = LocalSpacing.current.TINY),
                    value = "-"
                ) {
                    if (subscription.value > 1)
                    {
                        subscription.value--
                        returnSubscription(subscription.value)
                    }
                }
                BorderedItem(
                    shape = RoundedCornerShape(0.dp),
                    value = subscription.value.toString()
                ) {

                }
                BorderedItem(
                    shape = RoundedCornerShape(topEnd = LocalSpacing.current.TINY, bottomEnd = LocalSpacing.current.TINY),
                    value = "+"
                ) {
                    if (subscription.value < 12)
                    {
                        subscription.value++
                        returnSubscription(subscription.value)
                    }
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.LITTLE))
                Text(text = "Month", fontSize = LocalFontSize.current.DEFAULT)
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

@Composable
fun BorderedItem(shape: RoundedCornerShape, value: String, onClick : () -> Unit )
{
    Card(
        modifier = Modifier
            .size(LocalSpacing.current.BIG)
            .clickable { onClick() },
        border = BorderStroke(1.dp, THEME_TERTIARY_DARK),
        shape = shape
    ) {
        Column(
            modifier = Modifier.padding(LocalSpacing.current.TINY),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = value)
        }
    }
}

@Composable
fun OrderDetails(subscription: Int)
{
    val discount = 20000.0
    val administration = 4000.0
    val discountAfterAdministration = discount - administration

    totalPrice = (modelSubscriptionPackage.Price?.times(subscription.toDouble()))?.minus(discountAfterAdministration) ?: 0.0

    Column(
        modifier = Modifier.padding(LocalSpacing.current.MEDIUM)
    ) {
        Text(text = "Order Details", fontSize = LocalFontSize.current.SMALL, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.TINY),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Price", fontSize = LocalFontSize.current.SMALL)
            Text(text = modelSubscriptionPackage.Price?.convertToStringThousandSeparator()?.convertToCurrency() ?: "", fontSize = LocalFontSize.current.SMALL)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.TINY),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Subscription", fontSize = LocalFontSize.current.SMALL)
            Text(text = "$subscription Month", fontSize = LocalFontSize.current.SMALL)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.TINY),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Discounts", fontSize = LocalFontSize.current.SMALL)
            Text(text = discount.convertToStringThousandSeparator().convertToCurrency(), fontSize = LocalFontSize.current.SMALL)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.TINY),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Administration", fontSize = LocalFontSize.current.SMALL)
            Text(text = administration.convertToStringThousandSeparator().convertToCurrency(), fontSize = LocalFontSize.current.SMALL)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            thickness = 1.dp,
            color = THEME_TERTIARY_LIGHT)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.TINY),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total", fontSize = LocalFontSize.current.SMALL, fontWeight = FontWeight.Bold)
            Text(text = totalPrice.convertToStringThousandSeparator().convertToCurrency() ?: "", fontSize = LocalFontSize.current.SMALL, fontWeight = FontWeight.Bold, color = THEME_PRIMARY_NORMAL)
        }
    }
}