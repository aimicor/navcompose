plugins {
    id("java-library")
    alias(libs.plugins.kotlinJvm)
}

java {
    sourceCompatibility = Config.javaVersion
    targetCompatibility = Config.javaVersion
}

dependencies {
    implementation(libs.navcompose.android)
}