package com.compose.kibumi.feature.presentation.pickup

import android.os.CountDownTimer
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.compose.kibumi.R
import com.compose.kibumi.feature.presentation.home.subscriptionPayment
import com.compose.kibumi.feature.presentation.util.BottomNavigationScreen
import com.compose.kibumi.feature.presentation.util.Screen
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.LocalSpacing
import com.compose.kibumi.ui.theme.THEME_PRIMARY_NORMAL
import java.util.*
import java.util.concurrent.TimeUnit

private var countDownTimer: CountDownTimer? = null

@Composable
fun SchedulePickupScreen(navController: NavController)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(THEME_PRIMARY_NORMAL)
    ) {
        ImageBackground()
        CountDown(navController)
    }
}

@Composable
fun ImageBackground()
{
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = LocalSpacing.current.LITTLE),
        horizontalArrangement = Arrangement.Start)
    {
        Image(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .alpha(0.1f)
                .offset((-40).dp, 0.dp),
            painter = painterResource(id = R.drawable.logo_kibumi_white),
            contentDescription = "logo")
    }
}

@Composable
fun CountDown(navController: NavController)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val millisUntilFinished = remember { mutableStateOf(0L) }

        countDownTimer()
        {
            millisUntilFinished.value = it
        }

        var timeUntilFinished = millisUntilFinished.value
        val days = TimeUnit.MILLISECONDS.toDays(timeUntilFinished)
        timeUntilFinished -= TimeUnit.DAYS.toMillis(days)
        val hours = TimeUnit.MILLISECONDS.toHours(timeUntilFinished)
        timeUntilFinished -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeUntilFinished)
        val textDays = if (days < 10) "0$days" else "$days"
        val textHours = if (hours < 10) "0$hours" else "$hours"
        val textMinutes = if (minutes < 10) "0$minutes" else "$minutes"

        Text(text = textDays, fontSize = 80.sp, fontWeight = FontWeight.Bold, color = Color.White, lineHeight = 0.sp)
        Text(text = "days", fontSize = LocalFontSize.current.MEDIUM, fontWeight = FontWeight.Bold, color = Color.White, lineHeight = 0.sp)
        Text(text = textHours, fontSize = 80.sp, fontWeight = FontWeight.Bold, color = Color.White, lineHeight = 0.sp)
        Text(text = "hours", fontSize = LocalFontSize.current.MEDIUM, fontWeight = FontWeight.Bold, color = Color.White, lineHeight = 0.sp)
        Text(text = textMinutes, fontSize = 80.sp, fontWeight = FontWeight.Bold, color = Color.White, lineHeight = 0.sp)
        Text(text = "minutes", fontSize = LocalFontSize.current.MEDIUM, fontWeight = FontWeight.Bold, color = Color.White, lineHeight = 0.sp)

        Spacer(modifier = Modifier.height(LocalSpacing.current.SMALL))

        Text(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(LocalSpacing.current.MEDIUM),
            textAlign = TextAlign.Center,
            text = "Next pickup: 2023-02-04",
            fontSize = LocalFontSize.current.SMALL,
            color = Color.Black)
        RowItem(R.drawable.icon_schedule_home, "Address", "Jln. Villa Inti Persada Blok B1 No. 6, RT/RW 001/002, Bambu Apus, Pamulang Timur, Tangerang Selatan, Provinsi Banten")
        Divider(thickness = LocalSpacing.current.TINY, color = Color.White)
        RowItem(R.drawable.icon_schedule_date, "Pickup Schedule", "Monday, Wednesday, Friday")
        Divider(thickness = LocalSpacing.current.TINY, color = Color.White)
        RowItem(R.drawable.icon_schedule_time, "Pickup Time", "09.00-15.00")
        Divider(thickness = LocalSpacing.current.TINY, color = Color.White)
        RowItem(R.drawable.icon_schedule_transport, "Pickup Type", "Pickup Scheduled")
        Divider(thickness = LocalSpacing.current.TINY, color = Color.White)
        RowItem(R.drawable.icon_schedule_assignment, "Pickup Quota", "8 pickups left")
        //This button it additional for back to flow subscription
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = LocalSpacing.current.MEDIUM, vertical = LocalSpacing.current.TINY),
            colors = ButtonDefaults.buttonColors(backgroundColor = THEME_PRIMARY_NORMAL),
            onClick =
            {
                navController.navigate(BottomNavigationScreen.Home.route ?: "")
                {
                    popUpTo(0)
                }
                subscriptionPayment = 0
            })
        {
            Text(text = "Cancel Subscription", color = Color.White)
        }
    }
}

@Composable
fun RowItem(
    icon: Int,
    titleText: String,
    subtitleText: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White))
    {
        Divider(thickness = 2.dp)
        Row(
            modifier = Modifier
                .padding(LocalSpacing.current.MEDIUM)
                .fillMaxWidth()
        ) {
            Image(painter = painterResource(id = icon), contentDescription = "icon")
            Spacer(modifier = Modifier.width(LocalSpacing.current.LITTLE))
            Column()
            {
                Text(text = titleText, fontSize = LocalFontSize.current.MEDIUM, fontWeight = FontWeight.Bold)
                Text(text = subtitleText, fontSize = LocalFontSize.current.SMALL)
            }
        }
        Divider(thickness = 2.dp)
    }
}

fun countDownTimer(tick: (Long) -> Unit)
{
    val startCalendar = Calendar.getInstance()
    val endCalendar = Calendar.getInstance()
    val month = 2 - 1
    endCalendar[2023, month, 5, 9, 0] = 0
    val startMillis = startCalendar.timeInMillis
    val endMillis = endCalendar.timeInMillis
    val totalMillis = endMillis - startMillis
    countDownTimer = object : CountDownTimer(totalMillis, 1000)
    {
        override fun onTick(millisUntilFinished: Long)
        {
            tick(millisUntilFinished)
        }
        
        override fun onFinish() {}
    }
    countDownTimer?.start()
}