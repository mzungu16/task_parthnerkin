package ru.simbirdevs.task_parthnerkin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.simbirdevs.task_parthnerkin.R
import ru.simbirdevs.task_parthnerkin.data.ConferenceData
import ru.simbirdevs.task_parthnerkin.data.MockData

@Composable
fun ConferenceItem(
    conference: ConferenceData,
    onConferenceClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(2.dp))
            .clickable { onConferenceClick() }
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inversePrimary)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = conference.conferenceName,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.errorContainer)
                ) {
                    AsyncImage(
                        model = conference.image[0].imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .weight(1f)
                    )
                    TextContainer(
                        startDate = conference.startDate.split("-").last(),
                        endDate = conference.endDate.split("-").last(),
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.padding(start = 10.dp)) {
                conference.category.forEach { it ->
                    ElevatedButton(
                        onClick = {},
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(30.dp)
                    ) {
                        Text(text = it.categoryName, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Image(painterResource(R.drawable.ic_location), "Location")
                Text(
                    text = "${conference.city}, ${conference.country}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConferenceItemPreview() {
    ConferenceItem(conference = MockData.conferenceData)
}