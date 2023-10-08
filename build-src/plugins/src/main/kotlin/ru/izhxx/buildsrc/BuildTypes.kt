package ru.izhxx.buildsrc

import org.gradle.api.NamedDomainObjectContainer

fun <BuildTypeT> NamedDomainObjectContainer<BuildTypeT>.debug(
    action: BuildTypeT.() -> Unit
) {
    maybeCreate("debug").action()
}

fun <BuildTypeT> NamedDomainObjectContainer<BuildTypeT>.release(
    action: BuildTypeT.() -> Unit
) {
    maybeCreate("release").action()
}