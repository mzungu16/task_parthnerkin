package ru.simbirdevs.task_parthnerkin.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.simbirdevs.task_parthnerkin.utils.Dates
import java.time.YearMonth

@Composable
fun ConferenceDateSeparator(date: String) {
    Text(
        text = date,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ConferenceDateSeparatorPreview() {
    ConferenceDateSeparator(date = "ad")
}