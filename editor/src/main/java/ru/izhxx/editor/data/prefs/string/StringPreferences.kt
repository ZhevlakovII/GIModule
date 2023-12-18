package ru.izhxx.editor.data.prefs.string

internal interface StringPreferences {

    fun getNullableValue(key: String): String?

    fun getValueWithDefault(key: String, defaultValue: String): String

    fun setValue(key: String, value: String)
}