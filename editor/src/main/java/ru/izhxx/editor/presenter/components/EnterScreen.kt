package ru.izhxx.editor.presenter.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.izhxx.editor.R
import ru.izhxx.editor.presenter.model.MainScreenState

@Composable
internal fun InputScreen(
    onButtonAddClick: (name: String, url: String) -> Unit,
    onButtonCancelClick: () -> Unit,
    state: MainScreenState.Input,
) {
    var nameTextState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(state.serverName))
    }
    var serverTextState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(state.serverUrl))
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)
    ) {
        OutlinedTextField(
            value = nameTextState,
            onValueChange = { nameTextState = it },
            label = { TextFieldLabel(stringResource(R.string.enter_screen_name_label), false) },
            modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically)
        )
        OutlinedTextField(
            value = serverTextState,
            onValueChange = { serverTextState = it },
            label = {
                TextFieldLabel(
                    stringResource(if (state.isError) R.string.enter_screen_input_error else R.string.enter_screen_url_label),
                    state.isError
                )
            },
            isError = state.isError,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically)
                .padding(top = 4.dp)
        )
        Button(
            onClick = { onButtonAddClick.invoke(nameTextState.text, serverTextState.text) },
            modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.enter_screen_button_add_text),
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentSize().wrapContentHeight().padding(all = 4.dp)
            )
        }
        OutlinedButton(
            onClick = onButtonCancelClick,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically)
                .padding(top = 4.dp)
        ) {
            Text(
                text = stringResource(R.string.enter_screen_button_close_text),
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentSize().wrapContentHeight().padding(all = 4.dp)
            )
        }
    }
}

@Composable
private fun TextFieldLabel(
    text: String,
    isError: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically)
    ) {
        Text(
            text,
            color = if (isError) Color.Red else Color.Unspecified
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewEnterScreen() {
    InputScreen(
        onButtonAddClick = { name, url -> },
        onButtonCancelClick = {},
        MainScreenState.Input(
            "asb",
            "asd",
            true
        )
    )
}