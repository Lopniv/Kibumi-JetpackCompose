package com.compose.kibumi.feature.presentation.market

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.compose.kibumi.feature.presentation.util.TopAppBarHome
import com.compose.kibumi.R
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_PRIMARY_NORMAL

@Composable
fun MarketScreen(
    navController: NavController,
    scaffoldState: ScaffoldState)
{
    Box(modifier = Modifier.fillMaxSize()) 
    {
        TopAppBarHome(navController = navController, THEME_PRIMARY_NORMAL, scaffoldState)
        ImageFeatureInProgress()
    }
}

@Composable
fun ImageFeatureInProgress()
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painter = painterResource(id = R.drawable.icon_feature_inprogress),
            contentDescription = "feature in progress")
        Text(
            text = "Feature Not Yet Available",
            fontSize = LocalFontSize.current.MEDIUM,
            fontWeight = FontWeight.Bold)
        Text(
            modifier = Modifier.padding(horizontal = LocalSpacing.current.MEDIUM),
            text = "This feature is still under development. Keep supporting Kibumi, okay?",
            fontSize = LocalFontSize.current.SMALL,
            textAlign = TextAlign.Center)
    }
}