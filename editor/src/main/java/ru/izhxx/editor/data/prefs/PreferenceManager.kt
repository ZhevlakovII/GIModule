package ru.izhxx.editor.data.prefs

import android.content.Context
import android.content.SharedPreferences


internal class PreferenceManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    companion object {
        private const val APP_PREFERENCES = "GIEditorPreferences"

        private val synchronizer = Any()

        @Volatile
        private var instance: PreferenceManager? = null

        fun getInstance(): PreferenceManager = synchronized(synchronizer) {
            return@synchronized requireNotNull(instance) {
                "PreferenceManager isn't initialize. Check init method"
            }
        }

        fun init(context: Context) {
            instance = PreferenceManager(context)
        }
    }

    fun editPreferences(): SharedPreferences.Editor = prefs.edit()

    fun getPreferences(): SharedPreferences = prefs
}