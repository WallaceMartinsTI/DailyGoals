package com.wcsm.dailygoals.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.dailygoals.util.getTwoQuotes

@Composable
fun MotivationalQuotes() {

    val threeQuotes = getTwoQuotes(LocalContext.current)

    Column(
        Modifier.fillMaxWidth().padding(horizontal = 24.dp)
    ) {
        threeQuotes.forEach {
            Row(
                modifier = Modifier
                    //.border(1.dp, Color.Red)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        "\"${it.quote}\"",
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .padding(top = 8.dp),

                    )
                    Text(
                        "- ${it.author}",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MotivationalQuotesPreview() {
    MotivationalQuotes()
}
