package ru.izhxx.editor.presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.izhxx.editor.R

@Composable
internal fun LoadingScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.loading_screen_title),
            fontSize = 17.sp,
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically)
        )
        Text(
            text = stringResource(R.string.loading_screen_subtitle),
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically)
                .padding(top = 8.dp)
        )
        LinearProgressIndicator(
            color = Color.Black,
            trackColor = Color.Cyan,
            strokeCap = StrokeCap.Round,
            modifier = Modifier.fillMaxWidth().height(4.dp).padding(top = 16.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewLoadingScreen() {
    LoadingScreen()
}