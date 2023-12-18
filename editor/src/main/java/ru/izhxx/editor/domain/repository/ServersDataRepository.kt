package ru.izhxx.editor.domain.repository

import ru.izhxx.editor.domain.model.Server
import ru.izhxx.editor.domain.util.OperationResult

internal interface ServersDataRepository {

    suspend fun getSavedServers(): OperationResult<List<Server>>

    suspend fun saveServer(server: Server)

    suspend fun getServerByPosition(serverPosition: Int): OperationResult<Server>

    suspend fun writeServers()
}