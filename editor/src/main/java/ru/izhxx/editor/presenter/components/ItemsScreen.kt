package ru.izhxx.editor.presenter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.izhxx.editor.domain.model.Server
import ru.izhxx.editor.presenter.model.MainScreenState

@Composable
internal fun ItemsScreen(
    state: MainScreenState.WithItems,
    onItemClick: (Int) -> Unit,
    onButtonAddClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)
        ) {
            itemsIndexed(state.serversData) { position, item ->
                ServerView(item, Modifier.clickable {
                    onItemClick.invoke(position)
                })
            }
        }
        IconButton(
            onClick = onButtonAddClick,
            modifier = Modifier.wrapContentSize().align(Alignment.BottomEnd).padding(end = 20.dp, bottom = 20.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color.Magenta
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                modifier = Modifier.size(48.dp).padding(8.dp)
            )
        }
    }
}

@Composable
private fun ServerView(
    server: Server,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = server.serverName,
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically)
        )
        Text(
            text = server.serverUrl,
            fontSize = 14.sp,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically).padding(top = 4.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewItemsScreen() {
    val items = listOf(
        Server("aaa", "asdas"),
        Server("aaa", "asdas"),
        Server("aaa", "asdas"),
        Server("aaa", "asdas"),
        Server("aaa", "asdas")
    )
    ItemsScreen(
        MainScreenState.WithItems(items),
        onItemClick = {},
        onButtonAddClick = {}
    )
}