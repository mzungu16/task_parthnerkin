package ru.simbirdevs.task_parthnerkin.ui.components

import android.util.Log
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
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
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val groupedList by mainScreenViewModel.groupedList.collectAsState()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, topBar = {
        MainScreenTopBar(
            title = stringResource(id = R.string.main_screen_topbar_title),
            onBackPressed = {
                coroutineScope.launch { snackbarHostState.showSnackbar("Arrow back clicked") }
            },
            onHeadphonePressed = {
                coroutineScope.launch { snackbarHostState.showSnackbar("Headphones icon clicked") }
            },
            showNavigationIcon = true
        )
    }
    ) { paddingValues ->
        LazyColumn(modifier = modifier.padding(paddingValues)) {

            groupedList.forEach { (date, conferenceList) ->
                item {
                    ConferenceDateSeparator(Dates.month(date))
                }
                items(conferenceList) { conference ->
                    ConferenceItem(
                        conference = conference.conference,
                        onConferenceClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Card clicked")
                            }
                        })
                    Spacer(modifier = Modifier.height(15.dp))
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