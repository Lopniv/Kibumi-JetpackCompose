package com.compose.kibumi.feature.presentation.util

import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.compose.kibumi.R
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_PRIMARY_NORMAL
import com.compose.kibumi.ui.theme.THEME_TERTIARY_NORMAL_LIGHT
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

//region TAB LAYOUT - START

@OptIn(ExperimentalPagerApi::class)
@Composable fun TabLayout(tabItems: List<String>, screen: @Composable @UiComposable (index: Int) -> Unit)
{
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column()
    {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.White,
            modifier = Modifier
                .height(50.dp)
                .background(Color.Transparent),
            indicator =
            { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .width(LocalSpacing.current.DEFAULT)
                        .height(LocalSpacing.current.DEFAULT)
                )
            },
            divider =
            {
                Divider(color = THEME_TERTIARY_NORMAL_LIGHT)
            }
        ) {
            tabItems.forEachIndexed { index, title ->

                val color = remember()
                {
                    Animatable(Color.White)
                }

                Tab(
                    text =
                    {
                        Text(
                            title,
                            style = if (pagerState.currentPage == index) TextStyle(THEME_PRIMARY_NORMAL, fontSize = 14.sp) else TextStyle(THEME_TERTIARY_NORMAL_LIGHT, fontSize = 12.sp),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    selected = pagerState.currentPage == index,
                    modifier = Modifier.background(color = color.value),
                    onClick =
                    {
                        coroutineScope.launch()
                        {
                            pagerState.animateScrollToPage(index)
                        }
                    })
            }
        }

        HorizontalPager(
            count = tabItems.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.Top)
        { pageIndex ->
            screen(pageIndex)
        }
    }
}

//endregion


//region TOP APP BAR HOME - START

@Composable
fun TopAppBarHome(navController: NavController, color: Color, scaffoldState: ScaffoldState)
{
    val scope = rememberCoroutineScope()

    Box()
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color)
                .padding(top = LocalSpacing.current.MEDIUM, bottom = LocalSpacing.current.MEDIUM)
        )
        {
            Box(modifier = Modifier.padding(start = LocalSpacing.current.MEDIUM))
            {
                Image(
                    modifier = Modifier.clickable
                    {
                        scope.launch()
                        {
                            scaffoldState.drawerState.open()
                        }
                    },
                    painter = painterResource(id = R.drawable.icon_account),
                    contentDescription = "Profile")
            }

            Box(modifier = Modifier.padding(start = LocalSpacing.current.MEDIUM))
            {
                Text(
                    fontSize = LocalFontSize.current.LARGE,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    text = "Kibumi")
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(end = LocalSpacing.current.MEDIUM),
                horizontalArrangement = Arrangement.End
            )
            {
                Image(
                    modifier = Modifier.clickable()
                    {
                        navController.navigate(Screen.Notification.route)
                    },
                    painter = painterResource(id = R.drawable.icon_bell), contentDescription = "Profile")
            }
        }
    }
}

//endregion


@Composable
fun TopAppBarGeneral(
    navController: NavController,
    stringTitle: String,
    includedIconRight: Boolean = false,
    iconRight: Int? = null,
    iconClick: () -> Unit)
{
    val scope = rememberCoroutineScope()

    Box()
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(THEME_PRIMARY_NORMAL)
                .padding(top = LocalSpacing.current.MEDIUM, bottom = LocalSpacing.current.MEDIUM)
        )
        {
            Box(modifier = Modifier.padding(start = LocalSpacing.current.MEDIUM))
            {
                Image(
                    modifier = Modifier
                        .rotate(180F)
                        .clickable
                        {
                            scope.launch()
                            {
                                navController.popBackStack()
                            }
                        },
                    painter = painterResource(id = R.drawable.icon_arrow_right),
                    contentDescription = "back navigation")
            }

            Box(modifier = Modifier.padding(start = LocalSpacing.current.MEDIUM))
            {
                Text(
                    fontSize = LocalFontSize.current.LARGE,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    text = stringTitle)
            }

            if (includedIconRight)
            {
                Row(modifier = Modifier
                        .padding(end = LocalSpacing.current.MEDIUM)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End)
                {
                    Image(
                        modifier = Modifier
                            .clickable
                            {
                                scope.launch()
                                {
                                    iconClick()
                                }
                            },
                        painter = painterResource(id = iconRight ?: R.drawable.icon_null),
                        contentDescription = "icon right")
                }
            }
        }
    }
}