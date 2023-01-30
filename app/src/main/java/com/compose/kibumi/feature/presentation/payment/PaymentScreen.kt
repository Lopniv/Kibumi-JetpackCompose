package com.compose.kibumi.feature.presentation.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.compose.kibumi.extension.convertToCurrency
import com.compose.kibumi.extension.convertToStringThousandSeparator
import com.compose.kibumi.feature.presentation.subscription.*
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.R
import com.compose.kibumi.feature.domain.model.SubscriptionPackageModel
import com.compose.kibumi.feature.presentation.home.subscriptionPayment
import com.compose.kibumi.feature.presentation.util.BottomNavigationScreen
import com.compose.kibumi.feature.presentation.util.Dialog
import com.compose.kibumi.feature.presentation.util.Screen
import com.compose.kibumi.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
            BottomSheetPayment(sheetState, scope)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val openDialog = remember { mutableStateOf(false) }

            if (openDialog.value)
            {
                Dialog(
                    setShowDialog = openDialog,
                    image = R.drawable.icon_payment_success,
                    title = "Payment Success!",
                    subtitle = "You have successfully made a payment for the garbage pick-up service",
                    positiveText = "Ok",
                    negativeText = "See Invoice",
                    positive =
                    {
                        openDialog.value = false
                    },
                    negative =
                    {
                        openDialog.value = false
                        subscriptionPayment = 1
                        navController.navigate(BottomNavigationScreen.Home.route ?: "")
                        {
                            popUpTo(0)
                        }
                    })
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(THEME_TERTIARY_LIGHT)
            ) {
                TopAppBarGeneral(navController = navController, "Payment") { }
                RowTotalPayment()
                RowImageBRIVA()
                RowVirtualAccountNumber()
                RowPaymentGuide()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(LocalSpacing.current.MEDIUM)
                    .align(Alignment.BottomCenter)
            ) {
                Button(
                    modifier = Modifier.weight(0.5f).padding(end = LocalSpacing.current.TINY),
                    colors = ButtonDefaults.buttonColors(backgroundColor = THEME_TERTIARY_WARNING),
                    onClick =
                    {
                        scope.launch()
                        {
                            if (sheetState.isCollapsed)
                            {
                                sheetState.expand()
                            }
                            else
                            {
                                sheetState.collapse()
                            }
                        }
                    })
                {
                    Text(text = "Cancel", color = Color.White)
                }
                Button(
                    modifier = Modifier.weight(0.5f).padding(start = LocalSpacing.current.TINY),
                    colors = ButtonDefaults.buttonColors(backgroundColor = THEME_PRIMARY_NORMAL),
                    onClick =
                    {
                        openDialog.value = true
                    })
                {
                    Text(text = "Confirm", color = Color.White)
                }
            }
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

@Composable
fun RowVirtualAccountNumber()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(LocalSpacing.current.MEDIUM)
    ) {
        Text(text = "Virtual Account", fontSize = LocalFontSize.current.DEFAULT)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "123 4567 8910 1112", fontSize = LocalFontSize.current.LARGE, color = THEME_PRIMARY_NORMAL, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(LocalSpacing.current.LITTLE))
            Image(painter = painterResource(id = R.drawable.icon_copy), contentDescription = "copy", colorFilter = ColorFilter.tint(THEME_SECONDARY_NORMAL))
            Text(text = "COPY", fontSize = LocalFontSize.current.DEFAULT, color = THEME_SECONDARY_NORMAL, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.LITTLE))
        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Amet pellentesque urna, nunc risus platea. Lorem integer aliquam sed aliquam tristique tellus.", fontSize = LocalFontSize.current.DEFAULT)
    }
    Divider(color = THEME_TERTIARY_LIGHT, thickness = LocalSpacing.current.NARROW)
}

@Composable
fun RowPaymentGuide()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
          modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.LITTLE),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(text = "Interbank transfer instruction")
          Image(painter = painterResource(id = R.drawable.icon_keyboard_right), contentDescription = "arrow", colorFilter = ColorFilter.tint(THEME_TERTIARY_DARK))
        }
        Divider(color = THEME_TERTIARY_LIGHT, thickness = LocalSpacing.current.THIN)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.LITTLE),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Bank transfer instruction")
            Image(painter = painterResource(id = R.drawable.icon_keyboard_right), contentDescription = "arrow", colorFilter = ColorFilter.tint(THEME_TERTIARY_DARK))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetPayment(
    sheetState: BottomSheetState,
    scope: CoroutineScope
)
{
    Column(modifier = Modifier.padding(LocalSpacing.current.MEDIUM))
    {
        Divider(modifier = Modifier
            .width(50.dp)
            .align(Alignment.CenterHorizontally), color = THEME_TERTIARY_LIGHT, thickness = 3.dp)
        Spacer(modifier = Modifier.height(LocalSpacing.current.SMALL))
        Text(fontSize = LocalFontSize.current.MEDIUM, fontWeight = FontWeight.Bold, text = "Are you sure you want to cancel?")
        Text(fontSize = LocalFontSize.current.SMALL, text = "Your VA code will be deleted from Purchase History")
        Spacer(modifier = Modifier.height(LocalSpacing.current.SMALL))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Button(
                modifier = Modifier.weight(0.5f).padding(end = LocalSpacing.current.TINY),
                colors = ButtonDefaults.buttonColors(backgroundColor = THEME_PRIMARY_NORMAL),
                onClick =
                {
                    scope.launch()
                    {
                        sheetState.collapse()
                    }
                })
            {
                Text(text = "Back", color = Color.White)
            }
            Button(
                modifier = Modifier.weight(0.5f).padding(start = LocalSpacing.current.TINY),
                colors = ButtonDefaults.buttonColors(backgroundColor = THEME_TERTIARY_WARNING),
                onClick =
                {
                    scope.launch()
                    {
                        sheetState.collapse()
                    }
                })
            {
                Text(text = "Cancel Payment", color = Color.White)
            }
        }
    }
}