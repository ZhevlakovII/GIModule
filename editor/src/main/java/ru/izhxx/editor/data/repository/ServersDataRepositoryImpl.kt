package ru.izhxx.editor.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.izhxx.editor.data.converter.ServerConverter
import ru.izhxx.editor.data.model.ServerDto
import ru.izhxx.editor.data.prefs.PreferenceManager
import ru.izhxx.editor.domain.model.Server
import ru.izhxx.editor.domain.repository.ServersDataRepository
import java.util.concurrent.CopyOnWriteArrayList

internal class ServersDataRepositoryImpl(
    private val preferenceManager: PreferenceManager,
    private val serverConverter: ServerConverter
) : ServersDataRepository {

    private val servers: CopyOnWriteArrayList<Server> = CopyOnWriteArrayList()

    companion object {
        private const val SERVERS_KEY = "GIEditorServersJson"
    }

    override suspend fun getServersData(): List<Server> = withContext(Dispatchers.IO) {
        if (servers.isNotEmpty()) {
            return@withContext servers
        }

        val serversJson = preferenceManager.getPreferences().getString(SERVERS_KEY, "")

        if (!serversJson.isNullOrEmpty() && serversJson.isNotBlank()) {
            val data = Json.decodeFromString<List<ServerDto>>(serversJson)
            if (data.isNotEmpty()) {
                servers.addAll(data.map { serverConverter.convertToDomain(it) })
            }
        }
        return@withContext servers
    }

    override suspend fun addServer(server: Server) {
        withContext(Dispatchers.IO) {
            servers.add(server)
        }
    }

    override suspend fun saveToStorage() {
        withContext(Dispatchers.IO) {
            val serversJson = Json.encodeToString(servers.map { serverConverter.convertToData(it) })
            preferenceManager.editPreferences().putString(SERVERS_KEY, serversJson).apply()
        }
    }

    override suspend fun selectServer(position: Int): Server = servers[position]
}