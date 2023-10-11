package ru.izhxx.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.izhxx.editor.data.converter.ServerConverter
import ru.izhxx.editor.data.prefs.PreferenceManager
import ru.izhxx.editor.data.repository.ServersDataRepositoryImpl
import ru.izhxx.editor.domain.repository.ServersDataRepository
import ru.izhxx.editor.domain.repository.WriterRepository

@Suppress("UNCHECKED_CAST")
internal class MainViewModelFactory(
    private val serversDataRepository: ServersDataRepository,
    private val writerRepository: WriterRepository
) : ViewModelProvider.Factory {

    companion object {
        fun getFactory(): MainViewModelFactory {
            return MainViewModelFactory(
                ServersDataRepositoryImpl(PreferenceManager.getInstance(), ServerConverter()),
                EditorApplication.getInstance().getWriterRepository()
            )
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModelImpl::class.java) -> {
                MainViewModelImpl(serversDataRepository, writerRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}