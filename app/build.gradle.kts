plugins {
    id("gimodule.app")
}

android {
    namespace = "ru.izhxx.gimodule"

    defaultConfig {
        applicationId = "ru.izhxx.gimodule"
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("lib*.so"))))
    implementation(libs.ezxhelper)
    compileOnly(libs.xposed)
    compileOnly(libs.xposed.sources)

//    i don't know how connect jars in gradle.kts :(
//    but if need, i save xposed jars
//    compileOnly(fileTree("$projectDir/xposed-module/api-82.jar"))
//    compileOnly(fileTree("$projectDir/xposed-module/api-82-sources.jar"))
}