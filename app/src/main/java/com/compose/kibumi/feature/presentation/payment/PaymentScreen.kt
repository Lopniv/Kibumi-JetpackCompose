package com.compose.kibumi.feature.presentation.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.kibumi.extension.convertToCurrency
import com.compose.kibumi.extension.convertToStringThousandSeparator
import com.compose.kibumi.feature.presentation.subscription.*
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_PRIMARY_NORMAL
import com.compose.kibumi.ui.theme.THEME_TERTIARY_LIGHT
import com.compose.kibumi.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaymentScreen(navController: NavController)
{
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetElevation = LocalSpacing.current.SMALL,
        sheetContent =
        {
            BottomSheetDetailSubscription(navController, modelSubscriptionPackage, sheetState, scope)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(THEME_TERTIARY_LIGHT)
        ) {
            TopAppBarGeneral(navController = navController, "Payment")
            RowTotalPayment()
            RowImageBRIVA()
        }
    }
}

@Composable
fun RowTotalPayment()
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.LITTLE),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Total Payment", fontSize = LocalFontSize.current.SMALL, fontWeight = FontWeight.Bold)
        Text(text = totalPrice.convertToStringThousandSeparator().convertToCurrency() ?: "", fontSize = LocalFontSize.current.MEDIUM, fontWeight = FontWeight.Bold, color = THEME_PRIMARY_NORMAL)
    }
    Divider(color = THEME_TERTIARY_LIGHT, thickness = LocalSpacing.current.NARROW)
}

@Composable
fun RowImageBRIVA()
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.LITTLE),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .width(70.dp)
                .wrapContentHeight(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.icon_briva),
            contentDescription = "briva")
        Spacer(modifier = Modifier.width(LocalSpacing.current.LITTLE))
        Text(text = "BRI Virtual Account", fontSize = LocalFontSize.current.SMALL)
    }
    Divider(color = THEME_TERTIARY_LIGHT, thickness = LocalSpacing.current.THIN)
}