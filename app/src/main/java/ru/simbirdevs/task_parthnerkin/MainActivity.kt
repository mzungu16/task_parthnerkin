package ru.simbirdevs.task_parthnerkin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import ru.simbirdevs.task_parthnerkin.ui.components.MainScreen
import ru.simbirdevs.task_parthnerkin.ui.theme.TaskparthnerkinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskparthnerkinTheme {
                MainScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}