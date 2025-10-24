package ru.simbirdevs.task_parthnerkin.utils


import android.app.Application
import android.content.Context
import ru.simbirdevs.task_parthnerkin.di.AppComponent

class App: Application() {
    val appComponent: AppComponent by lazy { AppComponent.init(this) }
}

val Context.app:App get() = applicationContext as App
val Context.viewModelFactory get() = app.appComponent.viewModelFactory
