package ru.izhxx.editor.data.converter

import ru.izhxx.editor.data.model.ServerDto
import ru.izhxx.editor.domain.model.Server

internal class ServerConverter {

    fun convertToDomain(serverDto: ServerDto): Server = Server(
        serverName = serverDto.serverName ?: "Unknown server name",
        serverUrl = serverDto.serverUrl ?: "Unknown server url"
    )

    fun convertToData(server: Server): ServerDto = ServerDto(
        serverName = server.serverName,
        serverUrl = server.serverUrl
    )
}