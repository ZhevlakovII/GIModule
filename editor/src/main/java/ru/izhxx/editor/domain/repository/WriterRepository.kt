package ru.izhxx.editor.domain.repository

import android.net.Uri
import ru.izhxx.editor.domain.model.Server

internal interface WriterRepository {

    suspend fun write(server: Server, uri: Uri): Boolean
}