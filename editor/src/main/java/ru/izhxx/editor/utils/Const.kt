package ru.izhxx.editor.utils

internal val urlRegex = Regex("https?://[a-zA-Z./?@=+&\\d#]{0,256}$")
internal val ipRegex = Regex("https?://([a-zA-Z\\d.]{2,256}):(\\d{1,5})$")