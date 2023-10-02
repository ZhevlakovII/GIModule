@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "ru.izhxx.gimodule"
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.izhxx.gimodule"
        minSdk = 28
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("lib*.so"))))
    implementation(libs.ezxhelper)

    //i don't know how connect jars in gradle.kts :(
    //but if need, i save xposed jars
//    compileOnly(fileTree("$projectDir/xposed-module/api-82.jar"))
//    compileOnly(fileTree("$projectDir/xposed-module/api-82-sources.jar"))
    compileOnly(libs.xposed)
    compileOnly(libs.xposed.sources)
}