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

@Composable
fun TextContainer(startDate: String, endDate: String, modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = startDate , style = MaterialTheme.typography.titleLarge )
            Text(text = "JUL")
        }
        Text(text = "-", modifier = Modifier.padding(5.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = endDate, style = MaterialTheme.typography.titleLarge)
            Text(text = "JUL")
        }
    }
}

