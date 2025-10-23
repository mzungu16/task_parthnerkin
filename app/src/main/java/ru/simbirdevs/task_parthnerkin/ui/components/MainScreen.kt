package ru.simbirdevs.task_parthnerkin.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.simbirdevs.task_parthnerkin.R
import ru.simbirdevs.task_parthnerkin.ui.theme.TaskparthnerkinTheme
import ru.simbirdevs.task_parthnerkin.ui.viewmodel.MainScreenViewModel
import ru.simbirdevs.task_parthnerkin.utils.Dates

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {

    val conferenceState by mainScreenViewModel.conferences.collectAsState()

    Scaffold(topBar = {
        MainScreenTopBar(
            title = stringResource(id = R.string.main_screen_topbar_title),
            onBackPressed = { /*TODO*/ },
            onHeadphonePressed = { /*TODO*/ },
            showNavigationIcon = true
        )
    }
    ) { paddingValues ->
        LazyColumn(modifier = modifier.padding(paddingValues)) {
            items(items = conferenceState.conferenceList) { conference ->
                ConferenceDateSeparator(Dates.dateValidation(conference.startDate))
                ConferenceItem(conference = conference)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TaskparthnerkinTheme {
        MainScreen(Modifier.fillMaxSize())
    }
}