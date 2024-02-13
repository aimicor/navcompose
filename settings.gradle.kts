rootProject.name = "navcompose"

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":navcompose")
include(":examples:singlemodule:composeApp")
include(":examples:scaffoldandfullscreen:composeApp")
include(":examples:multimoduleflatstructure:composeApp")
include(":examples:multimoduleflatstructure:home")
include(":examples:multimodulenestedstructure:composeApp")
include(":examples:multimodulenestedstructure:home")
include(":examples:multimodulenestedstructure:detail")
include(":examples:multimodulepeerstructure:composeApp")
include(":examples:multimodulepeerstructure:home")
include(":examples:multimodulepeerstructure:detail")
include(":examples:multimodulepeerstructure:navcompose")
include(":examples:navigatetogroup:composeApp")
include(":examples:navigatetoremotegroup:composeApp")
include(":examples:navigatetoremotegroup:home")
include(":examples:navigatetoremotegroup:detail")
include(":examples:navigatetoremotegroup:navcompose")
