package ru.simbirdevs.task_parthnerkin.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.simbirdevs.task_parthnerkin.utils.Dates
import kotlin.collections.last

@Composable
fun TextContainer(startDate: String, endDate: String, modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = startDate.split("-").last(), style = MaterialTheme.typography.titleLarge)
            Text(text = Dates.monthEnglish(startDate.split("-")[1]))
        }
        Text(text = "-", modifier = Modifier.padding(5.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = endDate.split("-").last(), style = MaterialTheme.typography.titleLarge)
            Text(text = Dates.monthEnglish(endDate.split("-")[1]))
        }
    }
}

