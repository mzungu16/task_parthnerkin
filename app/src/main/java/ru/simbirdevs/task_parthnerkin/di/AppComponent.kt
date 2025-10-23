package ru.simbirdevs.task_parthnerkin.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.simbirdevs.task_parthnerkin.di.viewmodel.MultiViewModelFactory
import ru.simbirdevs.task_parthnerkin.di.viewmodel.ViewModelsBindingModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelsBindingModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }

    val viewModelFactory: MultiViewModelFactory

    companion object {
        fun init(context: Context): AppComponent {
            return DaggerAppComponent.factory().create(context)
        }
    }
}