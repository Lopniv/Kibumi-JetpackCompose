package com.compose.kibumi.feature.presentation.notification

import android.app.Notification
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.R
import com.compose.kibumi.feature.domain.model.NotificationModel
import com.compose.kibumi.feature.presentation.activity.ItemActivity
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.THEME_TERTIARY_LIGHT

@Composable
fun NotificationScreen(navController: NavController)
{
    Column(
        modifier = Modifier.fillMaxSize().background(color = THEME_TERTIARY_LIGHT)
    ) {
        TopAppBarGeneral(navController = navController, stringTitle = "Notification")
        {

        }
        val listNotification = arrayListOf(
            NotificationModel(
                Title = "Trash has been removed!",
                Subtitle = "Your trash has been transported by kibi buddy. Keep it clean!",
                Date = "12 August 2020"
            ),
            NotificationModel(
                Title = "Subscription status is active",
                Subtitle = "Laying of Kibumi sorting bins will be carried out in 2-3 days.",
                Date = "12 August 2020"
            ),
            NotificationModel(
                Title = "Yeay, Your address has been verified",
                Subtitle = "Now you can use kibumi services",
                Date = "12 August 2020"
            ),
            NotificationModel(
                Title = "Welcome to Kibumi!",
                Subtitle = "Hello, welcome and join Kibumi yaa..",
                Date = "12 August 2020"
            )
        )
        LazyColumn(
            modifier = Modifier.background(Color.White),
            content =
            {
                itemsIndexed(listNotification)
                { _, notificationModel ->
                    ItemNotification(notificationModel)
                }
            })
    }
}

@Composable
fun ItemNotification(
    notificationModel: NotificationModel
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 100.dp))
    {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth()
                .padding(
                    horizontal = LocalSpacing.current.MEDIUM,
                    vertical = LocalSpacing.current.LITTLE),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(LocalSpacing.current.BIG),
                painter = painterResource(id = R.drawable.icon_notification_item),
                contentDescription = "notification")
            Spacer(modifier = Modifier.width(LocalSpacing.current.LITTLE))
            Column()
            {
                Text(
                    text = notificationModel.Title,
                    fontSize = LocalFontSize.current.MEDIUM,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = notificationModel.Subtitle,
                    fontSize = LocalFontSize.current.SMALL)
                Text(
                    text = notificationModel.Date,
                    fontSize = LocalFontSize.current.DEFAULT)
            }
        }
        Divider(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),thickness = 1.dp, color = THEME_TERTIARY_LIGHT)
    }
}











