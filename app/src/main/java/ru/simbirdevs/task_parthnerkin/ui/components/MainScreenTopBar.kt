package ru.simbirdevs.task_parthnerkin.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.simbirdevs.task_parthnerkin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(
    title: String,
    onBackPressed: () -> Unit,
    onHeadphonePressed: () -> Unit,
    showNavigationIcon: Boolean
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title, style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = stringResource(R.string.main_screen_topbar_arrow_desc),
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onHeadphonePressed) {
                Icon(
                    painter = painterResource(R.drawable.ic_headset),
                    contentDescription = stringResource(R.string.main_screen_topbar_headphones_desc)
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenTopBarPreview() {
    MainScreenTopBar(
        title = stringResource(R.string.main_screen_topbar_title),
        onBackPressed = {},
        onHeadphonePressed = {},
        showNavigationIcon = true
    )
}