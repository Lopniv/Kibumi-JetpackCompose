package com.compose.kibumi.feature.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.compose.kibumi.R
import com.compose.kibumi.feature.presentation.util.TopAppBarHome
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_PRIMARY_NORMAL
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

private val listBanner: MutableList<Int> = arrayListOf(R.drawable.image_banner, R.drawable.image_banner)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    openDialog: MutableState<Boolean>)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(THEME_PRIMARY_NORMAL)
    ) {
        TopAppBarHome(navController = navController, Color.Transparent, scaffoldState)
        ImageBackground()
        MainHomeItem(openDialog)
        BannerAutoSliding(list = listBanner)
    }
}

@Composable
fun ImageBackground()
{
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = LocalSpacing.current.LITTLE),
        horizontalArrangement = Arrangement.End)
    {
        Image(
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .alpha(0.1f)
                .offset(40.dp, 0.dp),
            painter = painterResource(id = R.drawable.logo_kibumi_white),
            contentDescription = "logo")
    }
}

@ExperimentalPagerApi
@Composable
fun BannerAutoSliding(list: MutableList<Int>)
{
    val pagerState = rememberPagerState(list.size)

    Column(modifier = Modifier.padding(top = 80.dp))
    {
        HorizontalPager(
            state = pagerState,
            count = list.size)
        { page ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.LITTLE)
                    .wrapContentHeight()
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    },
                shape = RoundedCornerShape(LocalSpacing.current.LITTLE),
                backgroundColor = Color.Green
            ) {
                val banner = list[page]
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth())
                {
                    Image(
                        modifier = Modifier
                            .heightIn(180.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillBounds,
                        painter = rememberImagePainter(banner),
                        contentDescription = null)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalSpacing.current.MEDIUM))
        {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun MainHomeItem(openDialog: MutableState<Boolean>)
{
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(top = 180.dp))
    {
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = LocalSpacing.current.MEDIUM, topEnd = LocalSpacing.current.MEDIUM),
            backgroundColor = Color.White
        ) {
            Column(modifier = Modifier.padding(top = 110.dp, bottom = 60.dp))
            {
                SavingsLabel()
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Hey heroes, choose your service",
                    textAlign = TextAlign.Center,
                    color = THEME_PRIMARY_NORMAL)
                Row(
                    modifier = Modifier
                        .padding(LocalSpacing.current.MEDIUM)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CardService(modifier = Modifier.fillMaxWidth(0.5f), title = "Pickup", drawableIcon = R.drawable.icon_pickup)
                    {
                        openDialog.value = true
                    }

                    CardService(modifier = Modifier.fillMaxWidth(), title = "Waste Sell", drawableIcon = R.drawable.icon_selling_trash)
                    {

                    }
                }
            }
        }
    }
}

@Composable
fun SavingsLabel()
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.LITTLE)
            .heightIn(50.dp),
        shape = RoundedCornerShape(LocalSpacing.current.LITTLE),
        backgroundColor = THEME_PRIMARY_NORMAL
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth())
        {
            Image(
                modifier = Modifier.height(50.dp),
                painter = painterResource(id = R.drawable.icon_piggy_bank),
                contentDescription = "piggy bank")
            Column(
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = LocalSpacing.current.SMALL),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Celengan",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = LocalSpacing.current.SMALL),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "150 Koin",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Image(painter = painterResource(id = R.drawable.icon_arrow_right), contentDescription = "arrow")
            }
        }
    }
}

@Composable
fun CardService(modifier: Modifier, title: String, drawableIcon: Int, onClick: () -> Unit)
{
    Card(
        modifier = modifier
            .height(200.dp)
            .padding(LocalSpacing.current.LITTLE)
            .clickable { onClick() },
        shape = RoundedCornerShape(LocalSpacing.current.MEDIUM),
        backgroundColor = Color.White,
        elevation = LocalSpacing.current.LITTLE
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Image(painter = painterResource(id = drawableIcon), contentDescription = "pickup")
            Text(text = title, textAlign = TextAlign.Center)
        }
    }
}