package ru.izhxx.editor.data.prefs

import android.content.Context
import android.content.SharedPreferences


internal class PreferenceManager private constructor(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    companion object {
        private const val APP_PREFERENCES = "GIEditorPreferences"

        private val synchronizer = Any()

        fun init(context: Context): PreferenceManager = synchronized(synchronizer) {
            return@synchronized PreferenceManager(context)
        }
    }

    fun editPreferences(): SharedPreferences.Editor = prefs.edit()

    fun getPreferences(): SharedPreferences = prefs
}