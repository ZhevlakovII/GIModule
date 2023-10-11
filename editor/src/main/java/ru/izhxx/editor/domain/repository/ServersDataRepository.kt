package ru.izhxx.editor.domain.repository

import ru.izhxx.editor.domain.model.Server

internal interface ServersDataRepository {

    suspend fun getServersData(): List<Server>

    suspend fun addServer(server: Server)

    suspend fun saveToStorage()

    suspend fun selectServer(position: Int): Server
}