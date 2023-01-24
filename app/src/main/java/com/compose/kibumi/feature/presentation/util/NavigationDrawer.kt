package com.compose.kibumi.feature.presentation.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.kibumi.feature.domain.model.MenuItemModel
import com.compose.kibumi.ui.theme.LocalFontSize
import com.compose.kibumi.ui.theme.LocalSpacing

@Composable
fun DrawerHeader()
{
    Column(modifier = Modifier.padding(LocalSpacing.current.MEDIUM))
    {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Blue),
            contentAlignment = Alignment.Center)
        {
            Text(text = "A", color = Color.White)
        }

        Spacer(modifier = Modifier.padding(top = LocalSpacing.current.LITTLE))
        
        Text(text = "Abdullah Fahmi", fontSize = LocalFontSize.current.MEDIUM, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = "bloginfo693@gmail.com", fontSize = LocalFontSize.current.SMALL, color = Color.Black)
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItemModel>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItemModel) -> Unit
) {
    LazyColumn(modifier)
    {
        items(items)
        { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable { onItemClick(item) },
                verticalAlignment = Alignment.CenterVertically)
            {
                Image(
                    modifier = Modifier.padding(horizontal = LocalSpacing.current.SMALL),
                    painter = painterResource(id = item.Icon),
                    contentDescription = item.ContentDescription)
                Text(text = item.Title, style = itemTextStyle)
            }
        }
    }
}