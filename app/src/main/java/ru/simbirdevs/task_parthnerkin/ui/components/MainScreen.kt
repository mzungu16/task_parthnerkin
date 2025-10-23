package ru.simbirdevs.task_parthnerkin.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.simbirdevs.task_parthnerkin.R
import ru.simbirdevs.task_parthnerkin.ui.theme.TaskparthnerkinTheme
import ru.simbirdevs.task_parthnerkin.ui.viewmodel.MainScreenViewModel
import ru.simbirdevs.task_parthnerkin.utils.Dates
import ru.simbirdevs.task_parthnerkin.utils.viewModelFactory

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = viewModel(
        factory = LocalContext.current.viewModelFactory
    )
) {

    val conferenceList by mainScreenViewModel.conferences.collectAsState()
    val isLoading by mainScreenViewModel.isLoading.collectAsState()
    val errorMessage by mainScreenViewModel.errorMessage.collectAsState()

    Scaffold(topBar = {
        MainScreenTopBar(
            title = stringResource(id = R.string.main_screen_topbar_title),
            onBackPressed = { /*TODO*/ },
            onHeadphonePressed = { /*TODO*/ },
            showNavigationIcon = true
        )
    }
    ) { paddingValues ->
        Box(modifier = modifier.padding(paddingValues)) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "Unknown error occurred",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                else -> {
                    LazyColumn {
                        items(items = conferenceList) { conference ->
                            ConferenceDateSeparator(Dates.dateValidation(conference.startDate))
                            ConferenceItem(conference = conference)
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
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