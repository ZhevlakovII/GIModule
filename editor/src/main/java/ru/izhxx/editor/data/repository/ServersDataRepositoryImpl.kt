package ru.izhxx.editor.data.repository

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.izhxx.editor.R
import ru.izhxx.editor.data.converter.ServerConverter
import ru.izhxx.editor.data.model.ServerDto
import ru.izhxx.editor.data.prefs.string.StringPreferences
import ru.izhxx.editor.domain.model.Server
import ru.izhxx.editor.domain.repository.ServersDataRepository
import ru.izhxx.editor.domain.util.OperationResult
import java.util.concurrent.CopyOnWriteArrayList

internal class ServersDataRepositoryImpl(
    private val stringPreferences: StringPreferences,
    private val serverConverter: ServerConverter
) : ServersDataRepository {

    private val servers: CopyOnWriteArrayList<Server> = CopyOnWriteArrayList()

    companion object {
        private const val SERVERS_KEY = "GIEditorServersJson"
    }

    override suspend fun getSavedServers(): OperationResult<List<Server>> {
        val serversJson = stringPreferences.getNullableValue(SERVERS_KEY)

        if (serversJson.isNullOrEmpty() || serversJson.isBlank()) {
            return OperationResult.Error(messageStringResId = R.string.write_error)
        }

        val resultList = Json.decodeFromString<List<ServerDto>>(serversJson)
        servers.addAll(resultList.map { serverConverter.convertToDomain(it) })

        return OperationResult.Success(resultData = servers)
    }

    override suspend fun saveServer(server: Server) {
        servers.add(server)
    }

    override suspend fun getServerByPosition(serverPosition: Int): OperationResult<Server> {
        val server = servers.getOrNull(serverPosition)

        return if (server == null) {
            OperationResult.Error(messageStringResId = R.string.write_error)
        } else {
            OperationResult.Success(server)
        }
    }

    override suspend fun writeServers() {
        stringPreferences.setValue(
            SERVERS_KEY,
            Json.encodeToString(servers.map { serverConverter.convertToData(it) })
        )
    }
}