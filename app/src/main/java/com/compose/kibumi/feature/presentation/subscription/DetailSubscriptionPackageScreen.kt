package com.compose.kibumi.feature.presentation.subscription

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.kibumi.R
import com.compose.kibumi.extension.convertToCurrency
import com.compose.kibumi.extension.convertToStringThousandSeparator
import com.compose.kibumi.feature.domain.`object`.getListSubscriptionPackage
import com.compose.kibumi.feature.domain.model.SubscriptionPackageModel
import com.compose.kibumi.feature.presentation.util.Screen
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val listPackage = getListSubscriptionPackage()
var modelSubscriptionPackage = SubscriptionPackageModel()

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailSubscriptionPackageScreen(navController: NavController, idProduct: Int)
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
        ) {
            listPackage.forEach()
            {
                if (idProduct == it.ID)
                {
                    modelSubscriptionPackage = it
                }
            }

            TopAppBarGeneral(navController = navController, "Detail Subscription")
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Image(
                    modifier = Modifier
                        .heightIn(150.dp)
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.image_detail_package),
                    contentDescription = "image detail package",
                    contentScale = ContentScale.Crop)
                CardDetailPackage(modelSubscriptionPackage)
                InformationPackage(modelSubscriptionPackage)
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.MEDIUM),
                    colors = ButtonDefaults.buttonColors(backgroundColor = THEME_PRIMARY_NORMAL),
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
                    Text(text = "Next", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun CardDetailPackage(modelPackage: SubscriptionPackageModel)
{
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .padding(LocalSpacing.current.LITTLE)
                .offset(0.dp, (-60).dp),
            shape = RoundedCornerShape(LocalSpacing.current.SMALL),
            backgroundColor = THEME_PRIMARY_NORMAL
        ) {
            Column(
                modifier = Modifier.padding(vertical = LocalSpacing.current.MEDIUM, horizontal = LocalSpacing.current.BIG),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = modelPackage.Title ?: "",
                    fontSize = LocalFontSize.current.LARGE,
                    color = Color.White,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = "${modelSubscriptionPackage.Price?.convertToStringThousandSeparator()?.convertToCurrency() ?: ""}/Month",
                    fontSize = LocalFontSize.current.MEDIUM,
                    color = Color.White,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = "*PPH included",
                    fontSize = LocalFontSize.current.DEFAULT,
                    color = Color.White)
            }
        }
    }
}

@Composable
fun InformationPackage(modelSubscriptionPackage: SubscriptionPackageModel)
{
    Column(modifier = Modifier.offset(0.dp, (-70).dp))
    {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.LITTLE),
            text = "What did you get?",
            fontSize = LocalFontSize.current.MEDIUM,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)
        ItemList(intNumber = 1, stringText = "Pickup ${modelSubscriptionPackage.PickupDays}x/week (24x/month)")
        ItemList(intNumber = 2, stringText = "Free pick-up on demand (3x)")
        ItemList(intNumber = 3, stringText = "Two sets of trash bag category markers")
        ItemList(intNumber = 4, stringText = "Exclusive house marker sticker")

        Spacer(modifier = Modifier.padding(top = LocalSpacing.current.LITTLE))

        Divider(thickness = 1.dp, color = THEME_TERTIARY_NORMAL)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.LITTLE),
            text = "Terms and Conditions",
            fontSize = LocalFontSize.current.MEDIUM,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)
        ItemList(intNumber = 1, stringText = "The trash can schedule is determined by Kibumi and can be seen through the application")
        ItemList(intNumber = 2, stringText = "Responsible for the maintenance of segregated waste bins by users")
        ItemList(intNumber = 3, stringText = "Damage or loss of trash cans are borne personally with a fine of IDR 200,000 / pcs")
        ItemList(intNumber = 4, stringText = "Termination of subscription;\n" + "1) Package purchase funds cannot be replaced/refunded,\n" + "2) Minimum 1 month subscription,\n" + "3) Termination of subscription by the second party (user) shall be borne by the user and termination of subscription by the first party (Kibumi) shall be borne by Kibumi")
    }
}

@Composable
fun ItemList(intNumber: Int, stringText: String)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = LocalSpacing.current.MEDIUM, end = LocalSpacing.current.MEDIUM, bottom = LocalSpacing.current.TINY),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(THEME_TERTIARY_NORMAL),
            contentAlignment = Alignment.Center)
        {
            Text(text = "$intNumber", color = Color.White, fontSize = LocalFontSize.current.DEFAULT)
        }
        Text(modifier = Modifier.padding(start = LocalSpacing.current.LITTLE), text = stringText, fontSize = LocalFontSize.current.SMALL)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDetailSubscription(
    navController: NavController,
    modelSubscriptionPackage: SubscriptionPackageModel,
    sheetState: BottomSheetState,
    scope: CoroutineScope)
{
    val buttonState = rememberSaveable { (mutableStateOf(false)) }
    Column(
        modifier = Modifier.padding(LocalSpacing.current.MEDIUM)
    ) {
        Divider(modifier = Modifier
            .width(50.dp)
            .align(Alignment.CenterHorizontally), color = THEME_TERTIARY_LIGHT, thickness = 3.dp)
        Text(fontSize = LocalFontSize.current.MEDIUM, fontWeight = FontWeight.Bold, text = "Confirm")
        Text(fontSize = LocalFontSize.current.SMALL, text = "I hereby agree to the terms & conditions that apply.")
        Spacer(modifier = Modifier.height(LocalSpacing.current.SMALL))
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Checkbox(checked = buttonState.value, onCheckedChange =
            {
                buttonState.value = !buttonState.value
            })
            Text(fontSize = LocalFontSize.current.SMALL, text = "I Agree")
        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.SMALL))

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = THEME_PRIMARY_NORMAL),
            enabled = buttonState.value,
            onClick =
            {
                scope.launch()
                {
                    sheetState.collapse()
                    navController.navigate(Screen.DetailSubmission.route)
                }
            })
        {
            Text(text = "Confirm", color = Color.White)
        }
    }
}