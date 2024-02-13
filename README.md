#  NavCompose

[![license](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/aimicor/navcompose/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.aimicor/navcompose-android
)](https://central.sonatype.com/artifact/com.aimicor/navcompose-android)

This is an extension library for Google's [navigation-compose](https://developer.android.com/jetpack/compose/navigation) for passing complex data and for those more comfortable developing Kotlin code than they are manipulating strings.

- Creates route URI strings under the hood so you don't have to.
- Supports optional/ defaulted parameters
- Pass values of complex data types with ease.
- Pass multiple values at the same time.
- Designed for multiple module apps - navigate to individual screens in different modules or groups defined in subgraphs in different modules.
- Can work alongside existing [navigation-compose](https://developer.android.com/jetpack/compose/navigation) code. No need to replace anything.

## Installation
Include the libraries (legacy)
```kotlin dsl
dependencies {
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.aimicor:navcompose-android:[maven-central version]")
}
```
Include the libraries (version catalogs)
```kotlin dsl
// libs.versions.toml
[versions]
gson = "2.10.1"
navigation-compose = "2.7.7"
navcompose-android = "[maven-central version]"

[libraries]
navigation-compose = { module = "androidx.navigation:navigation-compose", version.ref = "navigation-compose" }
gson = { module = "com.google.code.gson:gson", version.ref = "gson" }
navcompose-android = { module = "com.aimicor:navcompose-android", version.ref = "navcompose-android" }

// build.gradle.kts
dependencies {
    implementation(libs.navigation.compose)
    implementation(libs.gson)
    implementation(libs.navcompose.android)
}
```

## Usage
### Navigate to a screen
```kotlin
val homescreen = navComposableSpec()
val detailScreen = navComposableSpec()
...

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = homescreen
            ) {
                composable(homescreen) { HomeScreen() }
                composable(detailScreen) { DetailScreen() }
            }
        }
    }
}
...

@Composable
fun HomeScreen() {
    ...
    val navController = LocalNavController.current
    navController.navigate(detailScreen)
}

```
### Navigate to a screen with a parameter
```kotlin
val param = navParamSpec<ComplexStuff>() // where ComplexStuff is some complex data class

val detailScreen = navComposableSpec(param) // must pass parameter
// or
val detailScreen = navComposableSpec(param with null) // default null if not passed
// or
val detailScreen = navComposableSpec(param with someVal) // default someVal if not passed
...

NavHost(
    navController = navController,
    startDestination = homescreen
) {
    composable(homescreen) { HomeScreen() }
    composable(detailScreen) { backStackEntry ->

        // fetch parameter from backStackEntry
        DetailScreen(backStackEntry.getNavParam(param))
    }
}
...

@Composable
fun HomeScreen() {
    ...
    val someOtherVal = ComplexStuff()
    navController.navigate(detailScreen, param with someOtherVal)
}
...

// Also (preferred)
class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val receivedParam: ComplexStuff = savedStateHandle.getNavParam(param)
) : ViewModel() { ... }
```
### Navigate to a screen with multiple parameters
```kotlin
val param1 = navParamSpec<ComplexStuff>()
val param2 = navParamSpec<ComplexStuff>()
val detailScreen = navComposableSpec(param1, param2 with null)
...
navController.navigate(detailScreen, param1 with someComplexVal, param2 with someOtherComplexVal)
...
class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val receivedParam1: ComplexStuff = savedStateHandle.getNavParam(param1),
    private val receivedParam2: ComplexStuff? = savedStateHandle.getNavParamOrNull(param2)
) : ViewModel() { ... }
```
### Navigate to a screen with a parameter in a different module
```kotlin
// define this in a module accessible by both send and receive modules,
// apart from this, code is the same.
val param = navParamSpec<ComplexStuff>()
val detailScreen = navComposableSpec(param)
```
### Navigate to a subgraph
```kotlin
val homescreen = navComposableSpec()
val detailScreen = navComposableSpec()
val detailGroup = navComposableGroup(startDestination = detailScreen) {
    composable(detailScreen) { DetailScreen() }
}
...
NavHost(
    navController = navController,
    startDestination = homescreen
) {
    composable(homescreen) { HomeScreen() }
    navigation(detailGroup)
}
...
navController.navigate(detailGroup)
```
### Navigate to a subgraph in a different module
```kotlin
// define this in a module accessible by both send and receive modules
val detailGroupRoute = navGroupRoute()
...
NavHost(
    navController = navController,
    startDestination = homescreen
) {
    composable(homescreen) { HomeScreen() }
    navigation(
        navComposableGroup(detailScreen, groupPath = detailGroupRoute) {
            composable(detailScreen) { DetailScreen() }
        }
    )
}
...
navController.navigate(detailGroupRoute)
```
