package ru.izhxx.editor

import android.app.Application
import ru.izhxx.editor.data.prefs.PreferenceManager
import ru.izhxx.editor.data.repository.WriterRepositoryImpl
import ru.izhxx.editor.domain.repository.WriterRepository

internal class EditorApplication : Application() {

    private var writerRepository: WriterRepository? = null

    companion object {
        @Volatile
        private var instance: EditorApplication? = null

        @JvmStatic
        fun getInstance(): EditorApplication = requireNotNull(instance) {
            "Arr, EditorApplication isn't initialize. Method getInstance()"
        }
    }

    override fun onCreate() {
        super.onCreate()

        writerRepository = WriterRepositoryImpl(applicationContext)
        PreferenceManager.init(applicationContext)
        instance = this
    }

    fun getWriterRepository(): WriterRepository = requireNotNull(writerRepository) {
        "Arr, writerRepository isn't create! Class EditorApplication, method getWriterRepository"
    }
}