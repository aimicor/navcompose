plugins {
    id("maven-publish")
    alias(libs.plugins.dokka)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.aimicor.navcompose"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    packaging {
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
    }

    dependencies {
        implementation(libs.gson)
        implementation(libs.navigation.compose)

        androidTestImplementation(libs.junit)
        androidTestImplementation(libs.kotlin.test)
        androidTestImplementation(libs.mockk.android)
        androidTestImplementation(libs.compose.test)
        androidTestImplementation(libs.kotlin.reflect)

        debugImplementation(libs.compose.test.manifest)
    }
}


private val projectDescription = """
    This is an extension library for Google's Jetpack Compose navigation library.
    (https://developer.android.com/jetpack/compose/navigation).
    Takes care of route string building and provides complex data
    class parameter passing between composable screens.
  """

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.aimicor"
            artifactId = "navcompose-android"
            version = libs.versions.navcompose.get()

            pom {
                url = "https://github.com/aimicor"
                description = projectDescription
                scm {
                    connection = "scm:git:https://github.com/aimicor/navcompose.git"
                    developerConnection = "scm:git:ssh://github.com:aimicor/navcompose.git"
                    url = "https://github.com/aimicor/navcompose.git"
                }
                licenses {
                    license {
                        name = "The MIT License"
                        url = "https://github.com/aimicor/navcompose/blob/main/LICENSE"
                        distribution = "repo"
                    }
                }
                developers {
                    developer {
                        id = "aimicor"
                        name = "Aimicor"
                        email = "info@aimicor.com"
                        organization = "Aimicor Ltd."
                        organizationUrl = "aimicor.com"
                    }
                }
            }
        }
    }
}

tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("html-docs")
}

tasks.register<Jar>("dokkaJavadocJar") {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}