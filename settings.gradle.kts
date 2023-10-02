pluginManagement {
    repositories {
        //noinspection JcenterRepositoryObsolete
        jcenter()
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        //noinspection JcenterRepositoryObsolete
        jcenter()
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "GIModule"
include(":app")