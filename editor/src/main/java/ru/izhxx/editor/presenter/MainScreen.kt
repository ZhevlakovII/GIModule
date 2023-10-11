package ru.izhxx.editor.presenter

import androidx.compose.runtime.Composable
import ru.izhxx.editor.presenter.components.EmptyScreen
import ru.izhxx.editor.presenter.components.InputScreen
import ru.izhxx.editor.presenter.components.ItemsScreen
import ru.izhxx.editor.presenter.components.LoadingScreen
import ru.izhxx.editor.presenter.model.MainScreenState

@Composable
internal fun MainScreen(
    state: MainScreenState,
    emptyScreenOnButtonAddClick: () -> Unit,
    itemsScreenOnButtonAddClick: () -> Unit,
    itemsScreenOnItemClick: (Int) -> Unit,
    inputScreenOnButtonAddClick: (name: String, url: String) -> Unit,
    inputScreenOnButtonCancelClick: () -> Unit
) {
    when (state) {
        MainScreenState.Empty -> EmptyScreen(
            onButtonAddClick = emptyScreenOnButtonAddClick
        )
        MainScreenState.Loading -> LoadingScreen()
        is MainScreenState.WithItems -> ItemsScreen(
            state = state,
            onButtonAddClick = itemsScreenOnButtonAddClick,
            onItemClick = itemsScreenOnItemClick
        )
        is MainScreenState.Input -> InputScreen(
            onButtonAddClick = inputScreenOnButtonAddClick,
            onButtonCancelClick = inputScreenOnButtonCancelClick,
            state
        )
    }
}