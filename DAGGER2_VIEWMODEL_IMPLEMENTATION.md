# MainScreenViewModel with Dagger2 and ConferenceRepository

This implementation demonstrates how to create a ViewModel using Dagger2 dependency injection with
ConferenceRepository as a parameter to show data from the repository.

## Architecture Overview

The implementation follows the MVVM pattern with Dagger2 for dependency injection:

```
MainActivity -> MainScreen -> MainScreenViewModel -> ConferenceRepository
```

## Key Components

### 1. ConferenceRepository (@Singleton)

```kotlin
@Singleton
class ConferenceRepository @Inject constructor() {
    
    suspend fun getAllConferences(): List<ConferenceData>
    suspend fun getConferencesByCategory(categoryName: String): List<ConferenceData>
    suspend fun getConferenceById(id: String): ConferenceData?
    suspend fun searchConferences(query: String): List<ConferenceData>
}
```

**Features:**

- `@Singleton` ensures single instance across the app
- `@Inject` constructor enables Dagger2 to create instances
- Provides methods for different data operations
- Simulates network delays for realistic behavior
- Returns mock conference data with proper formatting

### 2. MainScreenViewModel with Dependency Injection

```kotlin
class MainScreenViewModel @Inject constructor(
    private val conferenceRepository: ConferenceRepository
) : ViewModel() {
    
    val conferences: StateFlow<UiState>
    val isLoading: StateFlow<Boolean> 
    val errorMessage: StateFlow<String?>
}
```

**Features:**

- `@Inject` constructor with ConferenceRepository parameter
- Reactive state management with StateFlow
- Loading, error, and success states
- Multiple data loading methods (all, by category, search)
- Proper error handling with try-catch blocks

### 3. Dagger2 Configuration

#### ViewModelsBindingModule

```kotlin
@Module
interface ViewModelsBindingModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    fun bindMainScreenViewModel(mainScreenViewModel: MainScreenViewModel): ViewModel
}
```

#### AppComponent

```kotlin
@Singleton
@Component(modules = [ViewModelsBindingModule::class])
interface AppComponent {
    val viewModelFactory: MultiViewModelFactory
}
```

#### App Class with Extensions

```kotlin
class App: Application() {
    val appComponent: AppComponent by lazy { AppComponent.init(this) }
}

val Context.viewModelFactory get() = app.appComponent.viewModelFactory
```

### 4. MainScreen Composable

```kotlin
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = viewModel(
        factory = LocalContext.current.viewModelFactory
    )
)
```

**Features:**

- Automatic ViewModel injection via Dagger2
- Reactive UI updates based on ViewModel states
- Loading indicators during data fetch
- Error handling with user feedback
- Conference list display with existing components

## Data Flow

1. **App Startup**: Dagger2 creates AppComponent with all dependencies
2. **Screen Creation**: MainScreen composable requests MainScreenViewModel
3. **ViewModel Creation**: Dagger2 injects ConferenceRepository into ViewModel
4. **Data Loading**: ViewModel calls repository methods to fetch data
5. **State Updates**: Repository data updates UI via StateFlow
6. **UI Rendering**: Compose reacts to state changes and updates UI

## Available ViewModel Methods

```kotlin
// Public methods for data operations
fun loadConferences()                          // Load all conferences
fun loadConferencesByCategory(categoryName: String)  // Filter by category  
fun searchConferences(query: String)           // Search conferences
fun refreshConferences()                       // Reload data
fun clearError()                              // Clear error state
```

## State Management

The ViewModel manages three main states:

```kotlin
// Conference data state
val conferences: StateFlow<UiState> = _conferences.asStateFlow()

// Loading state for UI indicators
val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

// Error state for user feedback
val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
```

## UI States Handled

1. **Loading State**: Shows CircularProgressIndicator
2. **Error State**: Displays error message in red text
3. **Success State**: Shows conference list with existing components
4. **Empty State**: Handled by empty list display

## Example Usage Patterns

### Basic Usage

```kotlin
@Composable
fun MyScreen() {
    MainScreen(modifier = Modifier.fillMaxSize())
}
```

### With Custom ViewModel Access

```kotlin
@Composable
fun MyScreen() {
    val viewModel: MainScreenViewModel = viewModel(
        factory = LocalContext.current.viewModelFactory
    )
    
    // Use viewModel methods
    LaunchedEffect(Unit) {
        viewModel.loadConferencesByCategory("Technology")
    }
    
    MainScreen(mainScreenViewModel = viewModel)
}
```

### Repository Methods Demo

```kotlin
// In ViewModel or other component
viewModel.loadConferences()                    // Shows all conferences
viewModel.loadConferencesByCategory("Tech")    // Shows filtered conferences
viewModel.searchConferences("Android")         // Shows search results
viewModel.refreshConferences()                 // Reloads data
```

## Mock Data Structure

The repository provides realistic mock data:

```kotlin
ConferenceData(
    id = "1",
    conferenceName = "Conference 1", 
    conferenceFormat = "Format 1",
    conferenceStatusTitle = "Status 1",
    image = listOf(ImageData("https://picsum.photos/200/300?random=1")),
    startDate = "2025-01-11",
    endDate = "2025-01-12", 
    country = "Country 1",
    city = "City 1",
    category = listOf(Category("Category 1"))
)
```

## Benefits of This Implementation

1. **Dependency Injection**: Clean separation of concerns
2. **Testability**: Easy to mock ConferenceRepository for testing
3. **Scalability**: Easy to add new repository methods
4. **Type Safety**: Compile-time dependency validation
5. **Performance**: Singleton repository prevents multiple instances
6. **Reactive UI**: Automatic updates when data changes
7. **Error Handling**: Comprehensive error states and user feedback

## Testing

### Unit Testing ViewModel

```kotlin
@Test
fun `when loadConferences called then shows loading state`() {
    val mockRepository = mockk<ConferenceRepository>()
    val viewModel = MainScreenViewModel(mockRepository)
    
    // Test loading state, data flow, error handling
}
```

### Integration Testing

```kotlin
@Test 
fun `when repository returns data then UI shows conferences`() {
    // Test full data flow from repository to UI
}
```

This implementation provides a robust, scalable foundation for displaying conference data using
modern Android development practices with Dagger2, MVVM, and Jetpack Compose.