# MainScreen ViewModel with Parameters using Dagger2

This project demonstrates how to implement a ViewModel with parameters using Dagger2 assisted
injection for the MainScreen component.

## Architecture Overview

The implementation uses Dagger2's assisted injection feature to create ViewModels with runtime
parameters. This approach is recommended when you need to pass dynamic values to your ViewModel that
aren't available at compile time.

## Key Components

### 1. MainScreenViewModel with Assisted Injection

```kotlin
class MainScreenViewModel @AssistedInject constructor(
    private val conferenceRepository: ConferenceRepository,
    @Assisted private val userId: String,
    @Assisted private val categoryFilter: String? = null,
    @Assisted private val showFavorites: Boolean = false
) : ViewModel()
```

**Parameters:**

- `userId`: Unique identifier for the current user
- `categoryFilter`: Optional filter to show only conferences matching a specific category
- `showFavorites`: Boolean flag to show only the user's favorite conferences

### 2. Assisted Factory Interface

```kotlin
@AssistedFactory
interface Factory {
    fun create(
        userId: String,
        categoryFilter: String? = null,
        showFavorites: Boolean = false
    ): MainScreenViewModel
}
```

### 3. ViewModelFactory Implementation

```kotlin
class MainScreenViewModelFactory(
    private val assistedFactory: MainScreenViewModel.Factory,
    private val userId: String,
    private val categoryFilter: String? = null,
    private val showFavorites: Boolean = false
) : ViewModelProvider.Factory
```

## Dagger2 Configuration

### 1. AppComponent

```kotlin
@Singleton
@Component(modules = [ViewModelsBindingModule::class])
interface AppComponent {
    val mainScreenViewModelFactory: MainScreenViewModel.Factory
    // ... other dependencies
}
```

### 2. ViewModelsBindingModule

The assisted factory is automatically provided by Dagger when you use `@AssistedInject` and
`@AssistedFactory`.

### 3. App Class Extensions

```kotlin
val Context.mainScreenViewModelFactory get() = app.appComponent.mainScreenViewModelFactory
```

## Usage Examples

### Basic Usage

```kotlin
@Composable
fun BasicMainScreen() {
    MainScreen(
        userId = "current_user_id"
    )
}
```

### Advanced Usage with All Parameters

```kotlin
@Composable
fun AdvancedMainScreen() {
    MainScreen(
        modifier = Modifier.fillMaxSize(),
        userId = "power_user_789",
        categoryFilter = "Technology",
        showFavorites = true
    )
}
```

### Manual ViewModel Creation (Advanced)

If you need to create the ViewModel manually:

```kotlin
@Composable
fun ManualViewModelCreation() {
    val context = LocalContext.current
    val viewModelFactory = MainScreenViewModelFactory(
        assistedFactory = context.mainScreenViewModelFactory,
        userId = "custom_user",
        categoryFilter = "Android",
        showFavorites = false
    )
    
    val viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory)
    // Use viewModel...
}
```

## ViewModel Features

### State Management

The ViewModel manages three main states:

```kotlin
val conferences: StateFlow<UiState> = _conferences.asStateFlow()
val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
```

### Filtering Logic

1. **Category Filtering**: Filters conferences based on category name
2. **Favorites Filtering**: Shows only favorite conferences (limited to first 3 as example)
3. **User-specific Data**: Conference names include user ID for personalization

### Available Methods

```kotlin
fun refreshConferences()  // Reload conference data
fun clearError()          // Clear current error message
```

## UI Integration

The MainScreen composable automatically handles:

- Loading states with CircularProgressIndicator
- Error states with error message display
- Success states with conference list display

```kotlin
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    userId: String = "default_user",
    categoryFilter: String? = null,
    showFavorites: Boolean = false
)
```

## Testing Different Configurations

Use the `MainScreenExamples` composable to test different parameter combinations:

1. **Default**: No filters applied
2. **Category Filtered**: Only shows specific category
3. **Favorites Only**: Shows user's favorites
4. **User Specific**: Combines user-specific data with filters

## Dependencies Required

Add these to your `build.gradle.kts`:

```kotlin
implementation("com.google.dagger:dagger:2.57.2")
kapt("com.google.dagger:dagger-compiler:2.57.2")
```

## Key Benefits

1. **Type Safety**: Compile-time checking of dependencies
2. **Flexibility**: Runtime parameters for dynamic behavior
3. **Testability**: Easy to mock dependencies and test different parameter combinations
4. **Performance**: Efficient dependency injection without reflection
5. **Scalability**: Easy to add new parameters or dependencies

## Migration Notes

If migrating from regular `@Inject` constructor:

1. Change `@Inject` to `@AssistedInject`
2. Add `@Assisted` annotations to runtime parameters
3. Create `@AssistedFactory` interface
4. Update Dagger component to expose the factory
5. Update Composable to use `MainScreenViewModelFactory`

This approach provides a clean, type-safe way to inject runtime parameters into ViewModels while
maintaining all the benefits of Dagger2's dependency injection system.