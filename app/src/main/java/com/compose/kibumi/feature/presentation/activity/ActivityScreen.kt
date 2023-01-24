package com.compose.kibumi.feature.presentation.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.kibumi.feature.presentation.util.TabLayout
import com.compose.kibumi.feature.presentation.util.TopAppBarHome
import com.compose.kibumi.R
import com.compose.kibumi.ui.theme.*

val tabItems = listOf("Today", "History")

@Composable
fun ActivityScreen(
    navController: NavController,
    scaffoldState: ScaffoldState)
{
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBarHome(navController = navController, THEME_PRIMARY_NORMAL, scaffoldState)
        TabLayout(tabItems = tabItems)
        { indexPage ->
            when(indexPage)
            {
                0 ->
                {
                    TodayActivity()
                }
                1 ->
                {
                    HistoryActivity()
                }
            }
        }
    }
}

@Composable
fun TodayActivity()
{
    Box(modifier = Modifier
        .fillMaxSize()
        .background(THEME_TERTIARY_LIGHT))
    {
        LazyColumn(
            content =
            {
                itemsIndexed(arrayListOf(1,2,3))
                { _, _ ->
                    ItemActivity()
                }
            })
    }
}

@Composable
fun HistoryActivity()
{
    Box(modifier = Modifier
        .fillMaxSize()
        .background(THEME_TERTIARY_LIGHT))
    {
        LazyColumn(
            content =
            {
                itemsIndexed(arrayListOf(1,2,3))
                { _, _ ->
                    ItemActivity()
                }
            })
    }
}

@Composable
fun ItemActivity()
{
    Column(modifier = Modifier.background(Color.White))
    {
        Row(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .padding(LocalSpacing.current.SMALL),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)
        {
            Image(
                modifier = Modifier.size(LocalSpacing.current.BIG),
                painter = painterResource(id = R.drawable.icon_item_pickup),
                contentDescription = "item_pickup")
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.7f),
                horizontalAlignment = Alignment.Start)
            {
                Text(text = "Scheduled Pickup", color = Color.Black, fontSize = LocalFontSize.current.MEDIUM)
                Text(text = "08.00 - 16.00", color = THEME_TERTIARY_NORMAL_LIGHT, fontSize = LocalFontSize.current.DEFAULT)
            }
            Text(text = "+50 Point", color = THEME_SECONDARY_NORMAL, fontSize = LocalFontSize.current.SMALL)
        }
        Divider(color = THEME_TERTIARY_LIGHT, thickness = 0.5.dp)
    }
}