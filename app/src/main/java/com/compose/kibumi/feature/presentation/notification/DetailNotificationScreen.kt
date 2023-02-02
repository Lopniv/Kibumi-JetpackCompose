package com.compose.kibumi.feature.presentation.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.kibumi.R
import com.compose.kibumi.feature.domain.model.NotificationModel
import com.compose.kibumi.feature.presentation.util.TopAppBarGeneral
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_TERTIARY_LIGHT

var modelNotification = NotificationModel()

@Composable
fun DetailNotificationScreen(navController: NavController, idNotification: Int)
{
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBarGeneral(navController = navController, stringTitle = "Notification")
        {

        }

        listNotificationModel.forEach()
        {
            if (idNotification == it.ID)
            {
                modelNotification = it
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp))
        {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth()
                    .padding(
                        horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.LITTLE
                    ),
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
                        text = modelNotification.Title ?: "",
                        fontSize = LocalFontSize.current.MEDIUM,
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(LocalSpacing.current.LITTLE))
                    Text(
                        text = modelNotification.Date ?: "",
                        fontSize = LocalFontSize.current.DEFAULT)
                }
            }
            Divider(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),thickness = 5.dp, color = THEME_TERTIARY_LIGHT)
        }

        Column(modifier = Modifier.padding(LocalSpacing.current.MEDIUM))
        {
            Text(text = "Detail", fontWeight = FontWeight.Bold, fontSize = LocalFontSize.current.MEDIUM)
            Spacer(modifier = Modifier.height(LocalSpacing.current.SMALL))
            Text(text = modelNotification.Subtitle ?: "", fontSize = LocalFontSize.current.SMALL)
        }
    }
}