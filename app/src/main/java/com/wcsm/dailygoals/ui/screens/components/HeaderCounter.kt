package com.wcsm.dailygoals.ui.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderCounter(
    sequenceOfDays: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .padding(16.dp)
            .border(
                2.dp,
                Color(0xFFFFD700),
                RoundedCornerShape(8.dp)
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(60.dp)
            )
            Text(
                "$sequenceOfDays",
                color = Color.Blue,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
            )
        }
        Text("of ", fontSize = 24.sp)
        Text(
            "30",
            color = Color.Blue,
            fontFamily = FontFamily.Serif,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderCounterPreview() {
    HeaderCounter(25)
}