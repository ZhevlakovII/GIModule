package ru.izhxx.editor

import android.net.Uri
import kotlinx.coroutines.flow.StateFlow
import ru.izhxx.editor.presenter.model.MainScreenState

internal interface MainViewModel {

    val state: StateFlow<MainScreenState>
    val writeResult: StateFlow<Boolean?>

    fun onStart()
    fun onStop()

    fun onFileCreate(uri: Uri)

    fun emptyScreenOnButtonAddClick()

    fun itemsScreenOnButtonAddClick()
    fun itemsScreenOnItemClick(position: Int)

    fun inputScreenOnButtonAddClick(name: String, url: String)
    fun inputScreenOnButtonCancelClick()
}