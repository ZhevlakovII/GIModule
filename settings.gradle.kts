@file:Suppress("DEPRECATION", "UnstableApiUsage")

rootProject.name = "GIModule"

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    includeBuild("build-src")

    repositories {
        //noinspection JcenterRepositoryObsolete
        jcenter()
        google()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositories {
        //noinspection JcenterRepositoryObsolete
        jcenter()
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

include(
    ":app",
    ":editor"
)