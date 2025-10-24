package ru.simbirdevs.task_parthnerkin.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import ru.simbirdevs.task_parthnerkin.ui.viewmodel.MainScreenViewModel
import kotlin.reflect.KClass

@Module
interface ViewModelsBindingModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    fun bindMainScreenViewModel(mainScreenViewModel: MainScreenViewModel): ViewModel
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)