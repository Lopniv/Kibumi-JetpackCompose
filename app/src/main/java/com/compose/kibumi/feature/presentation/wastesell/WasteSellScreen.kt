package com.compose.kibumi.feature.presentation.wastesell

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.R
import com.compose.kibumi.extension.convertToCurrency
import com.compose.kibumi.extension.convertToStringThousandSeparator
import com.compose.kibumi.feature.domain.`object`.getListWasteItem
import com.compose.kibumi.feature.domain.model.WasteItemModel
import com.compose.kibumi.feature.presentation.activity.ItemActivity
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.THEME_TERTIARY_LIGHT

@Composable
fun WasteSellScreen(navController: NavController)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(THEME_TERTIARY_LIGHT)
    ) {
        TopAppBarGeneral(
            navController = navController,
            stringTitle = "Waste Sell",
            includedIconRight = true,
            iconRight = R.drawable.icon_history)
        {
            
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.SMALL)
        ) {
            LazyVerticalGrid(
                contentPadding = PaddingValues(bottom = LocalSpacing.current.MEDIUM),
                columns = GridCells.Fixed(2),
                content =
                {
                    itemsIndexed(getListWasteItem())
                    { _, item ->
                        ItemWasteSell(item)
                    }
                })

            Card(
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.BottomEnd)
                    .padding(LocalSpacing.current.TINY)
                    .clickable {  },
                shape = RoundedCornerShape(LocalSpacing.current.LITTLE),
                backgroundColor = Color.White,
                elevation = LocalSpacing.current.LITTLE
            ) {
                Column(
                    modifier = Modifier
                        .padding(LocalSpacing.current.LITTLE),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Image(
                        modifier = Modifier.size(LocalSpacing.current.ENORMOUS),
                        painter = painterResource(id = R.drawable.icon_cart),
                        contentDescription = "image waste sell",
                        contentScale = ContentScale.Crop)
                }
            }
        }
    }
}

@Composable
fun ItemWasteSell(wasteItemModel: WasteItemModel)
{
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(LocalSpacing.current.TINY)
            .clickable {  },
        shape = RoundedCornerShape(LocalSpacing.current.LITTLE),
        backgroundColor = Color.White,
        elevation = LocalSpacing.current.LITTLE
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        )
        {
            Image(
                modifier = Modifier.height(150.dp),
                painter = painterResource(id = wasteItemModel.Image ?: R.drawable.icon_null),
                contentDescription = "image waste sell",
                contentScale = ContentScale.Crop)
            Text(
                modifier = Modifier.padding(top = LocalSpacing.current.LITTLE, start = LocalSpacing.current.LITTLE, end = LocalSpacing.current.LITTLE),
                text = wasteItemModel.ItemName ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = LocalFontSize.current.DEFAULT)
            Text(
                modifier = Modifier.padding(bottom = LocalSpacing.current.LITTLE, start = LocalSpacing.current.LITTLE, end = LocalSpacing.current.LITTLE),
                text = (wasteItemModel.Price?.convertToStringThousandSeparator()?.convertToCurrency() ?: "") + "/kg",
                fontSize = LocalFontSize.current.TINY)
        }
    }
}