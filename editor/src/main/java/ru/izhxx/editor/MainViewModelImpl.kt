package ru.izhxx.editor

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.izhxx.editor.domain.model.Server
import ru.izhxx.editor.domain.repository.ServersDataRepository
import ru.izhxx.editor.domain.repository.WriterRepository
import ru.izhxx.editor.presenter.model.MainScreenState
import ru.izhxx.editor.utils.ipRegex
import ru.izhxx.editor.utils.urlRegex

internal class MainViewModelImpl(
    private val serversDataRepository: ServersDataRepository,
    private val writerRepository: WriterRepository
) : ViewModel(), MainViewModel {

    override val state: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Loading)
    override val writeResult: MutableStateFlow<Boolean?> = MutableStateFlow(null)

    private var inputState: MainScreenState.Input = MainScreenState.Input("", "", false)
    private var selectedServer: Server? = null

    override fun onStart() {
        enableLoading()
        fetchServers()
    }

    override fun onStop() {
        viewModelScope.launch {
            serversDataRepository.saveToStorage()
        }
    }

    override fun onFileCreate(uri: Uri) {
        val file = selectedServer
        viewModelScope.launch {
            if (file != null)
                writeResult.value = writerRepository.write(file, uri)
        }
    }

    override fun emptyScreenOnButtonAddClick() {
        state.value = inputState
    }

    override fun itemsScreenOnButtonAddClick() {
        state.value = inputState
    }

    override fun itemsScreenOnItemClick(position: Int) {
        viewModelScope.launch {
            selectedServer = serversDataRepository.selectServer(position)
        }
    }

    override fun inputScreenOnButtonAddClick(name: String, url: String) {
        if (isCorrectInput(url)) {
            viewModelScope.launch {
                serversDataRepository.addServer(Server(name, url))
                fetchServers()
                clearInputState()
            }
        } else {
            inputState = MainScreenState.Input(
                name,
                url,
                true
            )
            state.value = inputState
        }
    }

    override fun inputScreenOnButtonCancelClick() {
        fetchServers()
    }

    private fun enableLoading() {
        state.value = MainScreenState.Loading
    }

    private fun fetchServers() {
        viewModelScope.launch {
            val serversData = serversDataRepository.getServersData()
            if (serversData.isNotEmpty()) {
                state.value = MainScreenState.WithItems(serversData)
            } else {
                state.value = MainScreenState.Empty
            }
        }
    }

    private fun clearInputState() {
        inputState = MainScreenState.Input("", "", false)
    }

    private fun isCorrectInput(text: String?): Boolean {
        //if empty -> use default
        if (text.isNullOrEmpty() || text.isBlank()) return false
        //if ip -> check port
        if (text.matches(ipRegex)) return isCorrectPort(text.toString())
        //last check -> if correct url
        return text.matches(urlRegex)
    }

    private fun isCorrectPort(url: String): Boolean {
        //Get port
        val port = url.substringAfterLast(":").substringBefore("/")

        //if port equals 0, 00, 000, 0000 -> invalid port
        return when (port.lastIndex) {
            0 -> port[0] != '0'
            else -> {
                port.forEach { if (it != '0') return true }
                false
            }
        }
    }
}