package ru.izhxx.buildsrc

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import ru.izhxx.buildsrc.Config.VERSION_CODE
import ru.izhxx.buildsrc.Config.VERSION_NAME

fun BaseExtension.appConfig(target: Project) {
    configureDefault(target)
    configureBuildTypes()
    configureBuildFeatures()
    configureCompileOptions(target)
}

internal fun BaseExtension.configureDefault(target: Project) {
    compileSdkVersion(Config.COMPILE_SDK)

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.COMPILE_SDK
        versionCode = target.VERSION_CODE
        versionName = target.VERSION_NAME
    }
}

internal fun BaseExtension.configureBuildTypes() {
    buildTypes {
        defaultConfig {
            buildConfigField(
                "String",
                "FILE_PATH",
                "/storage/emulated/0/Android/obb/com.yuuki.gi40cn/server.txt"
            )
        }
    }
}

private fun BaseExtension.configureBuildFeatures() {
    buildFeatures.buildConfig = true
    buildFeatures.viewBinding = false
    buildFeatures.aidl = false
    buildFeatures.compose = false
    buildFeatures.prefab = false
    buildFeatures.renderScript = false
    buildFeatures.resValues = false
    buildFeatures.shaders = false
}

internal fun BaseExtension.configureCompileOptions(target: Project) {
    compileOptions.sourceCompatibility = JavaVersion.VERSION_11
    compileOptions.targetCompatibility = JavaVersion.VERSION_11

    target.tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "11"
    }
}