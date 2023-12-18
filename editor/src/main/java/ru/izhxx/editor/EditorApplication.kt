package ru.izhxx.editor

import android.app.Application
import ru.izhxx.editor.data.prefs.PreferenceManager
import ru.izhxx.editor.data.repository.WriterRepositoryImpl
import ru.izhxx.editor.domain.repository.WriterRepository

internal class EditorApplication : Application() {

    private var writerRepository: WriterRepository? = null
    private var preferenceManager: PreferenceManager? = null

    companion object {
        @Volatile
        private var instance: EditorApplication? = null

        @JvmStatic
        fun getInstance(): EditorApplication = requireNotNull(instance) {
            "Arr, EditorApplication isn't initialize\n" + "EditorApplication#getInstance()"
        }
    }

    override fun onCreate() {
        super.onCreate()

        writerRepository = WriterRepositoryImpl(applicationContext)
        preferenceManager = PreferenceManager.init(applicationContext)
        instance = this
    }

    fun getWriterRepository(): WriterRepository = requireNotNull(writerRepository) {
        "Arr, writerRepository isn't create! Class EditorApplication, method getWriterRepository\n" +
                "EditorApplication#getWriterRepository()"
    }

    fun getPreferenceManager(): PreferenceManager = requireNotNull(preferenceManager) {
        "PreferenceManager isn't initialize\n" + "EditorApplication#getPreferenceManager()"
    }
}