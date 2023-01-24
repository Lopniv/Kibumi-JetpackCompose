package com.compose.kibumi.feature.presentation.savings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.compose.kibumi.feature.presentation.activity.ItemActivity
import com.compose.kibumi.feature.presentation.home.ImageBackground
import com.compose.kibumi.feature.presentation.util.TopAppBarHome
import com.compose.kibumi.ui.theme.*

@Composable
fun SavingsScreen(
    navController: NavController,
    scaffoldState: ScaffoldState)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(THEME_PRIMARY_NORMAL)
    ) {
        TopAppBarHome(navController = navController, Color.Transparent, scaffoldState)
        ImageBackground()
        SavingsItem()
        CoinLabel()
    }
}

@Composable
fun SavingsItem()
{
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(top = 250.dp))
    {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = LocalSpacing.current.MEDIUM, topEnd = LocalSpacing.current.MEDIUM),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize())
            {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.1f)
                        .fillMaxWidth()
                        .padding(start = LocalSpacing.current.MEDIUM),
                    verticalArrangement = Arrangement.Center)
                {
                    Text(text = "Coin Earnings History", fontWeight = FontWeight.Bold)
                }
                Column(modifier = Modifier
                    .fillMaxHeight(0.7f))
                {
                    LazyColumn(
                        content =
                        {
                            itemsIndexed(arrayListOf(1,2,3,4,5))
                            { _, _ ->
                                ItemActivity()
                            }
                        })
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.3f)
                        .fillMaxWidth()
                        .padding(start = LocalSpacing.current.MEDIUM, end = LocalSpacing.current.MEDIUM, top = LocalSpacing.current.LITTLE),
                    verticalArrangement = Arrangement.Center)
                {
                    Card(
                        modifier = Modifier
                            .fillMaxSize(),
                        shape = RoundedCornerShape(LocalSpacing.current.MEDIUM),
                        backgroundColor = Color.White,
                        elevation = LocalSpacing.current.THIN
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center)
                        {
                            Text(text = "All Coin Earnings History", color = Color.Black, fontSize = LocalFontSize.current.DEFAULT)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CoinLabel()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Your Total Bumi Coins", fontSize = 15.sp, color = Color.White)
        Spacer(modifier = Modifier.height(LocalSpacing.current.TINY))
        Text(text = "150", fontSize = 80.sp, color = Color.White, fontWeight = FontWeight.Bold)
        Text(text = "Coin", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
    }
}