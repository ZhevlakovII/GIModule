
import com.android.build.gradle.BaseExtension
import ru.izhxx.buildsrc.appConfig
import ru.izhxx.buildsrc.debug

plugins {
    id("com.android.application")
    id("kotlin-android")
}

configure<BaseExtension> {
    appConfig(project)

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
}