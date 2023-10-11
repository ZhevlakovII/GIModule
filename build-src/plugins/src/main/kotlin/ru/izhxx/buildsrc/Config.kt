package ru.izhxx.buildsrc

import org.gradle.api.Project

object Config {

    const val MIN_SDK = 28
    const val COMPILE_SDK = 34
    const val TARGET_SDK = 33

    val Project.VERSION_CODE
        get() = getVersionCodeByMasterCommits()
    val Project.VERSION_NAME
        get() = getVersionNameByTag()
}