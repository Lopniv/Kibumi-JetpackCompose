package com.compose.kibumi.feature.presentation.savings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.compose.kibumi.feature.presentation.activity.ItemActivity
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral

@Composable
fun AllSavingsScreen(navController: NavController)
{
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBarGeneral(navController = navController, stringTitle = "History Coins")
        {

        }
        AllItemSavingsScreen()
    }
}

@Composable
fun AllItemSavingsScreen()
{
    Column(modifier = Modifier.fillMaxSize())
    {
        LazyColumn(
            content =
            {
                itemsIndexed(arrayListOf(0,1,2,3,4,5,6,7))
                { _, _ ->
                    ItemActivity()
                }
            })
    }
}