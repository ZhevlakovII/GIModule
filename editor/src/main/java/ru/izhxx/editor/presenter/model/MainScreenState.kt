package ru.izhxx.editor.presenter.model

import ru.izhxx.editor.domain.model.Server

internal sealed class MainScreenState {

    data object Empty : MainScreenState()

    data object Loading : MainScreenState()

    class WithItems(val serversData: List<Server>) : MainScreenState()

    class Input(val serverName: String, val serverUrl: String, val isError: Boolean) : MainScreenState()
}