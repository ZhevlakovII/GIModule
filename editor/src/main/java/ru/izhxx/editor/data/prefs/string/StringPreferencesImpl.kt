package ru.izhxx.editor.data.prefs.string

import ru.izhxx.editor.data.prefs.PreferenceManager

internal class StringPreferencesImpl(
    private val preferenceManager: PreferenceManager
) : StringPreferences {

    override fun getNullableValue(key: String): String? =
        preferenceManager.getPreferences().getString(key, null)


    override fun getValueWithDefault(key: String, defaultValue: String): String =
        preferenceManager.getPreferences().getString(key, defaultValue) ?: defaultValue

    override fun setValue(key: String, value: String) {
        preferenceManager.editPreferences().putString(key, value).apply()
    }
}