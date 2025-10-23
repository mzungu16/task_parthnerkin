# TaskPartnerKin - Conference App with Dagger2

A modern Android application demonstrating MVVM architecture with Dagger2 dependency injection,
showcasing how to create ViewModels with repository parameters to display conference data.

## 🏗️ Architecture

The app follows MVVM architecture pattern with:

- **View**: Jetpack Compose UI components
- **ViewModel**: Business logic and state management
- **Repository**: Data layer with mock conference data
- **Dependency Injection**: Dagger2 for clean architecture

## 📱 Features

- **Conference List Display**: Shows conferences with dates, locations, and details
- **Loading States**: Visual indicators during data loading
- **Error Handling**: User-friendly error messages
- **Data Filtering**: Filter conferences by category
- **Search Functionality**: Search conferences by name, city, or country
- **Refresh Capability**: Pull-to-refresh functionality

## 🔧 Implementation Details

### MainScreenViewModel with Dagger2

```kotlin
class MainScreenViewModel @Inject constructor(
    private val conferenceRepository: ConferenceRepository
) : ViewModel() {
    
    val conferences: StateFlow<UiState>
    val isLoading: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    
    fun loadConferences()
    fun loadConferencesByCategory(categoryName: String)
    fun searchConferences(query: String)
    fun refreshConferences()
}
```

### ConferenceRepository

```kotlin
@Singleton
class ConferenceRepository @Inject constructor() {
    
    suspend fun getAllConferences(): List<ConferenceData>
    suspend fun getConferencesByCategory(categoryName: String): List<ConferenceData>
    suspend fun getConferenceById(id: String): ConferenceData?
    suspend fun searchConferences(query: String): List<ConferenceData>
}
```

### Dagger2 Configuration

- **AppComponent**: Main DI component with ViewModelFactory
- **ViewModelsBindingModule**: Binds ViewModels for injection
- **MultiViewModelFactory**: Factory for creating ViewModels with parameters

## 🚀 Usage

### Basic Usage

```kotlin
@Composable
fun MyApp() {
    MainScreen(modifier = Modifier.fillMaxSize())
}
```

### Advanced Usage with ViewModel Access

```kotlin
@Composable
fun MyScreen() {
    val viewModel: MainScreenViewModel = viewModel(
        factory = LocalContext.current.viewModelFactory
    )
    
    // Load specific category
    LaunchedEffect(Unit) {
        viewModel.loadConferencesByCategory("Technology")
    }
    
    MainScreen(mainScreenViewModel = viewModel)
}
```

## 📦 Key Dependencies

```kotlin
// Dagger2
implementation("com.google.dagger:dagger:2.57.2")
kapt("com.google.dagger:dagger-compiler:2.57.2")

// Jetpack Compose
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
implementation("androidx.compose.material3:material3")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android")
```

## 🔄 Data Flow

1. **App Launch**: Dagger2 creates AppComponent with dependencies
2. **Screen Creation**: MainScreen requests MainScreenViewModel
3. **ViewModel Injection**: Dagger2 injects ConferenceRepository into ViewModel
4. **Data Loading**: ViewModel calls repository methods
5. **State Updates**: Repository data flows to UI via StateFlow
6. **UI Updates**: Compose reacts to state changes

## 📊 State Management

The ViewModel manages three reactive states:

- **`conferences`**: Conference data with success/empty states
- **`isLoading`**: Loading state for progress indicators
- **`errorMessage`**: Error state for user feedback

## 🎨 UI States

- **Loading**: Shows CircularProgressIndicator
- **Success**: Displays conference list with existing components
- **Error**: Shows error message with retry options
- **Empty**: Handles empty data scenarios gracefully

## 🔍 Available Operations

```kotlin
// Repository operations via ViewModel
viewModel.loadConferences()                    // Load all conferences  
viewModel.loadConferencesByCategory("Tech")    // Filter by category
viewModel.searchConferences("Android")         // Search functionality
viewModel.refreshConferences()                 // Refresh data
viewModel.clearError()                        // Clear error state
```

## 📁 Project Structure

```
app/src/main/java/ru/simbirdevs/task_parthnerkin/
├── data/
│   ├── repo/ConferenceRepository.kt           # Data repository
│   ├── ConferenceData.kt                      # Data models  
│   └── MockData.kt                           # Mock data
├── di/
│   ├── AppComponent.kt                       # Main DI component
│   └── viewmodel/
│       ├── ViewModelsBindingModule.kt        # ViewModel bindings
│       └── MultiViewModelFactory.kt          # ViewModel factory
├── ui/
│   ├── components/
│   │   ├── MainScreen.kt                     # Main screen composable
│   │   ├── ConferenceItem.kt                 # Conference item UI
│   │   └── MainScreenTopBar.kt               # Top bar component
│   ├── viewmodel/MainScreenViewModel.kt       # ViewModel with DI
│   └── theme/                                # UI theming
└── utils/
    ├── App.kt                                # Application class
    └── Dates.kt                              # Date utilities
```

## ✅ Benefits

- **Dependency Injection**: Clean separation of concerns
- **Testability**: Easy to mock dependencies for testing
- **Type Safety**: Compile-time dependency validation
- **Scalability**: Easy to extend with new features
- **Performance**: Singleton repository prevents multiple instances
- **Reactive UI**: Automatic updates when data changes
- **Error Handling**: Comprehensive error management

## 📝 Documentation

See `DAGGER2_VIEWMODEL_IMPLEMENTATION.md` for detailed technical documentation and implementation
guides.

## 🏃‍♂️ Getting Started

1. Clone the repository
2. Open in Android Studio
3. Build the project (Dagger will generate necessary components)
4. Run on device or emulator

The app will automatically load conference data using the Dagger2-injected repository and display it
with proper loading and error states.

## 🧪 Testing

The architecture supports easy testing with mockable dependencies:

```kotlin
// Mock repository for testing
val mockRepository = mockk<ConferenceRepository>()
val viewModel = MainScreenViewModel(mockRepository)

// Test different scenarios
coEvery { mockRepository.getAllConferences() } returns testData
```

This implementation provides a solid foundation for Android apps requiring dependency injection,
reactive state management, and clean architecture patterns.