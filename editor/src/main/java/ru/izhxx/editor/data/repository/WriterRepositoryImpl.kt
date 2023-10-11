package ru.izhxx.editor.data.repository

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.izhxx.editor.domain.model.Server
import ru.izhxx.editor.domain.repository.WriterRepository
import java.io.BufferedWriter
import java.io.OutputStreamWriter

internal class WriterRepositoryImpl(
    private val context: Context
) : WriterRepository {

    override suspend fun write(server: Server, uri: Uri): Boolean = withContext(Dispatchers.IO) {
        try {
            val buffer = BufferedWriter(OutputStreamWriter(context.contentResolver.openOutputStream(uri, "wt")))
            buffer.use {
                it.write("")
                it.write(server.serverUrl)
            }
            return@withContext true
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext false
        }
    }
}